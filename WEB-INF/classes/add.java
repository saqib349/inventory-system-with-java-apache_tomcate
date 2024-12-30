import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class add extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess=req.getSession(false);
		String usertype=(String)sess.getAttribute("usertype");
		res.setContentType("text/html");
        PrintWriter out = res.getWriter();
		if (sess!=null && usertype.equals("admin")){
			
        
		String name=req.getParameter("pname");
		String quantity=req.getParameter("quantity");
		String price=req.getParameter("price");
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            String query = "SELECT quantity,price FROM products WHERE name = ?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString (1,name);
		    ResultSet rst=pst.executeQuery();
			if (rst.next()){
				 String query2 = "UPDATE products SET quantity=?,price=? WHERE name=?";
			     PreparedStatement pst2=con.prepareStatement(query2);
			     pst2.setString (1,quantity);
			     pst2.setString (2,price);
			     pst2.setString (3,name);
		         int result=pst2.executeUpdate();
				 out.println("<html><head><title>updated</title></head><body>");
                 out.println("<h3>successfully updated</h3>");
                 out.println("</body></html>");
				 pst2.close();
				 
			}
			else {
				 String query3 = "INSERT INTO products (name,quantity,price) VALUES(?,?,?)";
			     PreparedStatement pst3=con.prepareStatement(query3);
			     pst3.setString (1,name);
			     pst3.setString (2,quantity);
			     pst3.setString (3,price);
		         int result2=pst3.executeUpdate();
				 out.println("<html><head><title>added</title></head><body>");
                 out.println("<h3>successfully added</h3>");
                 out.println("</body></html>");
				 pst3.close();
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
            out.println("<h3>only admin can access this page.</h3>");
			out.println("<h3>GO TO <a href=\"login.html\">login</a> first.</h3>");
            out.println("</body></html>");
        }
		out.close();
    }
}
	