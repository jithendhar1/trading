package srv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.DBUtil;

@WebServlet("/WithdrawalStatus")
public class WithdrawalStatus extends HttpServlet{

	
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
            String Amount = request.getParameter("amount");
            String depositeid = request.getParameter("WithdrawalTransactionID");
            Connection conn = DBUtil.provideConnection();
            PreparedStatement ps = null;
            PreparedStatement psUpdateBank = null;
            
            Connection conn1 = DBUtil.provideConnection();
            PreparedStatement ps1 = null;
            PreparedStatement psUpdateBank1 = null;
            
            
	try {
		
		psUpdateBank1 = conn.prepareStatement("update withdrawal set status=1 where WithdrawalTransactionID=?");
		psUpdateBank1.setString(1, depositeid);
		int updateResult1 = psUpdateBank1.executeUpdate();
		if (updateResult1 > 0) {
        
		
        psUpdateBank = conn.prepareStatement("UPDATE bankdetails SET Amount = Amount - ? WHERE userID = ?");
        psUpdateBank.setString(1, Amount); // Assuming Amount is a String
        psUpdateBank.setString(2, userID);

        int updateResult = psUpdateBank.executeUpdate();

        if (updateResult > 0) {
            status = " updating bankdetails";
            response.sendRedirect("withdrawal_user.jsp");
        }
		}
	}catch (Exception e) {
		e.printStackTrace();
		response.sendRedirect("error.jsp");
	} finally {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Handle the exception if needed
		}
	}
}
}
