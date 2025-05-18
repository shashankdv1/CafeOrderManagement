package Cafe;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@MultipartConfig
@WebServlet("/AddtoCart")
public class AddtoCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddtoCart() {
        super();
 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		 response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 String url="DB_URL";
		 String user="DB_USERNAME";
		 String pass="DB_PASSWORD";
		 
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 try(Connection con = DriverManager.getConnection(url,user,pass))
			 {
				 String mail = request.getParameter("email");
				 String id = request.getParameter("itemId");
				 String name = request.getParameter("itemName");
				 String price = request.getParameter("itemPrice");
				String sql = "INSERT INTO Cart (email,Item_id,Item_name,Item_Price) VALUES(?,?,?,?)";
						try(PreparedStatement ps=con.prepareStatement(sql))
						{
							ps.setString(1, mail);
							ps.setString(2,id);
							ps.setString(3,name);			                
							ps.setString(4,price);
							int rowsInserted = ps.executeUpdate();
							if (rowsInserted > 0) {
							out.print("Items Successfully added to Cart");
							}
							 else {
				                    out.print("Nothing to show in cart");
				 }
			            }
					
						 
			 }
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
			 String check=ex.getMessage().substring(0,2);
			 if(check.equals("No"))
			 {
				 out.print("The Item already exists");
			 }
			 else {
	            out.print("<h3>Error occurred: " + ex.getMessage() + "</h3>");
			 }
		 }
		 finally {
			 out.close();
		 }
doGet(request, response);
	
	}
}
