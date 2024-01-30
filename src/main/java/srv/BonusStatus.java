package srv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BankdetailsDAO;
import DAO.CustomerDAO;
import utility.DBUtil;

@WebServlet("/BonusStatus")
public class BonusStatus extends HttpServlet{

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
            //String Transactiondate = request.getParameter("TransactionDate");
            String TransactionID = request.getParameter("TransactionID");
            String ReferralID = request.getParameter("ReferralID");
            String ApprovedUsername = request.getParameter("username");
            PreparedStatement ps = null;
            PreparedStatement psUpdateBank = null;
            
     
            String openamount = BankdetailsDAO.getUserOpenAmount(userID);
    		
    		Connection conn = DBUtil.provideConnection();
            
	try {
		
		/*psUpdateBank1 = conn.prepareStatement("update withdrawal set status=1 where WithdrawalTransactionID=?");
		psUpdateBank1.setString(1, depositeid);
		int updateResult1 = psUpdateBank1.executeUpdate();
		if (updateResult1 > 0) {*/
        
		
        psUpdateBank = conn.prepareStatement("INSERT INTO transaction (userID ,openamount ,closingamount ,transactiondate,Approvedby ,Transactiontype,status,TransactionID,Amount,ReferralID) VALUES(?,?,?,?,?,'Bonus',1,?,?,?)");
        double tempopen = Double.parseDouble(openamount);
		double tempamoyunt = Double.parseDouble(Amount);
		double closeamot = tempopen +tempamoyunt;
		String Closingamount = String.valueOf(closeamot);
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// You can adjust the format as needed
				 Date currentDate = new Date();
				 String formattedDateTime = dateTimeFormat.format(currentDate);
		
				
        psUpdateBank.setString(1, userID);
        psUpdateBank.setString(2, openamount);
        psUpdateBank.setString(3, Closingamount);
        psUpdateBank.setString(4, formattedDateTime);
        psUpdateBank.setString(5, ApprovedUsername);
        psUpdateBank.setString(6, TransactionID);
        psUpdateBank.setString(7, Amount);
        psUpdateBank.setString(8, ReferralID);
		
		int k1= psUpdateBank.executeUpdate();
		if(k1>0) {
			
			
	
    psUpdateBank = conn.prepareStatement("UPDATE customeraccdetails SET Amount = Amount + ? WHERE userID = ?");
    psUpdateBank.setString(1, Amount); // Assuming Amount is a String
    psUpdateBank.setString(2, userID);

    int updateResult = psUpdateBank.executeUpdate();

    if (updateResult > 0) {
    	status="additing completed";
    	 List<String> userInfo = CustomerDAO.getUserInfoByUsername(userID);
  		String email = userInfo.get(0);
  		String username = userInfo.get(1);
  		
  		BonusApprovalMailLink.sendLinkEmail(email, userID, username);
  		// Store the OTP in the session to verify it later
  		//request.getSession().setAttribute("otp", otp);
  		 request.getSession().setAttribute("email", email);
  		response.sendRedirect("referrals_user.jsp");
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
