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
	        		String sql = "UPDATE registeruser SET username=? WHERE password=?";
	        		try(PreparedStatement ps = con.prepareStatement(sql))
	        		{
	        			ps.setString(1, username);
	        			ps.setString(2, password);
	        			 int rowsUpdated = ps.executeUpdate();
	        			 if(rowsUpdated>0)
	        			 {

                             
							session.setAttribute("message", "Username update was successfully!");
	        			 }
	        			 else {
                             
                             session.setAttribute("message", "Username update failed.");
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
		doGet(request, response);
	}
	private String hashPassword(String password) throws UnsupportedEncodingException {
		
	    MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
        byte[] hash = md.digest(password.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hash);
	}
}
