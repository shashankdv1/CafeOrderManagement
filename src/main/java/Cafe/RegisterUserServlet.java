package Cafe;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/userRegister")
public class RegisterUserServlet  extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	public RegisterUserServlet () {
		// TODO Auto-generated constructor stub
	}
	


	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
	     response.setContentType("text/html");
		 String username=request.getParameter("username");
		 String email=request.getParameter("mail");
		 String contact=request.getParameter("Phone");
		 String password=request.getParameter("password");
		 String confirm=request.getParameter("confirmpassword");
		 
	
		 if(!password.equals(confirm))
		 {
			 out.print("<html><Script>alert('Password and Repeat Password do not match!!');</Script></html>");
			 return; 
		 }
		String hashedpassword=hashPassword(password);
		 Connect conObj = new Connect();
         Connection con= conObj.createConnection();
         if(con!=null)
         {
        	 String sqlCheck="SELECT * FROM registeruser where email=? or username=?";
        	 try(PreparedStatement pscheck=con.prepareStatement(sqlCheck)){
        		 pscheck.setString(1,email);
        		 pscheck.setString(2, username);
        		 ResultSet rscheck=pscheck.executeQuery();
        		 if(rscheck.next())
        		 {
        			 out.print("<html><Script>alert('The provided email or username already exists');</Script></html>");
        		 }
        		 else {
        	 String sql="INSERT INTO registeruser (email,contact,password,username) VALUES (?, ?, ?, ?)";
        	try(PreparedStatement ps = con.prepareStatement(sql))
        	{
        		ps.setString(1, email);
        		ps.setString(2, contact);
        		ps.setString(3, hashedpassword);
        		ps.setString(4, username);
        	int rowsInserted = ps.executeUpdate();
        	if(rowsInserted>0)
        	{
        		request.getRequestDispatcher("/Login").forward(request, response);
        	}
        		
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
                out.print("<h3>Error occurred: " + ex.getMessage() + "</h3>");
        	}
        	 }
        	 }
        	 catch(Exception ex)
        	 {
        		 ex.printStackTrace();
                 out.print("<h3>Error occurred: " + ex.getMessage() + "</h3>");
        	 }finally {
                 out.close();
             }
         }
		 
	 }
	  private String hashPassword(String password) throws UnsupportedEncodingException {
			// TODO Auto-generated method stub
		  try {
	            // Create a MessageDigest instance for SHA-256
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");

	            // Perform the hash computation
	            byte[] encodedhash = digest.digest(password.getBytes());

	            // Convert byte array into a hexadecimal string
	            StringBuilder hexString = new StringBuilder();
	            for (byte b : encodedhash) {
	                String hex = Integer.toHexString(0xff & b);
	                if (hex.length() == 1) {
	                    hexString.append('0');
	                }
	                hexString.append(hex);
	            }
	            return hexString.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	 }
}

