package Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utility.DBUtil;

public class RefferalsImpl {

public String AddReferal(String ReferralID,String ReferrerUserID,String ReferredUserID,String ReferralDate) {
		
		String Status1 = " Adding Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;

        try {
        	ps = con.prepareStatement("INSERT INTO referrals_table (ReferralID, ReferrerUserID, ReferredUserID, ReferralDate) VALUES (?,?,?,?)");
        
        	ps.setString(1, ReferralID);
            ps.setString(2, ReferrerUserID);
            ps.setString(3, ReferredUserID);
            ps.setString(4, ReferralDate);
           
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
	
}
