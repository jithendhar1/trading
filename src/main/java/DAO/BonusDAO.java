package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.TransactionBean;
import utility.DBUtil;

public class BonusDAO {

	 public static int BonustotalCount() {
		 int count = 0;
		 Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
		 try {
			 connection = DBUtil.provideConnection();
		   String query = "select count(*) as count from transaction where Transactiontype='Bonus'";
		 ps = connection.prepareStatement(query);
		 rs = ps.executeQuery();
		 while (rs.next()) {
		 count = rs.getInt("count");
		 }
		 } catch (Exception e) {
		 e.printStackTrace();
		 } finally {
		 try {
			 connection.close();
		 } catch (SQLException ex) {
		 ex.printStackTrace();
		 }
		 }
		 return count;
		 }
	 
	 public static List<TransactionBean>getWithdrawalsByUsername(String username) {
		    List<TransactionBean> userWithdrawals = new ArrayList<>();
		    Connection connection = null;
		    PreparedStatement userStatement = null;
		    PreparedStatement depositStatement = null;
		    ResultSet userResultSet = null;
		    ResultSet depositResultSet = null;

		    try {
		        connection = DBUtil.provideConnection();

		        // Step 1: Get userID from userDB based on the provided username
		        if ("Admin".equals(username)) {
		            // If the username is Admin, use a different query
		            String adminQuery = "SELECT  UserID, transactiondate, TransactionID,Amount, ReferralID  ,   max(status) status  FROM trading.transaction where Transactiontype='Bonus' group by userid, Transactiontype, date(transactiondate),amount";
		            depositStatement = connection.prepareStatement(adminQuery);
		        } else {
		            // For other users, get userID from userDB based on the provided username
					
					  String userQuery = "SELECT userID FROM users WHERE username = ?";
					  userStatement = connection.prepareStatement(userQuery);
					  userStatement.setString(1, username);
					 
		            userResultSet = userStatement.executeQuery();

		            if (userResultSet.next()) {
		                String userID = userResultSet.getString("userID");

		                // Get all deposits based on the obtained userID
		                String depositQuery = "SELECT  UserID, transactiondate, TransactionID,Amount, ReferralID  ,   max(status) status  FROM trading.transaction where Transactiontype='Bonus' AND userID = ? group by userid, Transactiontype, date(transactiondate),amount ";
		                depositStatement = connection.prepareStatement(depositQuery);
		                depositStatement.setString(1, userID);
		            }}
		        
		        // Execute the deposit query
		        depositResultSet = depositStatement.executeQuery();

		        while (depositResultSet.next()) {
		        	TransactionBean deposit = new TransactionBean();
		        	 deposit.setUserID(depositResultSet.getString("UserID"));
			            deposit.setTransactiondate(depositResultSet.getString("transactiondate"));
			            deposit.setStatus(depositResultSet.getString("status"));
			            deposit.setTransactionID(depositResultSet.getString("TransactionID"));
			            deposit.setAmount(depositResultSet.getString("Amount"));
			            deposit.setReferralID(depositResultSet.getString("ReferralID"));
		            userWithdrawals.add(deposit);
		        }

		    } catch (Exception e) {
		        // Handle exceptions
		        e.printStackTrace();
		    } finally {
		        // Close database resources
		        try {
		            if (depositResultSet != null) depositResultSet.close();
		            if (depositStatement != null) depositStatement.close();
		            if (userResultSet != null) userResultSet.close();
		            if (userStatement != null) userStatement.close();
		            if (connection != null) connection.close();
		        } catch (Exception e) {
		            // Handle exceptions
		            e.printStackTrace();
		        }
		    }

		    return userWithdrawals;
		}
}
