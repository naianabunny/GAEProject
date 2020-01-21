package nrp;

	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;

	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

import nrp.model.Mobile;

	@WebServlet("/EditController")
	public class EditController extends HttpServlet 
	{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
			int productid=Integer.parseInt(req.getParameter("productid"));
			
			try 
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://35.225.40.133:3306/db","root","root");
				PreparedStatement ps=con.prepareStatement("select * from mobiles where productid=?");
				ps.setInt(1,productid);
				
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
					Mobile m=new Mobile();
					m.setProductid(rs.getInt("productid"));
					m.setMobilename(rs.getString("mobilename"));
					m.setModel(rs.getString("model"));
					m.setPrice(rs.getFloat("price"));
					
					req.setAttribute("mobile", m);
					RequestDispatcher rd=req.getRequestDispatcher("addmobile.jsp");
					rd.forward(req, resp);
				}
				
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
			Mobile m=new Mobile();
			m.setProductid(Integer.parseInt(req.getParameter("productid")));
			m.setMobilename(req.getParameter("mobilename"));
			m.setModel(req.getParameter("model"));
			m.setPrice(Float.parseFloat(req.getParameter("price")));
			
			try 
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://35.225.40.133:3306/db","root","root");
				PreparedStatement ps=con.prepareStatement("update mobiles set mobilename=?, model=?, price=? where bookid=?");
				ps.setString(1,m.getMobilename());
				ps.setString(2,m.getModel());
				ps.setFloat(3,m.getPrice());
				ps.setInt(4,m.getProductid());
				ps.executeUpdate();
				resp.sendRedirect("mobile.jsp");
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	}

