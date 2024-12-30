import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class checkavailability extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess=req.getSession(false);
		PrintWriter out=res.getWriter();
		if (sess!=null){
			RequestDispatcher rdt=req.getRequestDispatcher("/search");
			rdt.forward(req,res);
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
	