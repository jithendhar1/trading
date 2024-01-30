package Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.BankdetailsDAO;
import DAO.WithdrawalDAO;
import utility.DBUtil;

public class WithdrawalServiceImpl {

	
public String addV(String userID,String WithdrawalTransactionID,String Amount) {
		
		String Status1 = " Adding Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;
        PreparedStatement psUpdateBank = null;
     
        int count = WithdrawalDAO.COUNT(userID);
        String UA = WithdrawalDAO.AmountGetting(userID);
		 double UserAmount = Double.parseDouble(UA); 
        double Temp = Double.parseDouble(Amount);
        double temp2 =Double.parseDouble(Amount);
        
         if (count >=1 )
         {
        	Temp =  (0.10 * Temp);
        	temp2=temp2+Temp;
         
         }
        String Readamount = String.valueOf(temp2);
        String openamount = BankdetailsDAO.getUserOpenAmount(userID);
        Connection con1 = DBUtil.provideConnection();
        
        try {
       
        double tempopen = Double.parseDouble(openamount);
		double tempamoyunt = Double.parseDouble(Readamount);
		double closeamot = tempopen - tempamoyunt;
		String Closingamount = String.valueOf(closeamot);
		
		 SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// You can adjust the format as needed
		 Date currentDate = new Date();
		 String formattedDateTime = dateTimeFormat.format(currentDate);
        
        	ps = con1.prepareStatement("INSERT INTO transaction (userID,openamount, closingamount,transactiondate,status,Approvedby,Transactiontype,TransactionID,Amount) VALUES (?,?,?,?,0,'Pending','Withdrawal',?,?)");
        	ps.setString(1, userID);
        	ps.setString(2, openamount);
            ps.setString(3, Closingamount);
            ps.setString(4, formattedDateTime);
            ps.setString(5, WithdrawalTransactionID);
            ps.setString(6, Readamount);
       
            int k = ps.executeUpdate();
            if(k>0) {
        	Status1 = "Updating Successfully!";
            }
        
    }
     catch (SQLException e) {
    	 Status1 = "Error: " + e.getMessage();
        e.printStackTrace();
    } finally {
        DBUtil.closeConnection(con);
        DBUtil.closeConnection(ps);
    }

    return Status1 ;
}

}
