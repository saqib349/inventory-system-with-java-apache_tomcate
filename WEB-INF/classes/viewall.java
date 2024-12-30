import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class viewall extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess=req.getSession(false);res.setContentType("text/html");
        PrintWriter out = res.getWriter();
		if (sess!=null){
			
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            String query = "SELECT* FROM products";
			PreparedStatement pst=con.prepareStatement(query);
		    ResultSet rst=pst.executeQuery();
			int count=1;
			     while (rst.next()){
				    String name=rst.getString("name");
					int quantity=rst.getInt("quantity");
					int price=rst.getInt("price");
					out.println("<html><head><title>view all products</title></head><body>");
					out.println("<h3>product #"+count+"  is :</h3>");
					out.println("<br><h4>NAME: "+name+"<br>QUANTITY: "+quantity+"<br>PRICE: "+price+"</h4>");
					
					out.println("</body></html>");
					count++;
				 }	
			
			if (count==1) {
				
				 out.println("<html><head><title>Error</title></head><body>");
                 out.println("<h3>NO ANY ITEM IS THERE</h3>");
                 out.println("</body></html>");
			}
			con.close();
			pst.close();
			rst.close();
            
        }
            		
		    catch (Exception e) {
				e.printStackTrace(out);
			}
			
	   }
	   else {
            out.println("<html><head><title>Access Denied</title></head><body>");
            out.println("<h3>You cannot access this page without logging in.</h3>");
			out.println("<h3>GO TO <a href=\"login.html\">login</a></h3>");
            out.println("</body></html>");
        }
		out.close();
    }
}
	