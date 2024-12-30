import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class login extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		PrintWriter out=res.getWriter();
		String name=req.getParameter("uname");
		String psd=req.getParameter("psd");
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","root");
			String query="SELECT password,usertype FROM login WHERE username=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1,name);
			ResultSet rst=pst.executeQuery();
			if (rst.next()){
				String usertype=rst.getString("usertype");
				String password=rst.getString("password");
				if (password.equals(psd)){
					HttpSession sess=req.getSession(true);
				    sess.setAttribute("usertype",usertype);
					RequestDispatcher rd=req.getRequestDispatcher("/main");
					rd.forward(req,res);
				}
				else {
				     out.println("<html><head><title>login</title></head><body>");
				     out.println("<h3>please try again your pass or username is not correct in next:</h3>");
					 out.println("<h3><a href=\"login.html\">try again</a></h3>");
				     out.println("</body></html>");
				}
			}
			else {
				     out.println("<html><head><title>login</title></head><body>");
				     out.println("<h3>please try again your pass or username is not correct:</h3>");
					 out.println("<h3><a href=\"login.html\">try again</a></h3>");
				     out.println("</body></html>");
			}
			con.close();
		}
		catch(Exception e){
			e.printStackTrace(out);
		}
		out.close();
		
	}
}