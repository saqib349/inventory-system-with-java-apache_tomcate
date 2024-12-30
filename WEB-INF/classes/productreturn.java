import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class productreturn extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess=req.getSession(false);
		PrintWriter out = res.getWriter();
		if (sess!=null){
			
        res.setContentType("text/html");
		String name=req.getParameter("pname");
		String quan=req.getParameter("quantity");
		String day=req.getParameter("days");
		int quant=Integer.parseInt(quan);
		int days=Integer.parseInt(day);
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            String query = "SELECT quantity,price FROM products WHERE name =?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1,name);
		    ResultSet rst=pst.executeQuery();
			if (rst.next()){
				 int quantity=rst.getInt("quantity");
				 int price=rst.getInt("price");
                  String query2 = "UPDATE products SET quantity=? WHERE name =?";
			      PreparedStatement pst2=con.prepareStatement(query2);
			      pst2.setInt (1,quantity+quant);
			      pst2.setString (2,name);
				  int result=pst2.executeUpdate();
				 
				 out.println("<html><head><title>returning</title></head><body>");
                 out.println("<h3>SUCCESSFULLY RETURNING:</h3><br>");
                 out.println("<h4>TOTAL BILL: "+(quant*days*price)+"</h3><br>");
                 out.println("<h3>THANK YOU FOR USING THIS PLATFORM:</h3><br>");
                 out.println("</body></html>");
				 
				 
				 pst2.close();

			}
			else {
				
				 out.println("<html><head><title>Error</title></head><body>");
                 out.println("<h3>PLEASE ENTER THE RIGHT ITEM NAME</h3>");
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
	