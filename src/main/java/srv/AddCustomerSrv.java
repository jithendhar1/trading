package srv;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/AddUser")
public class AddCustomerSrv extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Your code to handle GET requests
		commonLogic(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		commonLogic(request, response);
	}

	private void commonLogic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String status = "Adding fail";
		String userID = request.getParameter("userID");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String AcountNumber = request.getParameter("AcountNumber");
		String phno = request.getParameter("phno");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String ReferrerUserID = request.getParameter("ReferrerUserID");

		Connection con = DBUtil.provideConnection();
		PreparedStatement customerStatement = null;
		PreparedStatement ps = null;

		try {
			if (con != null) {
				// Check if the email already exists in the CustomerAccdetails table
				if (isEmailExists(con, email)) {
					// Email already exists, set a warning attribute and forward to registration.jsp
					request.setAttribute("warningMessage", "Email already exists!");
					request.getRequestDispatcher("registration.jsp").forward(request, response);
				} else {
					customerStatement = con.prepareStatement(
							"INSERT INTO users (userID, username, email, password, phno, firstname, lastname, RoleID, ReferrerUsername) "
									+  "VALUES (?, ?, ?, ?, ?, ?, ?, 2, ?)");

					customerStatement.setString(1, userID);
					customerStatement.setString(2, username);
					customerStatement.setString(3, email);
					customerStatement.setString(4, password);
					customerStatement.setString(5, phno);
					customerStatement.setString(6, firstname);
					customerStatement.setString(7, lastname);
					customerStatement.setString(8, ReferrerUserID);

					int k = customerStatement.executeUpdate();
					Date currentDate = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = dateFormat.format(currentDate);

					formattedDate = userID + "" + formattedDate.replace("-", "");
					if (k > 0) {
						ps = con.prepareStatement(
								"INSERT INTO CustomerAccdetails (userID, userName, Amount, AcountNumber) VALUES (?, ?, 0, ?)");
						status = "Added Successfully!";
						ps.setString(1, userID);
						ps.setString(2, username);
						ps.setString(3, formattedDate);
						int updateResult = ps.executeUpdate();

						if (updateResult > 0) {
							status = "Updating bank details";
							response.sendRedirect("login.jsp");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				// Handle the exception if needed
			}
		}
	}

	private boolean isEmailExists(Connection connection, String email) {
		try {
			String query = "SELECT * FROM users WHERE email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.next(); // Returns true if the email exists
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}