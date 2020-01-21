package nrp;

	import java.io.IOException;
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;
	import nrp.model.User;

	@WebServlet(name = "LoginController",urlPatterns = "/LoginController")
	public class LoginController extends HttpServlet 
	{	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
			User u=new User();
			u.setUsername(req.getParameter("username"));
			u.setPassword(req.getParameter("password"));
			
			try 
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://35.225.40.133:3306/db","root","root");
				PreparedStatement ps=con.prepareStatement("select * from users where username=? and password=?");
				ps.setString(1, u.getUsername());
				ps.setString(2,u.getPassword());
				ResultSet rs=ps.executeQuery();
				
				PrintWriter out=resp.getWriter();
				HttpSession hs=req.getSession();
				
				if(rs.next())
				{
					u.setRole(rs.getString("role"));
					u.setEmail(rs.getString("email"));
					hs.setAttribute("u", u);
					out.println("<script>"
							+ "alert('Welcome "+u.getUsername()+"');"
									+ "window.location='home.jsp';"
							+ "</script>");
				}
				
				else
				{
					out.println("<script>"
							+ "alert('Incorrect username or password');"
									+ "window.location='login.jsp';"
							+ "</script>");
				}
			}
			catch (Exception e) 
			{
				PrintWriter out=resp.getWriter();
				out.print(e);
			}
		}
	}

