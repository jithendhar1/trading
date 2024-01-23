package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CustomerBean;
import utility.DBUtil;

public class CustomerDAO {

	
	public static List<CustomerBean> getFilteredCustomers(String whereClause, int start, int limit) {
		List<CustomerBean> FilteredCustomers = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.provideConnection();
			String query;
			if (whereClause != null && !whereClause.isEmpty()) {
				
				query = "SELECT userID, username, email, phno, firstname, lastname from users  WHERE " + whereClause + " LIMIT ?, ?;";

			} else {
				
				query = "SELECT userID, username, email, phno, firstname, lastname from users" + " LIMIT ?, ?;";
			}
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, limit);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				CustomerBean invoice = new CustomerBean();
				invoice.setUserID(resultSet.getString("userID"));
				invoice.setUsername(resultSet.getString("username"));
				invoice.setEmail(resultSet.getString("email"));
				invoice.setPhno(resultSet.getString("phno"));
				invoice.setFirstname(resultSet.getString("firstname"));
				invoice.setLastname(resultSet.getString("lastname"));
				

				FilteredCustomers.add(invoice);
			}
		} catch (SQLException e) {
			// Handle exceptions or log them properly
			e.printStackTrace();
		} finally {
			// closeResources(connection, preparedStatement, resultSet);
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

		return FilteredCustomers;
	}

	
	

	public static int totalCount() {
		int count = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtil.provideConnection();
			String query = "select count(*) as count from users";
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

	public static String getUserIDByUsername(String username) {
	    String userID = null;
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBUtil.provideConnection();
	        String query = "SELECT userID FROM users WHERE username = ?;";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, username);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            userID = resultSet.getString("userID");
	        }
	    } catch (SQLException e) {
	        // Handle exceptions or log them properly
	        e.printStackTrace();
	    } finally {
	        // Close database resources
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            // Handle exceptions
	            e.printStackTrace();
	        }
	    }

	    return userID;
	}

	
}
