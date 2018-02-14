package com.srccodes.example;
// Loading required libraries
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/DatabaseAccess")
public class DatabaseAccess extends HttpServlet{

	
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
   
	   String url = "/result.jsp";
	   
	   Student student = null;
	   
	   List<Student> studentList = new ArrayList<Student>();
	   
      // JDBC driver name and database URL
       final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
       final String DB_URL="jdbc:mysql://localhost/mysql";

      //  Database credentials
       final String USER = "root";
       final String PASS = "Look2find!";

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
     HttpSession session = request.getSession();
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      
      out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor = \"#f0f0f0\">\n" +
         "<h1 align = \"center\">" + title + "</h1>\n");
      try {
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

         // Execute SQL query
         Statement stmt = conn.createStatement();
         String sql;
         sql = "select * from students";
         ResultSet rs = stmt.executeQuery(sql);

         // Extract data from result set
         while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("student_id");
          //  int age = rs.getInt("age");
            String first = rs.getString("student_name");
           // String last = rs.getString("last");

            student = new Student();
            student.setId(id);
            student.setName(first);
            
            studentList.add(student);
            
            //Display values
           //System.out.println("ID: " + id + "<br>");
         //   out.println(", Age: " + age + "<br>");
            //System.out.println(", First: " + first + "<br>");
           // out.println(", Last: " + last + "<br>");
         }
         
         session.setAttribute("resultSet",rs);
         out.println("</body></html>");

         // Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
      } catch(SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch(Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      } finally {
         //finally block used to close resources
//         try {
//            if(stmt!=null)
//               stmt.close();
//         } catch(SQLException se2) {
//         } // nothing we can do
//         try {
//            if(conn!=null)
 //           conn.close();
//         } catch(SQLException se) {
//            se.printStackTrace();
//         } //end finally try
    	  request.setAttribute("students", studentList);
    	  getServletContext()
          .getRequestDispatcher(url)
          .forward(request, response);
      } //end try
   }
} 
