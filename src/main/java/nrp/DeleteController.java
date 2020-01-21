package nrp;

	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	@WebServlet("/DeleteController")
	public class DeleteController extends HttpServlet
	{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
			int productid=Integer.parseInt(req.getParameter("productid"));
			
			try 
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://35.225.40.133:3306/db","root","root");
				PreparedStatement ps=con.prepareStatement("update mobiles set status='D' where productid=?");
				ps.setInt(1, productid); 
				ps.executeUpdate();
				resp.sendRedirect("displaymobile.jsp");
			} 
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	}


