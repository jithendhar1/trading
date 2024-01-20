package com.weblabs.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weblabs.beans.AddressBean;
import com.weblabs.beans.CustomerBean;
import com.weblabs.utility.DBUtil;

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
				
				query = "SELECT customerID, customername, email, phno, firstname, lastname from customer  WHERE " + whereClause + " LIMIT ?, ?;";

			} else {
				
				query = "SELECT customerID, customername, email, phno, firstname, lastname from customer" + " LIMIT ?, ?;";
			}
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, limit);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				CustomerBean invoice = new CustomerBean();
				invoice.setCustomerID(resultSet.getString("customerID"));
				invoice.setCustomername(resultSet.getString("customername"));
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

	
	public static List<AddressBean> getAddressItemsByCustomerId(String CustomerID) {
	    List<AddressBean> invoiceItemsByInvoiceId = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBUtil.provideConnection();
	        String query ="SELECT addressID, customerID, street, city, postal_code, state, hno FROM address WHERE customerID = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, CustomerID);

	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	        	AddressBean invoiceItem = new AddressBean();
	            invoiceItem.setAddressID(resultSet.getString("addressID"));
	            invoiceItem.setCustomerID(resultSet.getString("customerID"));
	            invoiceItem.setStreet(resultSet.getString("street"));
	            invoiceItem.setCity(resultSet.getString("city"));
	            invoiceItem.setPostal_code(resultSet.getString("postal_code"));
	            invoiceItem.setState(resultSet.getString("state"));
	            invoiceItem.setHno(resultSet.getString("hno"));
	            invoiceItemsByInvoiceId.add(invoiceItem);
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (Exception e) {
	            // Handle exceptions
	            e.printStackTrace();
	        }
	    }

	    return invoiceItemsByInvoiceId;
	}


	public static int totalCount() {
		int count = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtil.provideConnection();
			String query = "select count(*) as count from customer";
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

//get customername from id
	
	public static CustomerBean getCustomerById(String customerID) {
		CustomerBean project = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.provideConnection();
            String query = "SELECT customername FROM customer WHERE customerID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customerID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	project = new CustomerBean();
            	project.setCustomername(resultSet.getString("customername"));
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return project;
    }

	
	//for sake of search+retrive list of customers from DB (in dropdown)
	 public static List<CustomerBean> getAllCustomer() {
	        List<CustomerBean> allProjects = new ArrayList<>();
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = DBUtil.provideConnection();
	            String query = "SELECT customerID, customername, email, phno, firstname, lastname FROM customer";
	            preparedStatement = connection.prepareStatement(query);
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	CustomerBean project = new CustomerBean();
	                project.setCustomerID(resultSet.getString("customerID"));
	                project.setCustomername(resultSet.getString("customername"));
	                project.setEmail(resultSet.getString("email"));
	                project.setPhno(resultSet.getString("phno"));
	                project.setFirstname(resultSet.getString("firstname"));
	                project.setLastname(resultSet.getString("lastname"));
	                
	 
	                allProjects.add(project);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (resultSet != null) resultSet.close();
	                if (preparedStatement != null) preparedStatement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return allProjects;
	    }
	  
	 //aravindh retrive data of customers By customerID
	  public static List<CustomerBean> getCustomerProfileByCustomerID(String customerID) {
		    List<CustomerBean> abc = new ArrayList<>();
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;

		    try {
		        connection = DBUtil.provideConnection();
		        String query = "SELECT customerID, customername, email, phno, firstname, lastname FROM customer WHERE customerID = ?;";
		        
		        preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, customerID);
		        resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		        	CustomerBean ccc = new CustomerBean();
		        	ccc.setCustomerID(resultSet.getString("customerID"));
		        	ccc.setCustomername(resultSet.getString("customername"));
		        	ccc.setEmail(resultSet.getString("email"));
		        	ccc.setPhno(resultSet.getString("phno"));
		        	ccc.setFirstname(resultSet.getString("firstname"));
		        	ccc.setLastname(resultSet.getString("lastname"));
		                
		            abc.add(ccc);
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

		    return abc;
		}
}
