package Cafe;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String email = request.getParameter("mail");
        String password = request.getParameter("password");
        Connect conObj = new Connect();
        Connection con= conObj.createConnection();

        try {
            
         
            HttpSession session = request.getSession();
            
           if(con!=null){

                // SQL query to check the user credentials
                String sql = "SELECT * FROM registeruser WHERE email=? AND password=?";
                String sql2 = "SELECT username FROM registeruser WHERE email=? AND password=?";

                // First PreparedStatement for login authentication
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    // Hash the password to compare it securely
                    String hashedPassword = hashPassword(password);

                    // Set the parameters for the query
                    ps.setString(1, email);
                    ps.setString(2, hashedPassword);

                    // Execute the query
                    ResultSet rs = ps.executeQuery();

                    // If user is found, proceed to fetch username and manage session
                    if (rs.next()) {

                        // Second PreparedStatement to fetch the username based on email and password
                        try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
                            ps2.setString(1, email);
                            ps2.setString(2, hashedPassword);
                            ResultSet rs2 = ps2.executeQuery();

                            // If a username is found, set the session
                            if (rs2.next()) {
                                String username = rs2.getString("username");

                                // Set the username in the request attribute for display
                                request.setAttribute("username", username);

                                session.setAttribute("username", username);
                                session.setAttribute("email", email);

                                // Forward the request to the Render page or another post-login page
                                request.getRequestDispatcher("/Render").forward(request, response);
                            } else {
                                out.print("<html><Script>alert('Error retrieving username.');</Script></html>");
                            }
                        }

                    } else {
                        // If login fails, show error message
                        out.print("<html><Script>alert('Invalid email or password. Please try again.');</Script></html>");
                        out.print("<html><a href='Register.jsp'>New User? Register here.</a></html>");
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

    private String hashPassword(String password) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
	    MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] hash = md.digest(password.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hash);
	}
}