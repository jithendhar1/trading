package Imp;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utility.DBUtil;

public class DepositServiceImpl {

	
	//DepositID, AccountID, DepositDate, Amount, userID
	
	public String addV(String DepositTransactionID,String DepositDate,String Amount,String userID) {
		
		String Status1 = " Adding Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;

        try {
        	ps = con.prepareStatement("INSERT INTO deposit ( DepositTransactionID, DepositDate, Amount, userID) VALUES (?,?,?,?)");
        	//ps.setString(1, DepositID);
        	ps.setString(1, DepositTransactionID);
            ps.setString(2, DepositDate);
            ps.setString(3, Amount);
            ps.setString(4, userID);
           
           
            int k = ps.executeUpdate();

            if (k > 0) {
                Status1 = " Added Successfully!";
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
	
	
	public String editV(String DepositID,String DepositTransactionID,String DepositDate,String Amount,String userID)  {
		
			String Status3 = "Updating  Failed!";

	        Connection con = DBUtil.provideConnection();
	        PreparedStatement ps = null;

	        try {
	        	ps = con.prepareStatement("UPDATE deposit SET  DepositTransactionID= ?, DepositDate= ?, Amount= ?, userID= ? WHERE DepositID = ?");
	        	
	        	ps.setString(1, DepositTransactionID);
	            ps.setString(2, DepositDate);
	            ps.setString(3, Amount);
	            ps.setString(4, userID);
	            ps.setString(5, DepositID); 
	           
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

	
	public String deleteV(String DepositID) {
		String Status2 = " delete Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;

        try {
        	ps = con.prepareStatement("DELETE FROM deposit  WHERE DepositID = ?");
            ps.setString(1, DepositID);
           
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
