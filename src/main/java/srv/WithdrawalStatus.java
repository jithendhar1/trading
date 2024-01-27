package srv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BankdetailsDAO;
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
            String ApprovedUsername = request.getParameter("username");
            Connection conn = DBUtil.provideConnection();
            PreparedStatement ps = null;
            PreparedStatement psUpdateBank = null;
            
            Connection conn1 = DBUtil.provideConnection();
            PreparedStatement ps1 = null;
            PreparedStatement psUpdateBank1 = null;
            
            
	try {
		
		/*psUpdateBank1 = conn.prepareStatement("update withdrawal set status=1 where WithdrawalTransactionID=?");
		psUpdateBank1.setString(1, depositeid);
		int updateResult1 = psUpdateBank1.executeUpdate();
		if (updateResult1 > 0) {*/
        
		
        psUpdateBank = conn.prepareStatement("INSERT INTO transaction (userID ,openamount ,closingamount ,transactiondate,status ,Approvedby ,Transactiontype,status,TransactionID) VALUES(?,?,?,?,0,?,deposite,1,?)");
        String openamount = BankdetailsDAO.getUserOpenAmount(userID);
		int tempopen = Integer.parseInt(openamount);
		int tempamoyunt = Integer.parseInt(Amount);
		int closeamot = tempopen - tempamoyunt;
		String Closingamount = String.valueOf(closeamot);
		Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // You can adjust the format as needed
        String formattedDate = dateFormat.format(currentDate);
				
		ps1.setString(1, userID);
		ps1.setString(2, openamount);
		ps1.setString(3, Closingamount);
		ps1.setString(4, formattedDate);
		ps1.setString(5, ApprovedUsername);
		ps1.setString(6, depositeid);
		
		int k1= ps1.executeUpdate();
		if(k1>0) {
			
			
	
    psUpdateBank = conn.prepareStatement("UPDATE customeraccdetails SET Amount = Amount + ? WHERE userID = ?");
    psUpdateBank.setString(1, Amount); // Assuming Amount is a String
    psUpdateBank.setString(2, userID);

    int updateResult = psUpdateBank.executeUpdate();

    if (updateResult > 0) {
    	
}}
		
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
	
