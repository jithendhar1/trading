package srv;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Imp.BankDetailsServiceImpl;

@WebServlet("/AddBankDetailsSrv")
public class AddBankDetailsSrv extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
   
        String status = "Add Failed!";
        String userID = request.getParameter("userID");
        String userName = request.getParameter("userName");
        String Amount = request.getParameter("Amount");
       
      
        
        BankDetailsServiceImpl add = new BankDetailsServiceImpl();
        status = add.addV(userID, userName, Amount);


    	   RequestDispatcher rd = request.getRequestDispatcher("bankdetails_user.jsp?message=" + status);
    rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}








