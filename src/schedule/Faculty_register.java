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
 * Servlet implementation class Faculty_register
 */
@WebServlet("/faculty-register")
public class Faculty_register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Faculty_register() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String type = request.getParameter("type");
		
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
	         sql = "INSERT INTO faculty (username, email, password, type) VALUES (?, ?, ?, ?)";
	         PreparedStatement preparedStatement = conn.prepareStatement(sql);
	         preparedStatement.setString(1, username);
	         preparedStatement.setString(2, email);
	         preparedStatement.setString(3, pass);
	         preparedStatement.setString(4, type);
	         
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
		
		RequestDispatcher view=request.getRequestDispatcher("adminLogin.jsp");
		view.forward(request,response);
	}

}
