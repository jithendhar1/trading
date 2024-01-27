package Imp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.DepositDAO;
import utility.DBUtil;

public class DepositServiceImpl {

	// DepositID, AccountID, DepositDate, Amount, userID

	public String addV(String DepositTransactionID, String DepositDate, String Amount, String userID) {

		String Status1 = " Adding Failed!";

		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		PreparedStatement psUpdateBank = null;

		try {
			ps = con.prepareStatement(
					"INSERT INTO deposit (DepositTransactionID, DepositDate, Amount, userID , status) VALUES (?,?,?,?,0)");
			// ps.setString(1, DepositID);
			ps.setString(1, DepositTransactionID);
			ps.setString(2, DepositDate);
			ps.setString(3, Amount);
			ps.setString(4, userID);
			int k = ps.executeUpdate();

			if (k > 0) {
				/*
				 * Connection con1 = DBUtil.provideConnection(); int tempamount =
				 * Integer.parseInt(Amount); if(tempamount>=500) { int count=
				 * DepositDAO.totalCountDeposites(userID); if(count==1) { String reffereduserid
				 * = DepositDAO.referralID(userID); int Tenper = (int) (0.1 * tempamount);
				 * String Tenperamount = String.valueOf(Tenper); ps = con.
				 * prepareStatement("UPDATE bankdetails SET Amount = Amount + ? WHERE userID = ?"
				 * ); Status1 = " Added Successfully!"; ps.setString(1, Tenperamount);
				 * ps.setString(2, reffereduserid);
				 * 
				 * int updateResult = psUpdateBank.executeUpdate();
				 * 
				 * if (updateResult > 0) { Status1 = " updating bankdetails";
				 * response.sendRedirect("deposit_user.jsp"); } } }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Handle the exception if needed
		}
		return Status1;
	}

	public String editV(String DepositID, String DepositTransactionID, String DepositDate, String Amount,
			String userID) {

		String Status3 = "Updating  Failed!";

		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(
					"UPDATE deposit SET  DepositTransactionID= ?, DepositDate= ?, Amount= ?, userID= ? WHERE DepositID = ?");

			ps.setString(1, DepositTransactionID);
			ps.setString(2, DepositDate);
			ps.setString(3, Amount);
			ps.setString(4, userID);
			ps.setString(5, DepositID);

			int k = ps.executeUpdate();

			if (k > 0) {
				Status3 = "Updating Successfully!";
			}
		} catch (SQLException e) {
			Status3 = "Error: " + e.getMessage();
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(con);
			DBUtil.closeConnection(ps);
		}

		return Status3;
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
