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
public class Registrations extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Registrations() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("mail");
        String contact = request.getParameter("Phone");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String url="jdbc:mysql://localhost:3306/cafe";
        String user="root";
        String pass=DB_PASSWORD;
        try {
            // Hash password
            String hashedPassword = hashPassword(password);

            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, pass);
                 PreparedStatement ps = con.prepareStatement("INSERT INTO registeruser(email, contact, password) VALUES (?, ?, ?)")) {

                ps.setString(1, email);
                ps.setString(2, contact);
                ps.setString(3, hashedPassword);

                int i = ps.executeUpdate();
                if (i > 0) {
                    out.print("You are successfully registered...");
                }
            }
        } catch (Exception e) {
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
