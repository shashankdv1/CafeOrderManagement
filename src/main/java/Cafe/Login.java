package Cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		 response.setContentType("text/html");
	        String url="jdbc:mysql://localhost:3306/cafe";
	        String user="root";
	        String pass="12345@Shark";
		String email=request.getParameter("mail");
	    String password=request.getParameter("password");
	    try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    try(Connection con =DriverManager.getConnection(url,user,pass)) {
	    	String sql="SELECT * FROM registeruser WHERE email=? AND password=?";
	    	try(PreparedStatement ps=con.prepareStatement(sql))
			{
	    		String hashedPassword=hashPassword(password);
			 ps.setString(1,email);
			 ps.setString(2, hashedPassword);
			  ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
                 // Login successful
//                 out.print("<h3>Login successful! Welcome, " + email + "</h3>");
				 request.getRequestDispatcher("Home.jsp").forward(request, response);
                 // Redirect to a welcome page if needed
                 // response.sendRedirect("welcome.jsp");
             } else {
                 // Login failed
              out.print("<html><Script>alert('Invalid password or email!,Please try again! or ');</Script></html>");
              out.print("<html><a href='Register.jsp'>New User?</a></html>");
             }
         }
     }
 } catch (Exception e) {
     e.printStackTrace();
     out.print("<h3>Error occurred: " + e.getMessage() + "</h3>");
 } finally {
     out.close();
 }
}  

	private String hashPassword(String password) throws Exception {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hash = md.digest(password.getBytes("UTF-8"));
	        return Base64.getEncoder().encodeToString(hash);
	    }

}
