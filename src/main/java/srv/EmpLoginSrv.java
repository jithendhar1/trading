package srv;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utility.DBUtil;

@WebServlet("/EmpLoginSrv")
public class EmpLoginSrv extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		
	    String alertBeforeDB = "Alert: checking in the database.";
        request.setAttribute("alertBeforeDB", alertBeforeDB);
		
		if (validate(request, username, password)) {
			
			
            String alertAfterDB = "Alert: Your message after checking in the database.";
            request.setAttribute("alertAfterDB", alertAfterDB);
            
            
			response.sendRedirect("admin_dashboard.jsp");
		} else {
			String errorMessage = "Invalid username or password";
			request.setAttribute("error", errorMessage);
			request.setAttribute("wrongusername", "true"); // Set wrongusername attribute
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

	}

	private boolean validate(HttpServletRequest request, String username, String password) {
	    Connection con = DBUtil.provideConnection();
	    PreparedStatement ps = null;

	    try {
	        String sql = "SELECT users.email, users.username, users.userID,users.RoleID"
	                + " FROM users WHERE email=? AND password=?";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, username);
	        ps.setString(2, password);

	        ResultSet result = ps.executeQuery();
	        if (result.next()) {
	            HttpSession session = request.getSession();
	            session.setAttribute("username", result.getString("username"));
	            session.setAttribute("Email", result.getString("email"));
	            session.setAttribute("userID", result.getString("userID"));
	            session.setAttribute("RoleID",result.getString("RoleID"));
	            // Assuming "userID" is the correct column name

	            con.close();
	            return true;
	        }
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("wrongpassword", "true"); // Set wrongpassword attribute
	    } finally {
	        // Ensure that resources are properly closed in case of an exception
	        try {
	            if (ps != null) {
	                ps.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return false;
	}
}
