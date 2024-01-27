package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.DepositBean;
import utility.DBUtil;
public class DepositDAO {

	
	 public static List<DepositBean> getFilteredDeposits(String whereClause, int start, int limit) {
	        List<DepositBean> FilteredDeposits = new ArrayList<>();
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	       
	        try {
	            connection = DBUtil.provideConnection();
	            String query;
	            if (whereClause != null && !whereClause.isEmpty()) {
	                query = "SELECT  DepositID, DepositTransactionID, DepositDate, Amount, userID FROM deposit WHERE " + whereClause + " LIMIT ? , ?;";
	               
	            } else {
	                query = "SELECT  DepositID, DepositTransactionID, DepositDate, Amount, userID FROM deposit LIMIT ? , ?;";
	            }

	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, start);
	            preparedStatement.setInt(2, limit );
	     
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	DepositBean role = new DepositBean();
	            	
	            role.setDepositID(resultSet.getString("DepositID"));
          	    role.setDepositTransactionID(resultSet.getString("DepositTransactionID"));
          	    role.setDepositDate(resultSet.getString("DepositDate"));
          	    role.setAmount(resultSet.getString("Amount"));
         	    role.setUserID(resultSet.getString("userID"));
         	    
         	   FilteredDeposits.add(role);
	            }
	        } catch (Exception e) {
	            // Handle exceptions
	            e.printStackTrace();
	        } finally {
	            // Close database resources (connection, statement, result set)
	            try {
	                if (resultSet != null) resultSet.close();
	                if (preparedStatement != null) preparedStatement.close();
	                if (connection != null) connection.close();
	            } catch (Exception e) {
	                // Handle exceptions
	                e.printStackTrace();
	            }
	        }

	        return FilteredDeposits;
	        
	    } 
	 
	  public static int totalCount() {
			 int count = 0;
			 Connection connection = null;
		        PreparedStatement ps = null;
		        ResultSet rs = null;
			 try {
				 connection = DBUtil.provideConnection();
			   String query = "select count(*) as count from deposit";
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
	  
	  
	  public static List<DepositBean> getDepositsByUsername(String username) {
		    List<DepositBean> userDeposits = new ArrayList<>();
		    Connection connection = null;
		    PreparedStatement userStatement = null;
		    PreparedStatement depositStatement = null;
		    ResultSet userResultSet = null;
		    ResultSet depositResultSet = null;

		    try {
		        connection = DBUtil.provideConnection();

		        if ("Admin".equals(username)) {
		            // If the username is Admin, use a different query
		            String adminQuery = "SELECT TransactionID, transactiondate, Amount,status, userID FROM transaction";
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
		                String depositQuery = "SELECT  TransactionID, transactiondate, Amount,status, userID FROM transaction WHERE userID = ?";
		                depositStatement = connection.prepareStatement(depositQuery);
		                depositStatement.setString(1, userID);
		            }
		        }

		        // Execute the deposit query
		        depositResultSet = depositStatement.executeQuery();

		        while (depositResultSet.next()) {
		            DepositBean deposit = new DepositBean();
		           
		            deposit.setDepositTransactionID(depositResultSet.getString("TransactionID"));
		            deposit.setDepositDate(depositResultSet.getString("transactiondate"));
		            deposit.setAmount(depositResultSet.getString("Amount"));
		            deposit.setStatus(depositResultSet.getString("status"));
		            deposit.setUserID(depositResultSet.getString("userID"));
		            userDeposits.add(deposit);
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

		    return userDeposits;
		}


	  public static int totalCountByUsername(String username) {
		    int count = 0;
		    Connection connection = null;
		    PreparedStatement userStatement = null;
		    ResultSet userResultSet = null;

		    try {
		        connection = DBUtil.provideConnection();

		        // Step 1: Get userID from userDB based on the provided username
		        String userQuery = "SELECT userID FROM users WHERE username = ?";
		        userStatement = connection.prepareStatement(userQuery);
		        userStatement.setString(1, username);
		        userResultSet = userStatement.executeQuery();

		        if (userResultSet.next()) {
		            String userID = userResultSet.getString("userID");

		            // Step 2: Get the total count of withdrawals based on the obtained userID
		            String countQuery = "SELECT COUNT(*) AS count FROM deposit WHERE userID = ?";
		            PreparedStatement countStatement = connection.prepareStatement(countQuery);
		            countStatement.setString(1, userID);
		            ResultSet countResultSet = countStatement.executeQuery();

		            if (countResultSet.next()) {
		                count = countResultSet.getInt("count");
		            }

		            // Close countResultSet and countStatement
		            countResultSet.close();
		            countStatement.close();
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        // Close database resources
		        try {
		            if (userResultSet != null) userResultSet.close();
		            if (userStatement != null) userStatement.close();
		            if (connection != null) connection.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

		    return count;
		}
	  
	  public static int totalCountDeposites(String userID) {
			 int count = 0;
			 Connection connection = null;
		        PreparedStatement ps = null;
		        ResultSet rs = null;
			 try {
				 connection = DBUtil.provideConnection();
			   String query = "SELECT COUNT(*) AS count FROM deposit WHERE userID = ?";
			 ps = connection.prepareStatement(query);
			 ps.setString(1, userID);
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
	  
	  public static String referralID(String userID) {
			 String count = null;
			 Connection connection = null;
		        PreparedStatement ps = null;
		        ResultSet rs = null;
			 try {
				 connection = DBUtil.provideConnection();
			   String query = "SELECT ReferrerUsername FROM users WHERE userID = ?";
			 ps = connection.prepareStatement(query);
			 ps.setString(1, userID);
			 rs = ps.executeQuery();
			 while (rs.next()) {
			 count = rs.getString("ReferrerUsername");
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

}
