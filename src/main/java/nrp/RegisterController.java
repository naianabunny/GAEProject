package nrp;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nrp.model.User;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		User u=new User();
		u.setUsername(req.getParameter("username"));
		u.setEmail(req.getParameter("email"));
		u.setPassword(req.getParameter("password"));
		
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://35.255.40.133:3306/db","root","root");
			PreparedStatement ps=con.prepareStatement("insert into users (username,email,password) values(?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.executeUpdate();
			
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	}

