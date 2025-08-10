package Cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Base64;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public changePassword() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String prevpass=request.getParameter("prevpass");
			String newPass = request.getParameter("newpassword");
			 newPass = hashPassword(newPass);
		 	PrintWriter out = response.getWriter();
		 	
	        response.setContentType("text/html");
	        Connect conObj = new Connect();
	        Connection con= conObj.createConnection();
	        try {
	           
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            
	            HttpSession session = request.getSession();

	       
	           
	            if(con!=null)
	            {
	                
	                String sql = "SELECT * FROM registeruser WHERE password=?";
	                try (PreparedStatement ps = con.prepareStatement(sql)) {
	                    String hashedPassword = hashPassword(prevpass);
	                    ps.setString(1, hashedPassword);

	                    try (ResultSet rs = ps.executeQuery()) {
	                        if (rs.next()) {
	                          
	                            String sql2 = "UPDATE registeruser SET password=? WHERE password=?";
	                            try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
	                                ps2.setString(1, newPass);
	                                ps2.setString(2, hashedPassword);

	                                int rowsUpdated = ps2.executeUpdate();
	                                if (rowsUpdated > 0) {
	                                    
	                                    session.setAttribute("message", "Password updated successfully!");
	                                    request.getRequestDispatcher("/Login.jsp").forward(request, response);
	                                } else {
	                                    
	                                    session.setAttribute("message", "Password update failed.");
	                                }
	                            }
	                        } else {
	                           
	                            session.setAttribute("message", "Incorrect current password.");
	                        }
	                    }
	                }
	            }
	         
	         }
	        catch(Exception ex)
	        {
	            ex.printStackTrace();
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
