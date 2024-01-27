package Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utility.DBUtil;

public class CustomerImp {

    public String addUser(String userID,String username, String email,
            String password, String phno, String firstname, String lastname,String ReferrerUserID) {
    	
		String Status1 = " Adding Failed!";

        Connection con = DBUtil.provideConnection();
        PreparedStatement customerStatement = null;
        PreparedStatement ps = null;
        Connection conn1 = DBUtil.provideConnection();

            try {
          customerStatement = con.prepareStatement(
                        "INSERT INTO users (userID,username, email, password, phno, firstname, lastname,RoleID,ReferrerUsername) " +
                                "VALUES (?,?, ?, ?, ?, ?, ?,2,?)");
                       
                customerStatement.setString(1,userID);
                customerStatement.setString(2, username);
                customerStatement.setString(3, email);
                customerStatement.setString(4, password);
                customerStatement.setString(5, phno);
                customerStatement.setString(6, firstname);
                customerStatement.setString(7, lastname);
                customerStatement.setString(8, ReferrerUserID);

                int k = customerStatement.executeUpdate();
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // You can adjust the format as needed
                String formattedDate = dateFormat.format(currentDate);

                if (k > 0) {
                	ps =  con.prepareStatement("INSERT INTO bankdetails (userID , userName,Amount ,AcountNumber)VALUES(?,?,0,?)");
                    Status1 = " Added Successfully!";
                    ps.setString(1, userID);
                    ps.setString(2, username);
                    ps.setString(3, formattedDate);
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
