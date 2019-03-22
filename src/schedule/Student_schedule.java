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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Student_schedule
 */


@WebServlet("/students-course-management")
public class Student_schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_schedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equals("regcourse")){ 
			RequestDispatcher view=request.getRequestDispatcher("newRegCourse.jsp");
    		 view.forward(request,response);
		}
	
		else if (action.equals("regedcourse")){
			String email = (String) request.getSession(false).getAttribute("email");
			
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
		         sql = "SELECT * FROM users WHERE email = ?";
		         PreparedStatement pst=conn.prepareStatement(sql);
		         pst.setString(1, email);
		         ResultSet rs=pst.executeQuery();
		         
		         String userRegno = null;
		         while (rs.next()) {
		     	        userRegno= rs.getString("regno");       
		     	    }
		         
		         String get_student_info;
		         get_student_info = "SELECT course_code, course_name, semester, on_going FROM course_student WHERE reg_no = ?";
		         PreparedStatement get_student_info_pst=conn.prepareStatement(get_student_info);
		         get_student_info_pst.setString(1, userRegno);
		         ResultSet get_student_info_rs=get_student_info_pst.executeQuery(); 
		         
		         ArrayList<New> items = new ArrayList<New>();
		         
		         while (get_student_info_rs.next()) {
		        	 New e = new New(); 
		        	 e.setCode(get_student_info_rs.getString("course_code"));
		        	 System.out.println(get_student_info_rs.getString("course_code"));
		        	 e.setName(get_student_info_rs.getString("course_name"));
		        	 e.setSem(get_student_info_rs.getString("semester"));
		        	 e.setCurrent(get_student_info_rs.getString("on_going"));
		        	 
		        	 items.add(e);
		     	        
		     	    }
		         stmt.close();
		         conn.close();
		         request.getSession().setAttribute("items",items); 
		        // request.setAttribute("items", items);
				RequestDispatcher view=request.getRequestDispatcher("registeredCourse.jsp");
				view.forward(request,response);
		      } catch(SQLException se) {
		         //Handle errors for JDBC
		         se.printStackTrace();
		      } catch(Exception e) {
		         //Handle errors for Class.forName
		         e.printStackTrace();
		      }
		}
		
		
		
		
		else if (action.equals("allcourse")){ 
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

		         sql = "SELECT course_code, course_name, teacher, semester FROM course_teacher";
		         
		         ResultSet rs=stmt.executeQuery(sql); 
		         
		         ArrayList<New> items = new ArrayList<New>();
		         
		         while (rs.next()) {
		        	 New e = new New(); 
		        	 e.setCode(rs.getString("course_code")); 
		        	 e.setName(rs.getString("course_name"));
		        	 e.setSem(rs.getString("teacher"));
		        	 e.setCurrent(rs.getString("semester"));
		        	 
		        	 items.add(e);
		     	        
		     	    }
		         stmt.close();
		         conn.close();
		         request.getSession().setAttribute("items",items); 
		        // request.setAttribute("items", items);
				RequestDispatcher view=request.getRequestDispatcher("courseInfo.jsp");
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
