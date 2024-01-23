package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.CustomerBean;
import beans.Referrals;
import beans.WithdrawalBean;
import srv.AddRefferal;
import utility.DBUtil;

public class ReffertalDAO {

	public static List<CustomerBean> getAllEmployees() {
		List<CustomerBean> allEmployees = new ArrayList<>();
		Connection userID = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = DBUtil.provideConnection();
		try {
			
			String query = "SELECT userID, username, email, phno";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				CustomerBean employee = new CustomerBean();

				employee.setUserID(resultSet.getString("userID"));
				employee.setUsername(resultSet.getString("username"));
				employee.setUsername(resultSet.getString("email"));
				employee.setEmail(resultSet.getString("phno"));
				
				allEmployees.add(employee);
			}
		} catch (Exception e) {
			// Handle exceptions
			e.printStackTrace();
		} finally {
			// Close database resources (connection, statement, result set)
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// Handle exceptions
				e.printStackTrace();
			}
		}

		return allEmployees;
	}
	public static int totalCount() {
		 int count = 0;
		 Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
		 try {
			 connection = DBUtil.provideConnection();
		   String query = "select count(*) as count from referrals";
		 ps = connection.prepareStatement(query);
		 rs = ps.executeQuery();
		 while (rs.next()) {
		 count = rs.getInt("count");
		 }
		 } catch (Exception e) {
		 e.printStackTrace();
		 } finally {
		 try {
			 connection.close();
		 } catch (SQLException ex) {
		 ex.printStackTrace();
		 }
		 }
		 return count;
		 }

	 public static List<Referrals> getFilteredWithdrawals(String whereClause, int start, int limit) {
	        List<Referrals> FilteredWithdrawals = new ArrayList<>();
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	       
	        try {
	            connection = DBUtil.provideConnection();
	            String query;
	            if (whereClause != null && !whereClause.isEmpty()) {
	                query = "SELECT  ReferrerUserID, ReferredUserID, ReferralDate FROM referrals WHERE " + whereClause + " LIMIT ? , ?;";
	               
	            } else {
	                query = "SELECT  ReferrerUserID, ReferredUserID, ReferralDate FROM referrals LIMIT ? , ?;";
	            }

	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, start);
	            preparedStatement.setInt(2, limit );
	     
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	Referrals role = new Referrals();
	            	
	        role.setReferrerUserID(resultSet.getString("ReferrerUserID"));
    	    role.setReferredUserID(resultSet.getString("ReferredUserID"));
    	    role.setReferralDate(resultSet.getString("ReferralDate"));
    	 
   	  FilteredWithdrawals.add(role);
	            }
	        } catch (Exception e) {
	            // Handle exceptions
	            e.printStackTrace();
	        } finally {
	            // Close database resources (connection, statement, result set)
	            try {
	                if (resultSet != null) resultSet.close();
	                if (preparedStatement != null) preparedStatement.close();
	                if (connection != null) connection.close();
	            } catch (Exception e) {
	                // Handle exceptions
	                e.printStackTrace();
	            }
	        }

	        return FilteredWithdrawals;
	        
	    } 
}
