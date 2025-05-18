package Cafe;

import java.sql.Blob;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Render")
public class Render extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Render() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String url = "DB_URL";
        String user = "root";
        String pass = "DB_PASSWORD";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            HttpSession session = request.getSession(false); 
            if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("Login.jsp");
                return;
            }
            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                String sql = "SELECT Item_Id,Item_Name, Item_Description, Item_Image, Item_Price FROM items";
                try (con) {
                	PreparedStatement ps = con.prepareStatement(sql); 
                	ResultSet rs = ps.executeQuery();
            
                    ArrayList<items> data = new ArrayList<>();
                     while(rs.next()) {
                    	int itemId = rs.getInt("Item_Id");
                        String itemName = rs.getString("Item_Name");
                        String itemDescription = rs.getString("Item_Description");
                        Blob itemImage = rs.getBlob("Item_Image");
                        String itemPrice = rs.getString("Item_Price");
                        data.add(new items(itemId,itemName, itemDescription, itemImage, itemPrice));
                      
                    }
                    request.setAttribute("Itemdata", data);
                    request.getRequestDispatcher("/Home.jsp").forward(request, response);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h3>Error occurred: " + e.getMessage() + "</h3>");
        } finally {
            out.close();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); 
    }
}
