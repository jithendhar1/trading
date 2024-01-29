package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ROIBean;
import beans.TransactionBean;
import utility.DBUtil;

public class ROIDAO {

	 public static List<ROIBean> getFilteredROI(String whereClause, int start, int limit) {
	        List<ROIBean> FilteredROI = new ArrayList<>();
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	       
	        try {
	            connection = DBUtil.provideConnection();
	            String query;
	            if (whereClause != null && !whereClause.isEmpty()) {
	                query = "SELECT  TransactionID, userID, Amount, ModifiedDate, OpenAmount, ClosingAmount FROM roi WHERE " + whereClause + " LIMIT ? , ?;";
	               
	            } else {
	                query = "SELECT TransactionID, userID, Amount, ModifiedDate, OpenAmount, ClosingAmount FROM roi LIMIT ? , ?;";
	            }

	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, start);
	            preparedStatement.setInt(2, limit );
	     
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	ROIBean role = new ROIBean();
	            	
	        role.setTransactionID(resultSet.getString("TransactionID"));
       	    role.setUserID(resultSet.getString("userID"));
       	    role.setROIAmount(resultSet.getString("Amount"));
       	    role.setModifiedDate(resultSet.getString("ModifiedDate"));
      	    role.setOpenAmount(resultSet.getString("OpenAmount"));
      	  role.setClosingAmount(resultSet.getString("ClosingAmount"));
      	    
      	  FilteredROI.add(role);
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

	        return FilteredROI;
	        
	    } 
	 
	  public static int totalCount() {
			 int count = 0;
			 Connection connection = null;
		        PreparedStatement ps = null;
		        ResultSet rs = null;
			 try {
				 connection = DBUtil.provideConnection();
			   String query = "select count(*) as count from roi";
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
	
	
	  public static String getAmountByuserId(String userID) {
		    String amount = null; // Use lowercase for variable names
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;

		    try {
		        connection = DBUtil.provideConnection();
		        String query = "SELECT Amount FROM customeraccdetails WHERE userID = ?";
		        preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, userID);
		        resultSet = preparedStatement.executeQuery();

		        if (resultSet.next()) {
		            // Assuming Amount is a column of type String in your database
		            amount = resultSet.getString("Amount");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (resultSet != null) resultSet.close();
		            if (preparedStatement != null) preparedStatement.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return amount;
		}

	  public static List<TransactionBean> getROIByUsername(String username) {
		    List<TransactionBean> userROI = new ArrayList<>();
		    Connection connection = null;
		    PreparedStatement userStatement = null;
		    PreparedStatement depositStatement = null;
		    ResultSet userResultSet = null;
		    ResultSet depositResultSet = null;

		    try {
		    	connection = DBUtil.provideConnection();
		    	if ("Admin".equals(username)) {
			            // If the username is Admin, use a different query
			            String adminQuery = "SELECT TransactionID, userID, Amount,transactiondate,OpenAmount,ClosingAmount FROM transaction where Transactiontype='ROI'";
			            depositStatement = connection.prepareStatement(adminQuery);
			        }
		    	 else {
			            // For other users, get userID from userDB based on the provided username
		    		  String userQuery = "SELECT userID FROM users WHERE username = ?";
			            userStatement = connection.prepareStatement(userQuery);
			            userStatement.setString(1, username);
			            userResultSet = userStatement.executeQuery();
			            
			            if (userResultSet.next()) {
			                String userID = userResultSet.getString("userID");
			                // Get all deposits based on the obtained userID
			                String depositQuery = "SELECT TransactionID, userID,Amount, transactiondate, OpenAmount, ClosingAmount FROM transaction where Transactiontype='ROI' AND userID=?";
			                depositStatement = connection.prepareStatement(depositQuery);
			                depositStatement.setString(1, userID);
			            }
		    	         
		               
			        }
		    	 depositResultSet = depositStatement.executeQuery();

			        while (depositResultSet.next()) {
		            	TransactionBean deposit = new TransactionBean();
		                deposit.setTransactionID(depositResultSet.getString("TransactionID"));
		                deposit.setUserID(depositResultSet.getString("userID"));
		                deposit.setAmount(depositResultSet.getString("Amount"));
		                deposit.setTransactiondate(depositResultSet.getString("transactiondate"));
		                deposit.setOpenamount(depositResultSet.getString("OpenAmount"));
		                deposit.setClosingamount(depositResultSet.getString("ClosingAmount"));
		                userROI.add(deposit);
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

		    return userROI;
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
		            String countQuery = "SELECT COUNT(*) AS count FROM transaction WHERE userID = ? AND Transactiontype ='ROI'";
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
	  
}
