package schedule;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


/**
 * Servlet implementation class login
 */
@WebServlet(description = "Authentication system", urlPatterns = { "/home" })
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RequestDispatcher view=request.getRequestDispatcher("studentHome.jsp");
		//view.forward(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	    String DB_URL="jdbc:mysql://localhost/schedule";
		
	    String USER = "root";
	    String PASS = "";
	    
	    response.setContentType("text/html");
	    //PrintWriter out = response.getWriter();
	    String title = "Database Result";

	    try {
	         // Register JDBC driver
	         Class.forName("com.mysql.jdbc.Driver");

	         // Open a connection
	         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

	         // Execute SQL query
	         Statement stmt = conn.createStatement();
	         String sql;
	         sql = "SELECT * FROM users WHERE email = ? && password = ?";
	         PreparedStatement newpst=conn.prepareStatement(sql);
	         newpst.setString(1, email);
	         newpst.setString(2, pass);
	         
	         ResultSet newrs=newpst.executeQuery();
	         System.out.println(newrs);
	         if(newrs != null) {
	       
	        	 while(newrs.next()){
	        		 //String uname = newrs.getString("email");
	        		 //String upass = newrs.getString("password");

	        		 HttpSession session = request.getSession();  
	    	         session.setAttribute("email", email); 
	    	         RequestDispatcher view=request.getRequestDispatcher("studentHome.jsp");
	    	          view.forward(request,response);
	    	          return;
	        	 }
	     	   
	         }else {
	        	 System.out.println("entre last else");
	        	 response.sendRedirect("/Scheduler");
	     		
			}
	         

	         // Extract data from result set
	         /**
	         while(rs.next()){
	            //Retrieve by column name
	            int id  = rs.getInt("id");
	            int age = rs.getInt("age");
	            String first = rs.getString("first");
	            String last = rs.getString("last");

	            //Display values
	            out.println("ID: " + id + "<br>");
	            out.println(", Age: " + age + "<br>");
	            out.println(", First: " + first + "<br>");
	            out.println(", Last: " + last + "<br>");
	         }
	         out.println("</body></html>");
	        

	         // Clean-up environment
	         rs.close();
	         */
	         newrs.close();
	         stmt.close();
	         conn.close();
	      } catch(SQLException se) {
	         //Handle errors for JDBC
	         se.printStackTrace();
	      } catch(Exception e) {
	         //Handle errors for Class.forName
	         e.printStackTrace();
	      }
		
	}

}
