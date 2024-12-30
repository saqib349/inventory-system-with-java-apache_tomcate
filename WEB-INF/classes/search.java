import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class search extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess=req.getSession(false);	
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
		if (sess!=null){
		
		String name=req.getParameter("pname");
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            String query = "SELECT quantity,price FROM products WHERE name = ?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString (1,name);
		    ResultSet rst=pst.executeQuery();
			if (rst.next()){
				 int quantity=rst.getInt("quantity");
				 int price=rst.getInt("price");
				 if (quantity==0){
					  out.println("<html><head><title>Error</title></head><body>");
                      out.println("<h3>"+name+" OUT OF STOCK</h3>");
					  out.println("</body></html>");
				 }
				 else {
					 
					  out.println("<html><head><title>search</title></head><body>");
                      out.println("<h3>your product detail is:</h3>");
					  out.println("<h4>NAME: "+name+"<br>QUANTITY: "+quantity+"<br>PRICE: "+price+"</h4>");
					  out.println("</body></html>");
				 }
				
			}
			else {
				
				 out.println("<html><head><title>Error</title></head><body>");
                 out.println("<h3>ITEM NOT FOUND</h3>");
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
	