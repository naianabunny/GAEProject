package nrp;

	import java.io.ByteArrayOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.util.ArrayList;
	import java.util.Arrays;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.MultipartConfig;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.Part;

	import com.google.cloud.storage.Acl;
	import com.google.cloud.storage.Acl.Role;
	import com.google.cloud.storage.Acl.User;
	import com.google.cloud.storage.BlobInfo;
	import com.google.cloud.storage.Storage;
	import com.google.cloud.storage.StorageOptions;

	import nrp.model.Mobile;

	@WebServlet(name="AddMobileController",urlPatterns = {"/AddMobileController"})
	@MultipartConfig(maxFileSize = 999999999999999L)
	public class AddMobileController extends HttpServlet
	{
		  private static Storage storage = null;

		  static 
		  {
		    storage = StorageOptions.getDefaultInstance().getService();
		  }
		  
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
			Mobile m=new Mobile();
			m.setMobilename(req.getParameter("mobilename"));
			m.setModel(req.getParameter("model"));
			m.setPrice(Float.parseFloat(req.getParameter("price")));
			Part part=req.getPart("image");
			InputStream is=part.getInputStream();
			
			/*Remove this code to store image in database table*/
			ByteArrayOutputStream os = new ByteArrayOutputStream();		
			byte[] readBuf = new byte[4096];
			  while (is.available() > 0) {
			    int bytesRead = is.read(readBuf);
			    os.write(readBuf, 0, bytesRead);
			  }
			  
			  /*Remove this code to store image in database table*/
				/*Store image in bucket*/
				BlobInfo blobInfo =
				        storage.create(
				            BlobInfo
				                .newBuilder("bucket-1_mobile", m.getMobilename()+".jpg")
				                // Modify access list to allow all users with link to read file
				                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
				                .build(),
				            os.toByteArray());
				
				String imagelink=blobInfo.getMediaLink();
			  
			try 
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://35.225.40.133:3306/db","root","root");
				PreparedStatement ps=con.prepareStatement("insert into mobiles (mobilename,model,price,status,imagelink) values(?,?,?,?,?)");
				ps.setString(1,m.getMobilename());
				ps.setString(2,m.getModel());
				ps.setFloat(3,m.getPrice());
				ps.setString(4,"A");
				//Store image in database in binary format (stream format)
				//ps.setBlob(5, is);
				ps.setString(5, imagelink);
				ps.executeUpdate();

				
				
				PrintWriter out=resp.getWriter();
				out.println("<script>"
						+ "alert('Phone inserted successfully!!!!');"
								+ "window.location='addmobile.jsp';"
						+ "</script>");
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	}


