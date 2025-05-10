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
import com.mysql.cj.jdbc.Blob;
@WebServlet("/DisplayCart")
public class DisplayCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DisplayCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String url="jdbc:mysql://localhost:3306/cafe";
			String user="root";
			String pass="DB_PASSWORD";
		PrintWriter out = response.getWriter();
		try {
		      Class.forName("com.mysql.cj.jdbc.Driver");
		      try(Connection con = DriverManager.getConnection(url,user,pass))
		      {
		    		  String sql="Select Item_id,Item_name,Item_Price from Cart";
		    		  PreparedStatement ps = con.prepareStatement(sql);
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

