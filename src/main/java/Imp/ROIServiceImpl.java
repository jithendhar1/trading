package Imp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import utility.DBUtil;

public class ROIServiceImpl {

	
	//TransactionID, userID, ROIAmount, ModifiedDate, OpenAmount, ClosingAmount
	
	public String addV(String Approvedby, String ROIAmount, String ModifiedDate, String OpenAmount, String ClosingAmount, String TransactionID) {
	    String Status1 = "Adding Failed!";
	    Connection con = DBUtil.provideConnection();

	    String countQuery = "SELECT COUNT(*) AS rowCount FROM transaction WHERE Transactiontype ='ROI' AND DATE(TransactionDate) = CURDATE()";

	    try (PreparedStatement countPs = con.prepareStatement(countQuery)) {
	        ResultSet resultSet = countPs.executeQuery();

	        if (resultSet.next()) {
	            int count = resultSet.getInt("rowCount");

	            if (count > 0) {
	                String successMessage = "ROI is already added for today";
	                // response.getWriter().write(successMessage);
	                return successMessage;
	            } else {
	                String insertNewRecordQuery = "INSERT INTO transaction (userID, openamount, closingamount, transactiondate, status, Approvedby, Transactiontype, TransactionID, Amount) " +
	                        "SELECT userid, amount AS openamount, amount + (amount * ?)/100 AS closingamount, ?, 1, ?, 'ROI', ?, ? FROM customeraccdetails";

	                try (PreparedStatement insertPs = con.prepareStatement(insertNewRecordQuery)) {
	                    insertPs.setString(1, ROIAmount);
	                    insertPs.setString(2, ModifiedDate);
	                    insertPs.setString(3, Approvedby);
	                    insertPs.setString(4, TransactionID);
	                    insertPs.setString(5, ROIAmount);

	                    // Execute the INSERT statement
	                    int rowsAffected = insertPs.executeUpdate();

	                    if (rowsAffected > 0) {
	                       Status1="Record inserted successfully";
	                    } else {
	                        System.out.println("Failed to insert the record.");
	                    }

	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	                
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return "{ \"status\": \"" + (Status1.contains("Failed") ? "failure" : "success") + "\", \"message\": \"" + Status1 + "\" }";
	}


	
	
	public String editV(String TransactionID,String userID,String  ROIAmount,String  ModifiedDate,String  OpenAmount,String  ClosingAmount)  {
		
			String Status3 = "Updating  Failed!";

	        Connection con = DBUtil.provideConnection();
	        PreparedStatement ps = null;

	        try {
	        	ps = con.prepareStatement("UPDATE roi SET  userID= ?, ROIAmount= ?, ModifiedDate= ?, OpenAmount= ?, ClosingAmount= ? WHERE TransactionID = ?");
	        	
	        	ps.setString(1, userID);
	        	ps.setString(2, ROIAmount);
	            ps.setString(3, ModifiedDate);
	            ps.setString(4, OpenAmount);
	            ps.setString(5, ClosingAmount);
	            ps.setString(6, TransactionID); 
	           
	            int k = ps.executeUpdate();

	            if (k > 0) {
	            	Status3 = "Updating Successfully!";
	            }
	        }
	         catch (SQLException e) {
	        	Status3 = "Error: " + e.getMessage();
	            e.printStackTrace();
	        } finally {
	            DBUtil.closeConnection(con);
	            DBUtil.closeConnection(ps);
	        }

	        return Status3 ;
	}

	
	public String deleteV(String TransactionID) {
		String Status2 = " delete Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;

        try {
        	ps = con.prepareStatement("DELETE FROM deposit  WHERE TransactionID = ?");
            ps.setString(1, TransactionID);
           
            int k = ps.executeUpdate();

            if (k > 0) {
            	Status2 = " deleted Successfully!";
            }
        } catch (SQLException e) {
        	Status2 = "Error: " + e.getMessage();
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(con);
            DBUtil.closeConnection(ps);
        }

        return Status2;
	}
}
