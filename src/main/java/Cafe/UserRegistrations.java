package Cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.util.Base64;

@WebServlet("/Registrations")
public class UserRegistrations extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("mail");
        String contact = request.getParameter("Phone");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        Connect conObj = new Connect();
        Connection con= conObj.createConnection();
        try {
            // Hash password
            String hashedPassword = hashPassword(password);

            // Database connection
        
           if(con!=null) {
                 PreparedStatement ps = con.prepareStatement("INSERT INTO registeruser(email, contact, password,username) VALUES (?, ?, ?,?)");

                ps.setString(1, email);
                ps.setString(2, contact);
                ps.setString(3, hashedPassword);
                ps.setString(4, username);
                int i = ps.executeUpdate();
                if (i > 0) {
                    out.print("You are successfully registered...");
                    request.setAttribute(username, "username");
                    request.getRequestDispatcher("Render.java").forward(request, response);
                }
                 }
            }
  
        catch (Exception e) {
            e.printStackTrace();
            out.print("Error occurred: " + e.getMessage());
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
