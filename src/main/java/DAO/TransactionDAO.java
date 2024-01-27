package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import beans.TransactionBean;
import utility.DBUtil;

public class TransactionDAO {

	
	public static List<TransactionBean> getTransactionsByTransactiontype(String transactionType) {
	    List<TransactionBean> transactions = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBUtil.provideConnection();
	        String query = "SELECT userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID FROM transaction WHERE Transactiontype = ?;";
	        
	        // Prepare the statement with the query
	        preparedStatement = connection.prepareStatement(query);
	        // Set the parameter for the prepared statement
	        preparedStatement.setString(1, transactionType);
	        
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
	            transaction.setTransactionID(resultSet.getString("TransactionID"));
	            
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
