package srv;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Imp.DepositServiceImpl;

@WebServlet("/AddDepositSrv")
public class AddDepositSrv extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        String status = "Add Failed!";
        String DepositTransactionID = request.getParameter("DepositTransactionID");
        String DepositDate = request.getParameter("DepositDate");
        String Amount = request.getParameter("Amount");
        String userID = request.getParameter("userID");
      
        
        DepositServiceImpl add = new DepositServiceImpl();
        status = add.addV(DepositTransactionID, DepositDate, Amount, userID);


    	   RequestDispatcher rd = request.getRequestDispatcher("deposit.jsp?message=" + status);
    rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}








