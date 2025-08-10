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
@WebServlet("/DisplayCart")
public class DisplayCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DisplayCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  Connect conObj = new Connect();
	        Connection con= conObj.createConnection();
		PrintWriter out = response.getWriter();
		try {
			HttpSession session = request.getSession(false); 
	        String emailcheck = null;

	        if (session != null) {
	            emailcheck = (String) session.getAttribute("email");
	        }

	        if(emailcheck == null) {
	            response.getWriter().println("Session expired or email not set.");
	            return;
	        }
		     if(con!=null)
		      {
		    	 
		    		  String sql="Select Item_id,Item_name,Item_Price from Cart where email=?";
		    		  PreparedStatement ps = con.prepareStatement(sql);
		    		  ps.setString(1, emailcheck);
		    		  ResultSet rs = ps.executeQuery();
		    		  ArrayList<AddItemsToCart> Cartdata = new ArrayList<>();
		    		  
		    		  while(rs.next())
		    		  {
		    			  String Item_Id=rs.getString("Item_Id");
		    			  String Item_Name=rs.getString("Item_Name");
		    			  String Item_price=rs.getString("Item_price");  
			              Cartdata.add(new AddItemsToCart(Item_Id,Item_Name,Item_price));
			                	
			                }
		    		  request.setAttribute("Cart_data", Cartdata);
	                    request.getRequestDispatcher("DisplayCart.jsp").forward(request, response);  
		    		  }
		    		 
		                
		    	  }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
	            out.print("<h3>Error occurred: " + ex.getMessage() + "</h3>");
		 }
		 finally {
			 out.close();
		 }
	}
	}

