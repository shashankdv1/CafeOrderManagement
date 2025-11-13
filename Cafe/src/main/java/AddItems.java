package Cafe;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
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
    	response.setContentType("text/html");
    	String Email=request.getParameter("mail");
        String itemName = request.getParameter("item");
        String itemType = request.getParameter("list");
        String itemDescription = request.getParameter("description");
        String itemPrice = request.getParameter("price");

        // Validate form parameters
        if (itemName == null || itemName.trim().isEmpty()) {
            response.getWriter().print("<h3>Item Name is required!</h3>");
            return;
        }

        InputStream inputStream = null;
        Part filePart = request.getPart("upload_image");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }
        try (PrintWriter out = response.getWriter()) {
            // Set response content type
            response.setContentType("text/html");

         Connect conObj = new Connect();
         Connection con= conObj.createConnection();


               if(con!=null)
               {// Prepare SQL query

                String sql = "INSERT INTO items (Item_Name, Item_Type, Item_Image, Item_Description, Item_Price) VALUES (?,?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, itemName);
                    ps.setString(2, itemType);

                    if (inputStream != null) {
                        ps.setBlob(3, inputStream);
                    } else {
                        ps.setNull(3, java.sql.Types.BLOB);  // Handle missing image gracefully
                    }

                    ps.setString(4, itemDescription);
                    ps.setString(5, itemPrice);

                    // Execute the query
                    int rowsInserted = ps.executeUpdate();
                    out.print("<html><head><script type='text/javascript'>");

                    if (rowsInserted > 0) {
                        out.print("<html><script>alert('Item Added Successfully!!');</script></html>");
                        out.print("window.location='home.jsp';");
                    } else {
                        out.print("<html><script>alert('Failed to add the item. Please try again.');</script></html>");
                        out.print("window.location='addItem.jsp';");
                    }
                    out.print("</script></head><body></body></html>");
                }
                catch(Exception ex)
                {
                	 ex.printStackTrace();
                }
               }

        }
    }
}
