import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class signup extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("uname").trim();
        String psd = req.getParameter("psd").trim();

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            String query = "SELECT password, usertype FROM login WHERE username = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            ResultSet rst = pst.executeQuery();

            if (rst.next()) {
                out.println("<html><head><title>Signup</title></head><body>");
                out.println("<h3>Username "+ name+ "  already exists. Please <a href=\"signup.html\">try again</a> with a unique username.</h3>");
                out.println("</body></html>");
            } 
			else {
                String query2 = "INSERT INTO login (username, password, usertype) VALUES (?,?,?)";
                PreparedStatement pst2 = con.prepareStatement(query2);
                pst2.setString(1, name);
                pst2.setString(2, psd);
                pst2.setString(3, "user");

                int rowsInserted = pst2.executeUpdate();
                if (rowsInserted > 0) {
					res.sendRedirect("login.html");
                } 
				else {
                    out.println("<html><head><title>Error</title></head><body>");
                    out.println("<h3>Signup failed. Please <a href=\"signup.html\">try again</a>.</h3>");
                    out.println("</body></html>");
                }
            }
            rst.close();
            pst.close();
            con.close();
        } 
		catch (Exception e) {
            e.printStackTrace(out);
        }
		out.close();
    }
}
	