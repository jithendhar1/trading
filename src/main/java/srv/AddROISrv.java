package srv;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Imp.ROIServiceImpl;

@WebServlet("/AddROISrv")
public class AddROISrv extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	
        String status = "Add Failed!";
        String userID = request.getParameter("userID");
        String TransactionID = request.getParameter("TransactionID");
        String Approvedby = request.getParameter("Approvedby");
        String ROIAmount = request.getParameter("ROIAmount");
        String ModifiedDate = request.getParameter("ModifiedDate");
        String OpenAmount = request.getParameter("OpenAmount");
        String ClosingAmount = request.getParameter("ClosingAmount");
        
        ROIServiceImpl add = new ROIServiceImpl();
        status = add.addV(Approvedby,userID, ROIAmount, ModifiedDate, OpenAmount, ClosingAmount,TransactionID);


    	   RequestDispatcher rd = request.getRequestDispatcher("roi_user.jsp?message=" + status);
    rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}








