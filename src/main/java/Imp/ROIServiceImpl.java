package Imp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utility.DBUtil;

public class ROIServiceImpl {

	
	//TransactionID, userID, ROIAmount, ModifiedDate, OpenAmount, ClosingAmount
	
	public String addV(String Approvedby,String userID,String  ROIAmount,String  ModifiedDate,String  OpenAmount,String  ClosingAmount,String TransactionID) {
		
		String Status1 = " Adding Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;

        try {
        	ps = con.prepareStatement("INSERT INTO transaction (userID,openamount, closingamount,transactiondate,status,Approvedby,Transactiontype,TransactionID,Amount) VALUES (?,?,?,?,1,?,'ROI',?,?)");
        	ps.setString(1, userID);
        	ps.setString(2, OpenAmount);
        	ps.setString(3, ClosingAmount);
        	ps.setString(4, ModifiedDate);
        	ps.setString(5, Approvedby);
        	ps.setString(6, TransactionID);
        	ps.setString(7, ROIAmount);
           
           
            int k = ps.executeUpdate();

            if (k > 0) {
            	 Status1 = "Added Successfully!";

                 // Now update the Amount in bankdetails
                 String updateQuery = "UPDATE customeraccdetails SET Amount = ? WHERE userID = ?";
                 ps = con.prepareStatement(updateQuery);
                 
                 // Assuming ClosingAmount is the amount to be updated in bankdetails
                 ps.setString(1, ClosingAmount);
                 ps.setString(2, userID);

                 int updateResult = ps.executeUpdate();

                 if (updateResult > 0) {
                     Status1 += " Amount in bankdetails updated Successfully!";
                 } else {
                     Status1 += " Failed to update Amount in bankdetails.";
                 }
             }
         } catch (SQLException e) {
             Status1 = "Error: " + e.getMessage();
             e.printStackTrace();
         } finally {
             DBUtil.closeConnection(con);
             DBUtil.closeConnection(ps);
         }

         return Status1;
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
