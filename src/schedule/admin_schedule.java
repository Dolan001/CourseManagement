package schedule;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class admin_schedule
 */
@WebServlet(description = "Go to admin page", urlPatterns = { "/course-management" })
public class admin_schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_schedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equals("assign_course")){ 
			RequestDispatcher view=request.getRequestDispatcher("assignCourse.jsp");
    		 view.forward(request,response);
		}

		else if (action.equals("course_info")){
			String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		    String DB_URL="jdbc:mysql://localhost/schedule";
			
		    String USER = "root";
		    String PASS = "";
		    
		    response.setContentType("text/html");
		   
		    String title = "Database Result";

		    try {
		         // Register JDBC driver
		         Class.forName("com.mysql.jdbc.Driver");

		         // Open a connection
		         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

		         // Execute SQL query
		         Statement stmt = conn.createStatement();
		         String sql;

		         sql = "SELECT course_code, teacher, semester, take_course FROM course_teacher";
		         
		         ResultSet rs=stmt.executeQuery(sql); 
		         
		         ArrayList<New> items = new ArrayList<New>();
		         
		         while (rs.next()) {
		        	 New e = new New(); 
		        	 e.setCode(rs.getString("course_code")); 
		        	 e.setName(rs.getString("teacher"));
		        	 e.setSem(rs.getString("semester"));
		        	 e.setCurrent(rs.getString("take_course"));
		        	 
		        	 items.add(e);
		     	        
		     	    }
		         stmt.close();
		         conn.close();
		         request.getSession().setAttribute("items",items); 
		        // request.setAttribute("items", items);
				RequestDispatcher view=request.getRequestDispatcher("courseInfoAdmin.jsp");
				view.forward(request,response);
		      } catch(SQLException se) {
		         //Handle errors for JDBC
		         se.printStackTrace();
		      } catch(Exception e) {
		         //Handle errors for Class.forName
		         e.printStackTrace();
		      }
		}
		else if (action.equals("students_info")){ 
			String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		    String DB_URL="jdbc:mysql://localhost/schedule";
			
		    String USER = "root";
		    String PASS = "";
		    
		    response.setContentType("text/html");
		   
		    String title = "Database Result";

		    try {
		         // Register JDBC driver
		         Class.forName("com.mysql.jdbc.Driver");

		         // Open a connection
		         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

		         // Execute SQL query
		         Statement stmt = conn.createStatement();
		         String sql;

		         sql = "SELECT reg_no, student_name, course_code, semester FROM course_student";
		         
		         ResultSet rs=stmt.executeQuery(sql); 
		         
		         ArrayList<New> items = new ArrayList<New>();
		         
		         while (rs.next()) {
		        	 New e = new New(); 
		        	 e.setCode(rs.getString("reg_no")); 
		        	 e.setName(rs.getString("student_name"));
		        	 e.setSem(rs.getString("course_code"));
		        	 e.setCurrent(rs.getString("semester"));
		        	 
		        	 items.add(e);
		     	        
		     	    }
		         stmt.close();
		         conn.close();
		         request.getSession().setAttribute("items",items); 
		        // request.setAttribute("items", items);
				RequestDispatcher view=request.getRequestDispatcher("studentsInfoAdmin.jsp");
				view.forward(request,response);
		      } catch(SQLException se) {
		         //Handle errors for JDBC
		         se.printStackTrace();
		      } catch(Exception e) {
		         //Handle errors for Class.forName
		         e.printStackTrace();
		      }
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
