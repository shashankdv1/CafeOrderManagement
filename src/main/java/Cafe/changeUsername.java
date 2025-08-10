package Cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class changeUsername
 */
@WebServlet("/changeUsername")
public class changeUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public changeUsername() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String email=request.getParameter("email");
			String username = request.getParameter("name");
		    String password = request.getParameter("Password");
		    password=hashPassword(password);
			PrintWriter out= response.getWriter();
	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	  HttpSession session = request.getSession();
	        	
	        	  Connect conObj = new Connect();
	  	        Connection con= conObj.createConnection();
	        	 if(con!=null) {
	        		String sql = "UPDATE registeruser SET username=? WHERE password=? and email=?";
	        		try(PreparedStatement ps = con.prepareStatement(sql))
	        		{
	        			ps.setString(1, username);
	        			ps.setString(2, password);
	        			ps.setString(3, email);        			 
	        			int rowsUpdated = ps.executeUpdate();
	        			 if(rowsUpdated>0)
	        			 {
							response.sendRedirect("Profile.jsp");
							 out.print("<html><script>alert(`Update was unsuccessfull`);</script></html>");
	        			 }
	        			 else {
                             
                             out.print("<html>Update was unsuccessfull</html>");
                         }
	        		}
	        	 }
	        	}
	        	  
	        catch(Exception e) {
	        	e.printStackTrace();
	            out.print("<h3>Error occurred: " + e.getMessage() + "</h3>");
	        }
	        finally {
	            out.close();
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
