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
import DAO.DepositDAO;
import utility.DBUtil;

@WebServlet("/DepositeStatus")
public class DepositeStatus extends HttpServlet{

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
		
		 String status ="Adding fail";
  
            String userID = request.getParameter("userID");         
            String Amount = request.getParameter("amount");
            String depositeid = request.getParameter("depositID");
            String ApprovedUsername = request.getParameter("username");
            
            
            PreparedStatement ps = null;
            PreparedStatement psUpdateBank = null;
            PreparedStatement ps1 = null;
            
        //    Connection conn1 = DBUtil.provideConnection();
            
     //       PreparedStatement psUpdateBank1 = null;
            String openamount = BankdetailsDAO.getUserOpenAmount(userID);
            String reffereduserid = DepositDAO.referralID(userID);
	    	String openamount2 = BankdetailsDAO.getUserOpenAmount(reffereduserid);
	    	int count= DepositDAO.totalCountDeposites(userID);
    		Connection conn = DBUtil.provideConnection();
    		
	try {
		
			
		ps1 = conn.prepareStatement("INSERT INTO transaction (userID ,openamount ,closingamount ,transactiondate,Approvedby ,Transactiontype,status,TransactionID,Amount) VALUES(?,?,?,?,?,'Deposit',1,?,?)");
			
		
			double tempopen = Double.parseDouble(openamount);
			double tempamoyunt = Double.parseDouble(Amount);
			double closeamot = tempopen+ tempamoyunt;
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
			ps1.setString(7, Amount);
			
			int k1= ps1.executeUpdate();
			if(k1>0) {
		
        psUpdateBank = conn.prepareStatement("UPDATE customeraccdetails SET Amount = Amount + ? WHERE userID = ?");
        psUpdateBank.setString(1, Amount); // Assuming Amount is a String
        psUpdateBank.setString(2, userID);

        int updateResult = psUpdateBank.executeUpdate();

        if (updateResult > 0) {
            
        //	Connection con1 = DBUtil.provideConnection();
        	double tempamount = Double.parseDouble(Amount);
        	if(tempamount>=500.00)
        	{
        		double tempopen2 = Double.parseDouble(openamount2);
    	
        		String formattedDate2 = dateFormat.format(currentDate);
                
        	    
        	    if(count==1)
        	    {
          		//	Connection con = DBUtil.provideConnection();
        	    	int Tenper = (int) (0.1 * tempamount);
        	    	if (!(Tenper > 500.00)) {
        	    	    Tenper = 500;
        	    	}
        	    	 double closeamot2 = tempopen2 +Tenper;
        	    	 String Closingamount2 = String.valueOf(closeamot2);
        	    	String Tenperamount = String.valueOf(Tenper);
        	    	ps =  conn.prepareStatement("INSERT INTO transaction (userID ,openamount ,closingamount ,transactiondate,Approvedby ,Transactiontype,status,TransactionID,Amount,ReferralID) VALUES(?,?,?,?,'Pending','Bonus',0,?,?,?)");
        	    			
			
                     String randomAccountID = RandomAccountIDGenerator.generateRandomAccountID();
                
        	    	status = " Added Successfully!";
                    ps.setString(1, reffereduserid);
                    ps.setString(2, openamount2);
                    ps.setString(3, Closingamount2);
                    ps.setString(4, formattedDate2);
                    ps.setString(5, randomAccountID);
                    ps.setString(6, Tenperamount);
                    ps.setString(7, userID);
                    
                    
                    int k = ps.executeUpdate();

                    if (k > 0) {
                    	status = " updating bankdetails";
                        /*response.sendRedirect("deposit_user.jsp");*/
                    	
                    	 List<String> userInfo = CustomerDAO.getUserInfoByUsername(reffereduserid);
                  		String email = userInfo.get(0);
                  		String username = userInfo.get(1);
                  		
                  		DepositApprovalMailLink.sendLinkEmail(email, userID, username);
                  		// Store the OTP in the session to verify it later
                  		
                  		 request.getSession().setAttribute("email", email);
                  	
                    }
            		}
        	   
         		 
        	}
        	

          
        }
        
        List<String> userInfo = CustomerDAO.getUserInfoByUsername(userID);
 		String email = userInfo.get(0);
 		String username = userInfo.get(1);
 		
 		DepositApprovalMailLink.sendLinkEmail(email, userID, username);
 	request.getSession().setAttribute("email", email);
 		response.sendRedirect("deposit_user.jsp");
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


