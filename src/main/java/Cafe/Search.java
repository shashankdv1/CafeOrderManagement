package Cafe;

import java.io.IOException;
import java.io.PrintWriter;
<<<<<<< HEAD
=======
//import java.net.URLEncoder;
>>>>>>> 5802e278c7dd8859a3975d4d65621f37f6aa7f0b
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		 Connect conObj = new Connect();
	        Connection con= conObj.createConnection();
	
		String value=request.getParameter("searchname");
		//String encodedURL = URLEncoder.encode(value, "UTF-8");
		try {
			
			if(con!=null) {
				
				String sql="SELECT * FROM ITEMS WHERE Item_Name=? OR Item_Description=?";
			
				try(PreparedStatement ps = con.prepareStatement(sql))
				{		
				ArrayList<items> data = new ArrayList<>();
					ps.setString(1, value);
					ps.setString(2, value);
					ResultSet rs = ps.executeQuery();
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
		}
		catch(Exception ex)
		{
			  ex.printStackTrace();
	            out.print("<h3>Error occurred: " + ex.getMessage() + "</h3>");
		}
		finally
		{
			out.close();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
