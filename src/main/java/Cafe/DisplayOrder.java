package Cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import javax.servlet.http.HttpSession;
=======
>>>>>>> 5802e278c7dd8859a3975d4d65621f37f6aa7f0b

@WebServlet("/DisplayOrder")
public class DisplayOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
    	 HttpSession session = request.getSession(false); 
	        String emailcheck = null;

	        if (session != null) {
	            emailcheck = (String) session.getAttribute("email");
	        }

	        if(emailcheck == null) {
	            response.getWriter().println("Session expired or email not set.");
	            return;
	        }
=======
>>>>>>> 5802e278c7dd8859a3975d4d65621f37f6aa7f0b
        PrintWriter out = response.getWriter();
        Connect conObj = new Connect();
        Connection con= conObj.createConnection();
        try {
         
           if(con!=null){
<<<<<<< HEAD
                String sql = "SELECT * FROM orders where email=?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                	ps.setString(1, emailcheck);
=======
                String sql = "SELECT * FROM orders";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
>>>>>>> 5802e278c7dd8859a3975d4d65621f37f6aa7f0b
                    ResultSet rs = ps.executeQuery();
                    ArrayList<Orderdata> od = new ArrayList<>();

                    while (rs.next()) {
<<<<<<< HEAD
                    	String email=rs.getString("email");
                        String itemName = rs.getString("itemname");  
                        float orderPrice = rs.getFloat("totalprice");
                        od.add(new Orderdata(email,itemName,orderPrice));
=======
                    	int orderId = rs.getInt("order_id");
                        String itemId = rs.getString("item");
                        String orderName = rs.getString("username");  
                        float orderPrice = rs.getFloat("order_price");
                        od.add(new Orderdata(orderId,itemId,orderName,orderPrice));
>>>>>>> 5802e278c7dd8859a3975d4d65621f37f6aa7f0b
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
