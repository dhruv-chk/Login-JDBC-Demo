package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dbcheck
 */
@WebServlet("/login.do")
public class dbcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dbcheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int flag=0;
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		String name= request.getParameter("name");
		String password= request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		try
		{
			Connection con=DriverManager.getConnection(url,"system","root");
			Statement st=con.createStatement();
			//ResultSet rs2 = st.executeUpdate("select username, password from login where username="+name+" and password="+password);
			ResultSet rs = st.executeQuery("select * from login");
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "+rs.getString(2));
				if(rs.getString(1).equals(name) && rs.getString(2).equals(password))
				{	System.out.println(rs.getString(1)+" "+rs.getString(2));
					out.println("<html>");
					out.println("<body>");
					out.println("<h3>Login successful for "+name+".</h3>");
					//out.println("\);
					out.println("</body>");
					out.println("</html>");
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				out.println("<html>");
				out.println("<body>");
				out.println("<h3>Username or password invalid.</h3>");
				out.println("</body>");
				out.println("</html>");
			}
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}

}
