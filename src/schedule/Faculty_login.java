package schedule;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Faculty_login
 */
@WebServlet("/faculty-home")
public class Faculty_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Faculty_login() {
        //super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = (String) request.getSession(false).getAttribute("email");
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
	         sql = "SELECT * FROM faculty WHERE email = ?";
	         PreparedStatement pst=conn.prepareStatement(sql);
	         pst.setString(1, email);
	         ResultSet rs=pst.executeQuery();
	         
	         if(rs != null) {
	     		while (rs.next()) {
	     	        String usertype= rs.getString("type");
	     	        
	     	        
	     	       if (usertype.equals("Teacher")) {
	     	    	 
	     	    	  RequestDispatcher view=request.getRequestDispatcher("teacherHome.jsp");
	 	     		 view.forward(request,response);
	 	     		 return;
		     	    } else if (usertype.equals("Admin")) {
		     	    	
		     	    	RequestDispatcher view=request.getRequestDispatcher("adminHome.jsp");
			     		 view.forward(request,response);
			     		 return;
		     	    }else {
		     	    	
		     	    	RequestDispatcher view=request.getRequestDispatcher("adminLogin.jsp");
			     		 view.forward(request,response);
			     		 return;
					}
	     	    }
	     	   
	         }else{
	        	 //response.sendRedirect("/Scheduler/admin");
	        	 RequestDispatcher view=request.getRequestDispatcher("adminLogin.jsp");
	     		 view.forward(request,response);
	     		 return;
	     		
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
	         sql = "SELECT * FROM faculty WHERE email = ? && password = ?";
	         PreparedStatement pst=conn.prepareStatement(sql);
	         pst.setString(1, email);
	         pst.setString(2, pass);
	         
	         ResultSet rs=pst.executeQuery();
	         
	         if(rs != null) {
	        	 HttpSession session = request.getSession();  
	             session.setAttribute("email", email);
	     		while (rs.next()) {
	     	        String usertype= rs.getString("type");
	     	        
	     	        
	     	       if (usertype.equals("Teacher")) {
	     	    	 
	     	    	  RequestDispatcher view=request.getRequestDispatcher("teacherHome.jsp");
	 	     		 view.forward(request,response);
	 	     		 return;
		     	    } else if (usertype.equals("Admin")) {
		     	    	
		     	    	RequestDispatcher view=request.getRequestDispatcher("adminHome.jsp");
			     		 view.forward(request,response);
			     		 return;
		     	    }else {
		     	    	
		     	    	RequestDispatcher view=request.getRequestDispatcher("adminLogin.jsp");
			     		 view.forward(request,response);
			     		 return;
					}
	     	    }
	     	   
	         }else{
	        	
	        	 RequestDispatcher view=request.getRequestDispatcher("adminLogin.jsp");
	     		 view.forward(request,response);
	     		 return;
	     		
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
