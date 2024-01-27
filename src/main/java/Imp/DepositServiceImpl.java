package Imp;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.BankdetailsDAO;
import DAO.DepositDAO;
import utility.DBUtil;

public class DepositServiceImpl {

	// DepositID, AccountID, DepositDate, Amount, userID

	public String addV(String DepositTransactionID, String Amount, String userID) {

		String Status1 = " Adding Failed!";

		

		try {
			String openamount = BankdetailsDAO.getUserOpenAmount(userID);
			Connection con = DBUtil.provideConnection();
			PreparedStatement ps = null;
			
			ps = con.prepareStatement(
					"INSERT INTO transaction (userID,openamount, closingamount,transactiondate,status,Approvedby,Transactiontype,TransactionID,Amount) VALUES (?,?,?,?,0,'Pending','Deposit',?,?)");
			double tempopen = Double.parseDouble(openamount);
			int tempamoyunt = Integer.parseInt(Amount);
			double closeamot = tempopen + tempamoyunt;
			String Closingamount = String.valueOf(closeamot);
			 Date currentDate = new Date();
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // You can adjust the format as needed
             String formattedDate = dateFormat.format(currentDate);
			
			ps.setString(1, userID);
			ps.setString(2, openamount);
			ps.setString(3, Closingamount);
			ps.setString(4, formattedDate);
			ps.setString(5, DepositTransactionID);
			ps.setString(6, Amount);
			int k = ps.executeUpdate();

			if (k > 0) {
				
				Status1 ="adding succesfully";
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Handle the exception if needed
		}
		return Status1;
	}
}
