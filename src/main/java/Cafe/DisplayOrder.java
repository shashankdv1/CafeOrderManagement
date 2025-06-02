package Cafe;

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

@WebServlet("/DisplayOrder")
public class DisplayOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connect conObj = new Connect();
        Connection con= conObj.createConnection();
        try {
         
           if(con!=null){
                String sql = "SELECT * FROM orders";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    ArrayList<Orderdata> od = new ArrayList<>();

                    while (rs.next()) {
                    	int orderId = rs.getInt("order_id");
                        String itemId = rs.getString("item");
                        String orderName = rs.getString("username");  
                        float orderPrice = rs.getFloat("order_price");
                        od.add(new Orderdata(orderId,itemId,orderName,orderPrice));
                    }

                    request.setAttribute("Order_data", od);
                    request.getRequestDispatcher("DisplayOrder.jsp").forward(request, response);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            out.print("<h3>Error occurred: " + ex.getMessage() + "</h3>");
        } finally {
            out.close();
        }
    }
}
