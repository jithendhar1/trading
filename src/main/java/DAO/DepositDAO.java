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
	                query = "SELECT  DepositID, AccountID, DepositDate, Amount, userID FROM deposit WHERE " + whereClause + " LIMIT ? , ?;";
	               
	            } else {
	                query = "SELECT  DepositID, AccountID, DepositDate, Amount, userID FROM deposit LIMIT ? , ?;";
	            }

	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, start);
	            preparedStatement.setInt(2, limit );
	     
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	DepositBean role = new DepositBean();
	            	
	            role.setDepositID(resultSet.getString("DepositID"));
          	    role.setAccountID(resultSet.getString("AccountID"));
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
		        // Step 1: Get userID from userDB based on the provided username
		        connection = DBUtil.provideConnection();
		        String userQuery = "SELECT userID FROM users WHERE username = ?";
		        userStatement = connection.prepareStatement(userQuery);
		        userStatement.setString(1, username);
		        userResultSet = userStatement.executeQuery();

		        if (userResultSet.next()) {
		            String userID = userResultSet.getString("userID");

		            // Step 2: Get all deposits based on the obtained userID
		            String depositQuery = "SELECT DepositID, AccountID, DepositDate, Amount, userID FROM deposit WHERE userID = ?";
		            depositStatement = connection.prepareStatement(depositQuery);
		            depositStatement.setString(1, userID);
		            depositResultSet = depositStatement.executeQuery();

		            while (depositResultSet.next()) {
		                DepositBean deposit = new DepositBean();
		                deposit.setDepositID(depositResultSet.getString("DepositID"));
		                deposit.setAccountID(depositResultSet.getString("AccountID"));
		                deposit.setDepositDate(depositResultSet.getString("DepositDate"));
		                deposit.setAmount(depositResultSet.getString("Amount"));
		                deposit.setUserID(depositResultSet.getString("userID"));
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
