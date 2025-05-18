package Cafe;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/AddItems")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) 
public class AddItems extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddItems() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String itemName = request.getParameter("item");
        String itemType = request.getParameter("list");
        String itemDescription = request.getParameter("description");
        String itemPrice = request.getParameter("price");

        InputStream inputStream = null;
        Part filePart = request.getPart("upload_image");

        if (filePart != null) {
            inputStream = filePart.getInputStream(); 
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String url = "DB_URL";
        String user = "DB_USERNAME";
        String pass = "DB_PASSWORD";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

    
            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                String sql = "INSERT INTO items (Item_Name, Item_Type, Item_Image, Item_Description, Item_Price) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, itemName);
                ps.setString(2, itemType);

                if (inputStream != null) {
                    ps.setBlob(3, inputStream);
                } else {
                    ps.setNull(3, java.sql.Types.BLOB); 
                }

                ps.setString(4, itemDescription);
                ps.setString(5, itemPrice);
                	
                
                int rowsInserted = ps.executeUpdate();

                if (rowsInserted > 0) {
                    out.print("<h3>Item added successfully!</h3>");
                    doGet(request,response);
                } else {
                    out.print("<h3>Failed to add the item. Please try again.</h3>");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h3>Error occurred: " + e.getMessage() + "</h3>");
        } finally {
            out.close();
        }
    }
}
