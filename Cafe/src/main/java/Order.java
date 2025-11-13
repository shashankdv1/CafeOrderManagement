package Cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Order() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		 response.setContentType("text/html");
		 Connect conObj = new Connect();
	        Connection con= conObj.createConnection();
	        HttpSession session = request.getSession(false); // don't create a new one if none exists
	        String email = null;

	        if (session != null) {
	            email = (String) session.getAttribute("email");
	        }

	        if(email == null) {
	            response.getWriter().println("Session expired or email not set.");
	            return;
	        }

	          String itemname=request.getParameter("item_name");
	          String username=request.getParameter("name");
	          String totalprice=request.getParameter("Totalprice");
	        try {

	        	if(con!=null){
                   String sql="INSERT INTO orders(email,itemname,totalprice) VALUES(?,?,?)";
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
	        		catch(Exception ex)
	        		{
	        			ex.printStackTrace();
	        		}
	        	}
	        	else {
	        		out.print("connection failure");
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
