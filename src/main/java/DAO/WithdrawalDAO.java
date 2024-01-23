package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.WithdrawalBean;
import utility.DBUtil;

public class WithdrawalDAO {
	//WithdrawalID, userID, AccountID, WithdrawalDate, Amount
	 public static List<WithdrawalBean> getFilteredWithdrawals(String whereClause, int start, int limit) {
	        List<WithdrawalBean> FilteredWithdrawals = new ArrayList<>();
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	       
	        try {
	            connection = DBUtil.provideConnection();
	            String query;
	            if (whereClause != null && !whereClause.isEmpty()) {
	                query = "SELECT  WithdrawalID, userID, WithdrawalTransactionID, WithdrawalDate, Amount FROM withdrawal WHERE " + whereClause + " LIMIT ? , ?;";
	               
	            } else {
	                query = "SELECT  WithdrawalID, userID, WithdrawalTransactionID, WithdrawalDate, Amount FROM withdrawal LIMIT ? , ?;";
	            }

	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, start);
	            preparedStatement.setInt(2, limit );
	     
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	WithdrawalBean role = new WithdrawalBean();
	            	
	            role.setWithdrawalID(resultSet.getString("WithdrawalID"));
       	    role.setUserID(resultSet.getString("userID"));
       	    role.setWithdrawalTransactionID(resultSet.getString("WithdrawalTransactionID"));
       	    role.setWithdrawalDate(resultSet.getString("WithdrawalDate"));
      	   
      	    
      	  FilteredWithdrawals.add(role);
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

	        return FilteredWithdrawals;
	        
	    } 
	 
	  public static int totalCount() {
			 int count = 0;
			 Connection connection = null;
		        PreparedStatement ps = null;
		        ResultSet rs = null;
			 try {
				 connection = DBUtil.provideConnection();
			   String query = "select count(*) as count from withdrawal";
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
	
	  public static List<WithdrawalBean> getDepositsByUsername(String username) {
		    List<WithdrawalBean> userDeposits = new ArrayList<>();
		    Connection connection = null;
		    PreparedStatement userStatement = null;
		    PreparedStatement depositStatement = null;
		    ResultSet userResultSet = null;
		    ResultSet depositResultSet = null;

		    try {
		        // Step 1: Get userID from userDB based on the provided username
		        connection = DBUtil.provideConnection();
		        String userQuery = "SELECT userID FROM users WHERE username = ?";
		        userStatement = connection.prepareStatement(userQuery);
		        userStatement.setString(1, username);
		        userResultSet = userStatement.executeQuery();

		        if (userResultSet.next()) {
		            String userID = userResultSet.getString("userID");

		            // Step 2: Get all deposits based on the obtained userID
		            String depositQuery = "SELECT  WithdrawalID, userID, WithdrawalTransactionID, WithdrawalDate, Amount FROM withdrawal WHERE userID = ?";
		            depositStatement = connection.prepareStatement(depositQuery);
		            depositStatement.setString(1, userID);
		            depositResultSet = depositStatement.executeQuery();

		            while (depositResultSet.next()) {
		            	WithdrawalBean deposit = new WithdrawalBean();
		            	
		                deposit.setWithdrawalID(depositResultSet.getString("WithdrawalID"));
		                deposit.setUserID(depositResultSet.getString("userID"));
		                deposit.setWithdrawalTransactionID(depositResultSet.getString("WithdrawalTransactionID"));
		                deposit.setWithdrawalDate(depositResultSet.getString("WithdrawalDate"));
		                deposit.setAmount(depositResultSet.getString("Amount"));
		                userDeposits.add(deposit);
		            }
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
}
