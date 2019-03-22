package schedule;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class New_registration
 */
@WebServlet("/new-registration")
public class New_registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public New_registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseCode = request.getParameter("courseCode");
		String studentName = request.getParameter("studentName");
		String regNo = request.getParameter("regNo");
		String courseName = request.getParameter("courseName");
		String semester = request.getParameter("semester");
		
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
	         sql = "INSERT INTO course_student (reg_no, student_name, course_code, course_name, semester, on_going) VALUES (?, ?, ?, ?, ?, ?)";
	         PreparedStatement preparedStatement = conn.prepareStatement(sql);
	         preparedStatement.setString(1, regNo);
	         preparedStatement.setString(2, studentName);
	         preparedStatement.setString(3, courseCode);
	         preparedStatement.setString(4, courseName);
	         preparedStatement.setString(5, semester);
	         preparedStatement.setString(6, "1");
	         
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
	    response.sendRedirect("/Scheduler/home");
	}

}
