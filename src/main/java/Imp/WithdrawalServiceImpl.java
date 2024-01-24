package Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utility.DBUtil;

public class WithdrawalServiceImpl {

	
public String addV(String  userID,String WithdrawalTransactionID,String WithdrawalDate,String Amount) {
		
		String Status1 = " Adding Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;
        PreparedStatement psUpdateBank = null;

        try {
        	ps = con.prepareStatement("INSERT INTO withdrawal ( userID, WithdrawalTransactionID, WithdrawalDate, Amount) VALUES (?,?,?,?)");
        	ps.setString(1, userID);
        	ps.setString(2, WithdrawalTransactionID);
            ps.setString(3, WithdrawalDate);
            ps.setString(4, Amount);
       
           
           
            int k = ps.executeUpdate();

            if (k > 0) {
            	 Status1 = " Added Successfully!";

                 // Update the bankdetails table
                 psUpdateBank = con.prepareStatement("UPDATE bankdetails SET Amount = Amount - ? WHERE userID = ?");
                 psUpdateBank.setString(1, Amount); // Assuming Amount is a String
                 psUpdateBank.setString(2, userID);

                 int updateResult = psUpdateBank.executeUpdate();

                 if (updateResult <= 0) {
                     Status1 = "Error updating bankdetails";
                 }
             }
         } catch (SQLException e) {
             Status1 = "Error: " + e.getMessage();
             e.printStackTrace();
         } finally {
        	 DBUtil.closeConnection(con);
             DBUtil.closeConnection(ps);
             DBUtil.closeConnection(psUpdateBank);
         }

         return Status1;
     }
	
	
	public String editV(String WithdrawalID , String  userID,String WithdrawalTransactionID,String WithdrawalDate,String Amount)  {
		
			String Status3 = "Updating  Failed!";

	        Connection con = DBUtil.provideConnection();
	        PreparedStatement ps = null;

	        try {
	        	ps = con.prepareStatement("UPDATE withdrawal SET   userID= ?, WithdrawalTransactionID= ?, WithdrawalDate= ?, Amount= ? WHERE WithdrawalID = ?");
	        	
	        	ps.setString(1, userID);
	        	ps.setString(2, WithdrawalTransactionID);
	            ps.setString(3, WithdrawalDate);
	            ps.setString(4, Amount);
	            ps.setString(5, WithdrawalID);
	           
	           
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
        	ps = con.prepareStatement("DELETE FROM withdrawal  WHERE TransactionID = ?");
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

