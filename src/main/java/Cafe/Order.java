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

@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Order() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		 response.setContentType("text/html");
	        String url="DB_URL";
	        String user="root";
	        String pass="DB_PASSWORD";
	          String itemname=request.getParameter("item_name");
	          String username=request.getParameter("name"); 
	          String totalprice=request.getParameter("Totalprice");
	        try {
	        	 Class.forName("com.mysql.cj.jdbc.Driver");
	        	try(Connection con = DriverManager.getConnection(url,user,pass))
	        	{ String sql="INSERT INTO orders(item,username,order_price) VALUES(?,?,?)";
	        		try(PreparedStatement ps = con.prepareStatement(sql))
	        		{
	        			ps.setString(1, itemname);
	        			ps.setString(2, username);
	        			ps.setString(3, totalprice);
	        			int rowsInserted = ps.executeUpdate();
						if (rowsInserted > 0) {
						out.print("Items Ordered Successfully");
						}
						 else {
			                    out.print("Items Order Process UnSuccessfull");
			 }
	        		}
	        	}
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            out.print("<h3>Error occurred: " + e.getMessage() + "</h3>");
	        } finally {
	            out.close();
	        }
		doGet(request, response);
	}

}
