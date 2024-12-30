import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Logout extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sess = req.getSession(false);
        if (sess != null) {
            sess.invalidate(); 
        }
        res.sendRedirect("login.html"); 
    }
}