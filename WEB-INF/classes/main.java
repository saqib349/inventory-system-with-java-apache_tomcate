import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class main extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sess = req.getSession(false);
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        
        if (sess != null) {
            String usertype = (String) sess.getAttribute("usertype");

            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Main Page</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-image: url('images/others.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center; color: #fff; margin: 0; display: flex; justify-content: center; align-items: center; height: 100vh; }");
            out.println(".container { background: rgba(0, 0, 0, 0.7); padding: 30px; border-radius: 10px; width: 400px; text-align: center; box-shadow: 0 8px 20px rgba(0, 0, 0, 0.5); }");
            out.println("h1 { font-size: 26px; margin-bottom: 20px; color: #00c6ff; }");
            out.println("table { margin: 0 auto; width: 100%; }");
            out.println("table tr td { padding: 10px; text-align: center; }");
            out.println("input[type='button'], input[type='submit'] { width: 100%; padding: 10px; font-size: 16px; font-weight: bold; border: none; border-radius: 5px; cursor: pointer; transition: background-color 0.3s ease; }");
            out.println("input[type='button'] { background-color: #00c6ff; color: #fff; }");
            out.println("input[type='button']:hover { background-color: #009ace; }");
            out.println("input[type='submit'] { background-color: #555; color: #fff; }");
            out.println("input[type='submit']:hover { background-color: #444; }");
            out.println(".logout { margin-top: 20px; }");
            out.println("a { color: #00c6ff; text-decoration: none; }");
            out.println("a:hover { text-decoration: underline; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");

            
            if ("admin".equals(usertype)) {
                out.println("<h1>Welcome to Admin Interface</h1>");
                out.println("<table>");
                out.println("<tr><td><input type='button' value='Add Product' onclick=\"window.location.href='add.html';\"></td></tr>");
                out.println("<tr><td><input type='button' value='Delete Product' onclick=\"window.location.href='delete.html';\"></td></tr>");
                out.println("<tr><td><input type='button' value='Check Availability' onclick=\"window.location.href='checkavailability.html';\"></td></tr>");
                out.println("<tr><td><input type='button' value='Search Product' onclick=\"window.location.href='search.html';\"></td></tr>");
                out.println("<tr class='logout'><form action='logout' method='get'>");
                out.println("<td><input type='submit' value='Logout' name='logout'></td></form></tr>");
                out.println("</table>");
            } else { // For regular users
                out.println("<h1>Welcome to User Interface</h1>");
                out.println("<table>");
                out.println("<tr><td><input type='button' value='Check Availability' onclick=\"window.location.href='checkavailability.html';\"></td></tr>");
                out.println("<tr><td><input type='button' value='Search Product' onclick=\"window.location.href='search.html';\"></td></tr>");
                out.println("<tr><form action='viewall' method='get'>");
                out.println("<td><input type='submit' value='View All Products' name='viewall'></td></form></tr>");
                out.println("<tr><td><input type='button' value='Borrow' onclick=\"window.location.href='borrow.html';\"></td></tr>");
                out.println("<tr><td><input type='button' value='Return' onclick=\"window.location.href='return.html';\"></td></tr>");
                out.println("<tr class='logout'><form action='logout' method='get'>");
                out.println("<td><input type='submit' value='Logout' name='logout'></td></form></tr>");
                out.println("</table>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } else {
            out.println("<html>");
            out.println("<head><title>Access Denied</title></head>");
            out.println("<body style='font-family: Arial; text-align: center; margin-top: 100px;'>");
            out.println("<h1>Access Denied</h1>");
            out.println("<p>You cannot access this page without logging in.</p>");
            out.println("<p>Go to <a href='login.html'>Login</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
        out.close();
    }
}
