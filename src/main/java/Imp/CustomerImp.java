package Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import utility.DBUtil;

public class CustomerImp {

    public String addUser(String username, String email,
            String password, String phno, String firstname, String lastname) {
    	
		String Status1 = " Adding Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement customerStatement = null;

            try {
          customerStatement = con.prepareStatement(
                        "INSERT INTO users (username, email, password, phno, firstname, lastname) " +
                                "VALUES (?, ?, ?, ?, ?, ?)");
                       
                
                customerStatement.setString(1, username);
                customerStatement.setString(2, email);
                customerStatement.setString(3, password);
                customerStatement.setString(4, phno);
                customerStatement.setString(5, firstname);
                customerStatement.setString(6, lastname);

                int k = customerStatement.executeUpdate();

                if (k > 0) {
                    Status1 = " Added Successfully!";
                }
            } catch (SQLException e) {
                Status1 = "Error: " + e.getMessage();
                e.printStackTrace();
            } finally {
                DBUtil.closeConnection(con);
                DBUtil.closeConnection(customerStatement);
            }

            return Status1;
    	}
}
