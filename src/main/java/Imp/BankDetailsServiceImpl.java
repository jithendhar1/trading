
package Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utility.DBUtil;

public class BankDetailsServiceImpl {

	
	public String addV(String AcountNumber,String BankName,  String userID,String userName,String Amount) {
		
		String Status1 = " Adding Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;

        try {
        	ps = con.prepareStatement("INSERT INTO bankdetails (userID,userName, Amount,AcountNumber,BankName) VALUES (?,?,?,?,?)");
            ps.setString(1, userID);
        	ps.setString(2, userName);
            ps.setString(3, Amount);
            ps.setString(4, AcountNumber);
            ps.setString(5, BankName);
           
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
	
	
	public String editV(String userID,String userName,String Amount)  {
		
			String Status3 = "Updating  Failed!";

	        Connection con = DBUtil.provideConnection();
	        PreparedStatement ps = null;

	        try {
	        	ps = con.prepareStatement("UPDATE bankdetails SET  userName= ?, Amount= ? WHERE userID = ?");
	        	
	        	ps.setString(1, userName);
	            ps.setString(2, Amount);
	            ps.setString(3, userID); 
	           
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

	
	public String deleteV(String userID) {
		String Status2 = " delete Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;

        try {
        	ps = con.prepareStatement("DELETE FROM bankdetails  WHERE userID = ?");
            ps.setString(1, userID);
           
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
