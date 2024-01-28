package srv;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CustomerDAO;
import Imp.DepositServiceImpl;

@WebServlet("/AddDepositSrv")
public class AddDepositSrv extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        String status = "Add Failed!";
        String DepositTransactionID = request.getParameter("DepositTransactionID");
        String Amount = request.getParameter("Amount");
        String userID = request.getParameter("userID");
      
        
        DepositServiceImpl add = new DepositServiceImpl();
        status = add.addV(DepositTransactionID, Amount, userID);

        List<String> userInfo = CustomerDAO.getUserInfoByUsername(userID);
		String email = userInfo.get(0);
		String username = userInfo.get(1);
		
		DepositMailLink.sendLinkEmail(email, userID, username);
		// Store the OTP in the session to verify it later
		//request.getSession().setAttribute("otp", otp);
		 request.getSession().setAttribute("email", email);
		 
		 
    	   RequestDispatcher rd = request.getRequestDispatcher("deposit_user.jsp?message=" + status);
    rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}








