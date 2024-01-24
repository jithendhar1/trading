package srv;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Imp.RefferalsImpl;
@WebServlet("/AddRefferal")
public class AddRefferal extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String status="Adding fail";
  
            String ReferralID = request.getParameter("ReferralID");
            String ReferrerUserID = request.getParameter("ReferrerUserID");
            String ReferredUserID = request.getParameter("ReferredUserID");
            String ReferralDate = request.getParameter("ReferralDate");
            
            RefferalsImpl cisimp =new RefferalsImpl();
       	 status =cisimp.AddReferal(ReferralID, ReferrerUserID, ReferredUserID, ReferralDate);
            

       	   RequestDispatcher rd = request.getRequestDispatcher("referrals_user.jsp?message=" + status);
       rd.forward(request, response);
       
    }
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

       doGet(request, response);
       }
}
