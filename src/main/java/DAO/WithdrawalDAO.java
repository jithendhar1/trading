package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Referrals;
import beans.TransactionBean;
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
       	    role.setAmount(resultSet.getString("Amount"));
      	   
      	    
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
				 String query = "SELECT COUNT(*) AS count FROM (SELECT TransactionID, transactiondate, Amount, userID, MAX(status) AS status " +
			               "FROM trading.transaction " +
			               "WHERE Transactiontype='Withdrawal' " +
			               "GROUP BY userid, DATE(transactiondate), amount) AS test";

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
		        	String adminQuery = "SELECT TransactionID, transactiondate, Amount, userID, MAX(status) AS status"
		        			+ "FROM transaction"
		        			+ "WHERE Transactiontype='Withdrawal'"
		        			+ "GROUP BY userid, Transactiontype, date(transactiondate), amount"
		        			+ "ORDER BY transactiondate DESC, amount";
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
		                String depositQuery = "SELECT TransactionID, transactiondate, Amount, userID, MAX(status) AS status"
		                		+ "FROM transaction"
		                		+ "WHERE Transactiontype='Withdrawal' AND userid=?"
		                		+ "GROUP BY userid, Transactiontype, date(transactiondate), amount"
		                		+ "ORDER BY transactiondate DESC, amount";
		                depositStatement = connection.prepareStatement(depositQuery);
		                depositStatement.setString(1, userID);
		            }
		        }
		        // Execute the deposit query
		        depositResultSet = depositStatement.executeQuery();

		        while (depositResultSet.next()) {
		        	TransactionBean deposit = new TransactionBean();
		        	 deposit.setTransactionID(depositResultSet.getString("TransactionID"));
			            deposit.setTransactiondate(depositResultSet.getString("transactiondate"));
			            deposit.setAmount(depositResultSet.getString("Amount"));
			            deposit.setStatus(depositResultSet.getString("status"));
			            deposit.setUserID(depositResultSet.getString("userID"));
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
		            String countQuery = "SELECT COUNT(*) AS count FROM (SELECT TransactionID, transactiondate, Amount, userID, MAX(status) AS status FROM trading.transaction " +
		                    "WHERE Transactiontype = 'Withdrawal' AND userid = ? GROUP BY userid, date(transactiondate), amount) test";

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

	  public static String AmountGetting(String userID) {
		 
		    Connection connection = null;
		    PreparedStatement userStatement = null;
		    ResultSet userResultSet = null;
          String amount = null;
		    try {
		        connection = DBUtil.provideConnection();
		      
		            // Step 2: Get the total count of withdrawals based on the obtained userID
		            String countQuery = "SELECT Amount FROM customeraccdetails WHERE userID = ?";
		             userStatement = connection.prepareStatement(countQuery);
		             userStatement.setString(1, userID);
		            ResultSet countResultSet = userStatement.executeQuery();
		            // Close countResultSet and countStatement
		            if (countResultSet.next()) {
		                // Get the amount from the result set
		                amount = countResultSet.getString("Amount");
		            }
		            countResultSet.close();
		            userStatement.close();
		        }

		     catch (Exception e) {
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

		    return amount;
		}
	  
	  public static int COUNT(String userID) {
			 int count = 0;
			 Connection connection = null;
		        PreparedStatement ps = null;
		        ResultSet rs = null;
			 try {
				 connection = DBUtil.provideConnection();
			   String query = "select count(*) as count from transaction where userID=? AND Transactiontype='Withdrawal' AND MONTH(transactiondate)=MONTH(CURDATE()) AND status = 1";
			    ps = connection.prepareStatement(query);
	            ps.setString(1, userID);
	             rs = ps.executeQuery();

	            if (rs.next()) {
	                count = rs.getInt("count");
	            }

	            // Close countResultSet and countStatement
	            rs.close();
	            ps.close();
			 } catch (Exception e) {
			        e.printStackTrace();
			    } finally {
			        // Close database resources
			        try {
			            if (rs != null) rs.close();
			            if (rs != null) rs.close();
			            if (connection != null) connection.close();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			    }

			    return count;
			}
}