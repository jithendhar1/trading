package Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.WithdrawalDAO;
import utility.DBUtil;

public class WithdrawalServiceImpl {

	
public String addV(String userID,String WithdrawalTransactionID,String WithdrawalDate,String Amount) {
		
		String Status1 = " Adding Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;
        PreparedStatement psUpdateBank = null;
     
        int count = WithdrawalDAO.COUNT(userID);
        String UA = WithdrawalDAO.AmountGetting(userID);
        double UserAmount = Double.parseDouble(UA);
        double Temp = Double.parseDouble(Amount);
        try {
         if (count >=1 )
         {
        	Temp =  (0.10 * UserAmount) + Temp;
         
         }
        String Readamount = String.valueOf(Temp);
        Connection con1 = DBUtil.provideConnection();
        	ps = con1.prepareStatement("INSERT INTO withdrawal ( userID, WithdrawalTransactionID, WithdrawalDate, Amount) VALUES (?,?,?,?)");
        	ps.setString(1, userID);
        	ps.setString(2, WithdrawalTransactionID);
            ps.setString(3, WithdrawalDate);
            ps.setString(4, Readamount);
       
            int k = ps.executeUpdate();

            if (k > 0) {
            	 Status1 = " Added Successfully!";

                 // Update the bankdetails table
                 psUpdateBank = con1.prepareStatement("UPDATE bankdetails SET Amount = Amount - ? WHERE userID = ?");
                 psUpdateBank.setString(1, Amount); // Assuming Amount is a String
                 psUpdateBank.setString(2, userID);

                 int k2 = psUpdateBank.executeUpdate();

                 if (k2 <= 0) {
                     Status1 = "Error updating bankdetails";
                 }
             }
            DBUtil.closeConnection(con1);
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

