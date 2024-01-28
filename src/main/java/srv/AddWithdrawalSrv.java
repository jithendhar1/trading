
package srv;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CustomerDAO;
import Imp.WithdrawalServiceImpl;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddWithdrawalSrv")
public class AddWithdrawalSrv extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String status="Adding fail";
  
            String userID = request.getParameter("userID");
            String WithdrawalTransactionID = request.getParameter("WithdrawalTransactionID");
           
            String Amount = request.getParameter("Amount");
           

            WithdrawalServiceImpl aaa =new WithdrawalServiceImpl();
       	 status =aaa.addV( userID, WithdrawalTransactionID, Amount);
            
       	List<String> userInfo = CustomerDAO.getUserInfoByUsername(userID);
		String email = userInfo.get(0);
		String username = userInfo.get(1);
		
		WithdrawalMailLink.sendLinkEmail(email, userID, username);
		// Store the OTP in the session to verify it later
		//request.getSession().setAttribute("otp", otp);
		 request.getSession().setAttribute("email", email);
		 
		 
       	   RequestDispatcher rd = request.getRequestDispatcher("withdrawal_user.jsp?message=" + status);
       rd.forward(request, response);
       
    }
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

       doGet(request, response);
       }
       }