import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class delete extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess=req.getSession(false);
		String usertype=(String)sess.getAttribute("usertype");
		res.setContentType("text/html");
        PrintWriter out = res.getWriter();
		if (sess!=null && usertype.equals("admin")){
			
		String name=req.getParameter("pname");
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            String query = "DELETE FROM products  WHERE name=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString (1,name);
		    int result=pst.executeUpdate();
			if (result>0){
				 out.println("<html><head><title>delete</title></head><body>");
                 out.println("<h3>DELETED SUCCESSFULLY</h3>");
                 out.println("</body></html>");
			}
			else {
				
				 out.println("<html><head><title>error</title></head><body>");
                 out.println("<h3>ITEM NOT FOUND</h3>");
                 out.println("</body></html>");
			}
			con.close();
			pst.close();
            
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
	