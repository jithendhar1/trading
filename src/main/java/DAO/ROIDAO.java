package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BankdetailsBean;
import beans.ROIBean;
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
	                query = "SELECT  TransactionID, userID, ROIAmount, ModifiedDate, OpenAmount, ClosingAmount FROM roi WHERE " + whereClause + " LIMIT ? , ?;";
	               
	            } else {
	                query = "SELECT TransactionID, userID, ROIAmount, ModifiedDate, OpenAmount, ClosingAmount FROM roi LIMIT ? , ?;";
	            }

	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, start);
	            preparedStatement.setInt(2, limit );
	     
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	ROIBean role = new ROIBean();
	            	
	        role.setTransactionID(resultSet.getString("TransactionID"));
       	    role.setUserID(resultSet.getString("userID"));
       	    role.setROIAmount(resultSet.getString("ROIAmount"));
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
		        String query = "SELECT Amount FROM bankdetails WHERE userID = ?";
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

}
