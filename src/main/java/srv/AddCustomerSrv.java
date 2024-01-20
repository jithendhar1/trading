package com.weblabs.srv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weblabs.utility.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/AddCustomerSrv")
public class AddCustomerSrv extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commonLogic(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commonLogic(request, response);
    }

    private void commonLogic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String customername = request.getParameter("customername");
            String email = request.getParameter("email");
            String phno = request.getParameter("phno");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");

            String[] street = request.getParameterValues("street");
            String[] city = request.getParameterValues("city");
            String[] postal_code = request.getParameterValues("postal_code");
            String[] state = request.getParameterValues("state");
            String[] hno = request.getParameterValues("hno");

            Connection conn = DBUtil.provideConnection();

            if (conn != null) {
                // Check if the phone number already exists in the customer table
                if (isPhoneNumberExists(conn, phno)) {
                    // Phone number already exists, set a warning attribute and forward to register.jsp
                    request.setAttribute("warningMessage", "Phone number already exists!");
                    request.getRequestDispatcher("registration.jsp").forward(request, response);
                } else {
                    // Continue with the registration process
                    PreparedStatement customerStatement = conn.prepareStatement(
                            "INSERT INTO customer (customername, email, phno, firstname, lastname) " +
                                    "VALUES (?, ?, ?, ?, ?)",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

                    // Set parameters for customerStatement
                    customerStatement.setString(1, customername);
                    customerStatement.setString(2, email);
                    customerStatement.setString(3, phno);
                    customerStatement.setString(4, firstname);
                    customerStatement.setString(5, lastname);

                    int affectedRows = customerStatement.executeUpdate();

                    if (affectedRows > 0) {
                        // Insert address information
                        String addressInsertSQL = "INSERT INTO address (street, city, postal_code, state, hno) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement addressStatement = conn.prepareStatement(addressInsertSQL);

                        addressStatement.setString(1, street[0]);
                        addressStatement.setString(2, city[0]);
                        addressStatement.setString(3, postal_code[0]);
                        addressStatement.setString(4, state[0]);
                        addressStatement.setString(5, hno[0]);

                        addressStatement.execute();
                        addressStatement.close();
                    }

                    conn.close();
                    customerStatement.close();

                    response.sendRedirect("addcustomerprofile.jsp");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
          //  response.sendRedirect("error.jsp");
            response.sendRedirect("loginflutter.jsp");
        }
    }

    private boolean isPhoneNumberExists(Connection connection, String phoneNumber) {
        try {
            String query = "SELECT * FROM customer WHERE phno = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if the phone number exists
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
