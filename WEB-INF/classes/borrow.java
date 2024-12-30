import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class borrow extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess=req.getSession(false);
        res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		if (sess!=null){
			
		    String name=req.getParameter("pname");
		    String quant=req.getParameter("quantity");
			int quantity=Integer.parseInt(quant);
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            String query = "SELECT quantity FROM products  WHERE name =?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString (1,name);
		    ResultSet rst=pst.executeQuery();
			if ( rst.next() ){
				int availquantity=rst.getInt("quantity");
				if (availquantity>=quantity){
                    String query2 = "UPDATE products SET quantity=?   WHERE name =?";
					PreparedStatement pst2=con.prepareStatement(query2);
					pst2.setInt (1,availquantity-quantity);
					pst2.setString (2,name);
				    pst2.executeUpdate();
					
				    out.println("<html><head><title>borrow</title></head><body>");
                    out.println("<h3>"+name+"  is SUCCESSFULLY BORROWED</h3>");
                    out.println("</body></html>");
					pst2.close();
				}
				else {
					out.println("<html><head><title>borrow</title></head><body>");
                    out.println("<h3>REQUIRED QUANTITY IS GREATER THAN AVAILABLE</h3>");
                    out.println("</body></html>");
				}
			}
			else{
				out.println("<html><head><title>borrow</title></head><body>");
                out.println("<h3>REQUIRED ITEM IS NOT AVAILABLE</h3>");
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
	