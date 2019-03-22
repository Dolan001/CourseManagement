package schedule;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Assign_course
 */
@WebServlet("/course-assigned")
public class Assign_course extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Assign_course() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseCode = request.getParameter("courseCode");
		String courseName = request.getParameter("courseName");
		String teacherList = request.getParameter("teacherList");
		String semester = request.getParameter("semester");
		String email = request.getParameter("email");
		
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
	         sql = "INSERT INTO course_teacher (course_code, course_name, teacher, semester, email, take_course) VALUES (?, ?, ?, ?, ?, ?)";
	         PreparedStatement preparedStatement = conn.prepareStatement(sql);
	         preparedStatement.setString(1, courseCode);
	         preparedStatement.setString(2, courseName);
	         preparedStatement.setString(3, teacherList);
	         preparedStatement.setString(4, semester);
	         preparedStatement.setString(5, email);
	         preparedStatement.setString(6, "0");
	         
	         preparedStatement.executeUpdate(); 
	         //ResultSet rs = stmt.executeQuery(sql);

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
		
		//RequestDispatcher view=request.getRequestDispatcher("adminHome.jsp");
		//view.forward(request,response);
	    response.sendRedirect("/Scheduler/faculty-home");
	}

}
