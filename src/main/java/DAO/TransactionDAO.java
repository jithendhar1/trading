package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import beans.TransactionBean;
import utility.DBUtil;

public class TransactionDAO {

	//by transactionType
	public static List<TransactionBean> getTransactionsByTransactionType(String transactionType) {
	    List<TransactionBean> transactions = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBUtil.provideConnection();
	        String query;

	        if ("all".equalsIgnoreCase(transactionType)) {
	            // If transactionType is "all", retrieve all transactions
	            query = "SELECT userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID, Amount FROM transaction;";
	            preparedStatement = connection.prepareStatement(query);
	        } else {
	            // If a specific transactionType is provided, filter by that type
	            query = "SELECT userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID, Amount FROM transaction WHERE Transactiontype = ?;";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, transactionType);
	        }

	        resultSet = preparedStatement.executeQuery();

	        // Iterate through the result set
	        while (resultSet.next()) {
	            TransactionBean transaction = new TransactionBean();
	            transaction.setUserID(resultSet.getString("userID"));
	            transaction.setOpenamount(resultSet.getString("openamount"));
	            transaction.setClosingamount(resultSet.getString("closingamount"));
	            transaction.setTransactiondate(resultSet.getString("transactiondate"));
	            transaction.setStatus(resultSet.getString("status"));
	            transaction.setApprovedby(resultSet.getString("Approvedby"));
	            transaction.setTransactiontype(resultSet.getString("Transactiontype"));
	            transaction.setTransactionID(resultSet.getString("TransactionID"));
	            transaction.setAmount(resultSet.getString("Amount"));
	            // Add the transaction to the list
	            transactions.add(transaction);
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

	    return transactions;
	}

	
	
