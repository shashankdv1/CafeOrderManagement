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
import javax.servlet.http.HttpSession;

@WebServlet("/DisplayOrder")
public class DisplayOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpSession session = request.getSession(false); 
	        String emailcheck = null;

	        if (session != null) {
	            emailcheck = (String) session.getAttribute("email");
	        }

	        if(emailcheck == null) {
	            response.getWriter().println("Session expired or email not set.");
	            return;
	        }
        PrintWriter out = response.getWriter();
        Connect conObj = new Connect();
        Connection con= conObj.createConnection();
        try {
         
           if(con!=null){
                String sql = "SELECT * FROM orders where email=?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                	ps.setString(1, emailcheck);
                    ResultSet rs = ps.executeQuery();
                    ArrayList<Orderdata> od = new ArrayList<>();

                    while (rs.next()) {
                    	String email=rs.getString("email");
                        String itemName = rs.getString("itemname");  
                        float orderPrice = rs.getFloat("totalprice");
                        od.add(new Orderdata(email,itemName,orderPrice));
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
