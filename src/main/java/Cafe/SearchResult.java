package Cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Cafe.searchOperation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchResult")
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	     response.setContentType("text/html; charset=UTF-8");
		try {
		 Connect conObj = new Connect();
		 PrintWriter out = response.getWriter();
		 response.setContentType("text/html");
	    Connection con= conObj.createConnection();
	    if(con!=null)
	    {
	        String searchItem=request.getParameter("searchName");
	    	String sql="select * from items where Item_Name LIKE ?";
	    	
	    	try(PreparedStatement ps=con.prepareStatement(sql))
	    	{
	    		ArrayList<searchOperation> so=new ArrayList<>();
	    		if(searchItem==null) {
	    			 out.print("<html><h1>The corresponding item does not exist</h1></html>");
	    		return;
	    		}
	    		else {
	    				ps.setString(1,"%"+searchItem.toUpperCase()+"%");
	    		}
	    		ResultSet rs = ps.executeQuery();
	    		while(rs.next())
	    		{
	    			String itemName=rs.getString("Item_Name");
	    			String itemType=rs.getString("Item_type");
	    			Blob itemImage = rs.getBlob("Item_Image");
	    			String description = rs.getString("Item_description");
	    			Integer price = rs.getInt("Item_Price");
	    			so.add(new searchOperation(itemName,itemType,itemImage,description,price));
	    		}
	    		if (so.isEmpty()) {
    			    request.setAttribute("message", "No matching items found");
    			}
	    		    request.setAttribute("search_result", so);
	    	        request.getRequestDispatcher("Home.jsp").forward(request, response);
	    		}
	    	catch(Exception ex)
	    	{
	    		out.print("Expection has been occured: "+ex.getMessage());	
	    	}
	    	}
	    }
		  catch(Exception ex)
	    {
			 ex.printStackTrace();
	    }
		//doGet(request, response);
	}
}

