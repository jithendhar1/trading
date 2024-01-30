package srv;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CustomerDAO;
import Imp.CustomerImp;
import utility.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/AddUser")
public class AddCustomerSrv extends HttpServlet {

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
		String status="Adding fail";
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
            Connection conn1 = DBUtil.provideConnection();
            
            try {
                customerStatement = con.prepareStatement(
                              "INSERT INTO users (userID,username, email, password, phno, firstname, lastname,RoleID,ReferrerUsername) " +
                                      "VALUES (?,?, ?, ?, ?, ?, ?,2,?)");
                             
                      customerStatement.setString(1,userID);
                      customerStatement.setString(2, username);
                      customerStatement.setString(3, email);
                      customerStatement.setString(4, password);
                      customerStatement.setString(5, phno);
                      customerStatement.setString(6, firstname);
                      customerStatement.setString(7, lastname);
                      customerStatement.setString(8, ReferrerUserID);

                      int k = customerStatement.executeUpdate();
                      Date currentDate = new Date();
                      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // You can adjust the format as needed
                      String formattedDate = dateFormat.format(currentDate);

                      formattedDate = userID + "_" + formattedDate.replace("-", "_");
                      if (k > 0) {
                      	ps =  con.prepareStatement("INSERT INTO CustomerAccdetails (userID , userName,Amount ,AcountNumber)VALUES(?,?,0,?)");
                      	status = " Added Successfully!";
                          ps.setString(1, userID);
           
                          ps.setString(2, username);
                          ps.setString(3, formattedDate);
                          int updateResult = ps.executeUpdate();

                          if (updateResult > 0) {
                        	  
                        	  status = " updating bankdetails";
                        	  
                              response.sendRedirect("login.jsp");
                          }
                      
                   		RegistrationMailLink.sendLinkEmail(email, userID, username);
                   	request.getSession().setAttribute("email", email);
                  		}
                  	}catch (Exception e) {
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
            
            
					/*
					 * CustomerImp cisimp =new CustomerImp(); status
					 * =cisimp.addUser(userID,username, email, password, phno, firstname,
					 * lastname,ReferrerUserID);
					 * 
					 * 
					 * RequestDispatcher rd = request.getRequestDispatcher("login.jsp?message=" +
					 * status); rd.forward(request, response);
					 */
       
    }
       
       }