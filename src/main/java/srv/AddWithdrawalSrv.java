
package srv;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Imp.WithdrawalServiceImpl;
import java.io.IOException;

@WebServlet("/AddWithdrawalSrv")
public class AddWithdrawalSrv extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String status="Adding fail";
  
            String userID = request.getParameter("userID");
            String AccountID = request.getParameter("AccountID");
            String WithdrawalDate = request.getParameter("WithdrawalDate");
            String Amount = request.getParameter("Amount");
           

            WithdrawalServiceImpl aaa =new WithdrawalServiceImpl();
       	 status =aaa.addV( userID, AccountID, WithdrawalDate, Amount);
            

       	   RequestDispatcher rd = request.getRequestDispatcher("login.jsp?message=" + status);
       rd.forward(request, response);
       
    }
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

       doGet(request, response);
       }
       }