	//by transactiondate
	public static List<TransactionBean> getTransactionsByTransactiondate(String transactiondate) {
	    List<TransactionBean> transactions = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBUtil.provideConnection();
	        String query;

	        if ("yearly".equalsIgnoreCase(transactiondate)) {
	            query = "SELECT userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID, Amount FROM transaction WHERE transactiondate BETWEEN DATE_SUB(NOW(), INTERVAL 12 MONTH) AND NOW();";
	        } else if ("monthly".equalsIgnoreCase(transactiondate)) {
	            query = "SELECT userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID, Amount FROM transaction WHERE MONTH(transactiondate) = MONTH(NOW()) AND YEAR(transactiondate) = YEAR(NOW());";
	        } else if ("halfyearly".equalsIgnoreCase(transactiondate)) {
	            query = "SELECT userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID, Amount FROM transaction WHERE transactiondate BETWEEN DATE_SUB(NOW(), INTERVAL 6 MONTH) AND NOW();";
	        } else if ("quarterly".equalsIgnoreCase(transactiondate)) {
	            query = "SELECT userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID, Amount FROM transaction WHERE transactiondate BETWEEN DATE_SUB(NOW(), INTERVAL 3 MONTH) AND NOW();";
	        } else {
	            // Default query for a specific date
	            query = "SELECT userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID, Amount FROM transaction WHERE transactiondate = ?;";
	        }

	        // Prepare the statement with the query
	        preparedStatement = connection.prepareStatement(query);

	        // Set the parameter for the prepared statement (only for specific date, not needed for other cases)
	        if (!Arrays.asList("yearly", "monthly", "halfyearly", "quarterly").contains(transactiondate)) {
	            preparedStatement.setString(1, transactiondate);
	        }

	        // Execute the query
	        resultSet = preparedStatement.executeQuery();

	        // Iterate through the result set
	        while (resultSet.next()) {
	        	 TransactionBean transaction = new TransactionBean();
		            transaction.setUserID(resultSet.getString("userID"));
		            transaction.setOpenamount(resultSet.getString("openamount"));
		            transaction.setClosingamount(resultSet.getString("closingamount"));
		            transaction.setTransactiondate(resultSet.getString("transactiondate"));
		            transaction.setStatus(resultSet.getString("status"));
		            transaction.setApprovedby(resultSet.getString("Approvedby"));
		            transaction.setTransactiontype(resultSet.getString("Transactiontype"));
		            transaction.setTransactionID(resultSet.getString("TransactionID"));
		            transaction.setAmount(resultSet.getString("Amount"));
		            // Add the transaction to the list
		            transactions.add(transaction);
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

	    return transactions;
	}

	//userid dropdown
	 public static List<TransactionBean> getAllTransactions() {
	        List<TransactionBean> AllTransactions = new ArrayList<>();
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = DBUtil.provideConnection();
	            String query = "SELECT userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID, Amount FROM transaction;";

	            preparedStatement = connection.prepareStatement(query);
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	
	               
	            	 TransactionBean transaction = new TransactionBean();
			            transaction.setUserID(resultSet.getString("userID"));
			            transaction.setOpenamount(resultSet.getString("openamount"));
			            transaction.setClosingamount(resultSet.getString("closingamount"));
			            transaction.setTransactiondate(resultSet.getString("transactiondate"));
			            transaction.setStatus(resultSet.getString("status"));
			            transaction.setApprovedby(resultSet.getString("Approvedby"));
			            transaction.setTransactiontype(resultSet.getString("Transactiontype"));
			            transaction.setTransactionID(resultSet.getString("TransactionID"));
			            transaction.setAmount(resultSet.getString("Amount"));
			            // Add the transaction to the list
			            AllTransactions.add(transaction);
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

	        return AllTransactions;
	    }
	 
	 
	// by userID
	 public static List<TransactionBean> getTransactionsByUserID(String userID) {
	     List<TransactionBean> transactions = new ArrayList<>();
	     Connection connection = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet = null;

	     try {
	         connection = DBUtil.provideConnection();
	         String query = "SELECT userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID, Amount FROM transaction WHERE userID = ?;";
	         preparedStatement = connection.prepareStatement(query);
	         preparedStatement.setString(1, userID);

	         resultSet = preparedStatement.executeQuery();

	         // Iterate through the result set
	         while (resultSet.next()) {
	             TransactionBean transaction = new TransactionBean();
	             transaction.setUserID(resultSet.getString("userID"));
	             transaction.setOpenamount(resultSet.getString("openamount"));
	             transaction.setClosingamount(resultSet.getString("closingamount"));
	             transaction.setTransactiondate(resultSet.getString("transactiondate"));
	             transaction.setStatus(resultSet.getString("status"));
	             transaction.setApprovedby(resultSet.getString("Approvedby"));
	             transaction.setTransactiontype(resultSet.getString("Transactiontype"));
	             transaction.setTransactionID(resultSet.getString("TransactionID"));
	              transaction.setAmount(resultSet.getString("Amount"));
	             // Add the transaction to the list
	             transactions.add(transaction);
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

	     return transactions;
	 }

	 public static List<TransactionBean> getTransactionsByDate(String transactiondate) {
	     List<TransactionBean> transactions = new ArrayList<>();
	     Connection connection = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet = null;

	     try {
	         connection = DBUtil.provideConnection();
	         String query = "SELECT userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID, Amount FROM transaction WHERE transactiondate = ?;";
	         preparedStatement = connection.prepareStatement(query);
	         preparedStatement.setString(1, transactiondate);

	         resultSet = preparedStatement.executeQuery();

	         // Iterate through the result set
	         while (resultSet.next()) {
	             TransactionBean transaction = new TransactionBean();
	             transaction.setUserID(resultSet.getString("userID"));
	             transaction.setOpenamount(resultSet.getString("openamount"));
	             transaction.setClosingamount(resultSet.getString("closingamount"));
	             transaction.setTransactiondate(resultSet.getString("transactiondate"));
	             transaction.setStatus(resultSet.getString("status"));
	             transaction.setApprovedby(resultSet.getString("Approvedby"));
	             transaction.setTransactiontype(resultSet.getString("Transactiontype"));
	             transaction.setTransactionID(resultSet.getString("TransactionID"));
	              transaction.setAmount(resultSet.getString("Amount"));
	             // Add the transaction to the list
	             transactions.add(transaction);
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

	     return transactions;
	 }
}
