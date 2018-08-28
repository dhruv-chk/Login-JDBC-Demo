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
 * Servlet implementation class dbserve
 */
@WebServlet("/main.do")
public class dbserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dbserve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		String name= request.getParameter("name");
		String password= request.getParameter("password");
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con=DriverManager.getConnection(url,"system","root");
			Statement st=con.createStatement();
			st.execute("Create table login(username varchar(10),password varchar(10))");
			PreparedStatement ps= con.prepareStatement("insert into login valueS(?,?)");
			ps.setString(1,name);
			ps.setString(2,password);
			ps.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h3>Sign Up for "+name+" is complete.</h3>");
		out.println("<a href='login.html'>Login now.</a>");
		out.println("</body>");
		out.println("</html>");
	}

}
