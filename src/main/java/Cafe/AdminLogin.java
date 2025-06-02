package Cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		 response.setContentType("text/html");
		String email=request.getParameter("email");
	    String password=request.getParameter("password");
	    Connect conObj = new Connect();
        Connection con= conObj.createConnection();
	    try {
	    	int var = 0;
	  
	    	if(con!=null)
	    	{
	    	String sql="SELECT * FROM `Admin` WHERE Email=? AND Password=?";
	    	try(PreparedStatement ps=con.prepareStatement(sql))
			{  
	    		ps.setInt(1, var);
			 ps.setString(1,email);
			 ps.setString(2, password);
			  ResultSet rs = ps.executeQuery();
			  if(rs.next())
			  {
				 
		  request.getRequestDispatcher("/AddItems.jsp").forward(request, response);
             } else {
                 // Login failed
              out.print("<html><Script>alert('Invalid password or email!,Please try again!');</Script></html>");
             }
         }
 } 
	    }catch (Exception e) {
     e.printStackTrace();
     out.print("<h3>Error occurred: " + e.getMessage() + "</h3>");
 } finally {
     out.close();
 }
}  
}