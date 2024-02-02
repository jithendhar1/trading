<%@page import="DAO.CustomerDAO"%>
<%@page import="beans.CustomerBean"%>
<%@page import="DAO.ReffertalDAO"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="srv.RandomAccountIDGenerator"%>
<%
HttpSession sessionn = request.getSession();
    // Getting the username from the session
    String username = (String)session.getAttribute("username");
String ReferredUserID = request.getParameter("userid");
List<CustomerBean> deptt = ReffertalDAO.getAllEmployees();

// Check if ReferredUserID has a value
boolean isReferredUser = (ReferredUserID != null && !ReferredUserID.isEmpty());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <meta name="description" content="Smarthr - Bootstrap Admin Template">
    <meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
    <meta name="author" content="Dreamguys - Bootstrap Admin Template">
    <meta name="robots" content="noindex, nofollow">
    <title>Registration</title>

    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="css/font-awesome.min.css">

    <!-- Lineawesome CSS -->
    <link rel="stylesheet" href="css/line-awesome.min.css">

    <!-- Select2 CSS -->
    <link rel="stylesheet" href="css/select2.min.css">

    <!-- Main CSS -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/tstyles.css">

    <!-- Custom CSS -->
    <style>
        body {
            display: flex;
            align-i\tems: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            max-width: 600px; /* Set a maximum width if necessary */
            height: 80vh; /* Adjust the height as needed */
            border: 2px solid black;
            border-radius: 10px;
            background-color: #fff;
            padding: 20px; /* Adjust the padding as needed */
        }
    </style>
</head>
<body >
<div class="main-wrapper">
        <div class="account-content"> 
            <div class="container">
<div class="account-logo">
  <a><img src="assets/WhatsApp Image 2024-01-29 at 19.01.35_802bac3a.jpg" alt="Company Logo"></a>
</div> 


<div  style="width: 1000px;margin-left:320px;" class="row">
    <form action="./AddUser" method="post" class="form-container col-md-6">
        <div style="font-weight: bold;" class="text-center">
            <h2>Registration Form</h2>
        </div>

        <!-- Your form fields go here -->

        <div class="row">
        <div class="col-md-6">
                <div class="form-group">
                    <label>User ID<span class="text-danger">*</span></label>
                    <%
                            String randomAccountID = RandomAccountIDGenerator.generateRandomAccountID();
                        %>
                    <input name="userID" readonly class="form-control" type="text" value="<%= randomAccountID %>">
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label>User Name <span class="text-danger">*</span></label>
                    <input name="username" class="form-control" type="text">
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <label>First Name <span class="text-danger">*</span></label>
                    <input name="firstname" class="form-control" type="text">
                </div>
            </div>
           
           <div class="col-md-6">
                <div class="form-group">
                    <label>Last Name <span class="text-danger">*</span></label>
                    <input name="lastname" class="form-control" type="text">
                </div>
            </div>
 
           <div class="col-md-6">
                <div class="form-group">
                    <label>Email <span class="text-danger">*</span></label>
                    <input name="email" class="form-control" type="text">
                    <font color="red"><%= request.getAttribute("warningMessage") %></font>
                </div>
            </div>
           
           <div class="col-md-6">
                <div class="form-group">
                    <label>Phone Number <span class="text-danger">*</span></label>
                    <input name="phno" class="form-control" type="text">
                </div>
            </div>
            
            <div class="col-md-6">
                <div class="form-group">
                    <label>Password<span class="text-danger">*</span></label>
                    <input name="password" id="password" type="password" class="form-control">
                </div>
            </div>
            
            <div class="col-md-6">
                <div class="form-group">
                    <label>Confirm Password<span class="text-danger">*</span></label>
                    <input name="confirm_pass" id="confirm_pass" type="password" class="form-control">
                </div>
            </div>
            <span id="passwordError" style="color: red;"></span>
            
   <% if (ReferredUserID != null) { %>
    <div class="col-md-6">
        <div class="form-group">
            <label>referral<span class="text-danger">*</span></label>
            
            <select id="selectedEmployee" name="ReferrerUserID" class="form-control">
                <% 
                    List<CustomerBean> dept = ReffertalDAO.getAllEmployees();
                    String Userid = CustomerDAO.getUserIDByUsername(username);
                %>
                <option>Select Name</option>
                <%
                    for (CustomerBean dep : dept) {
                        // If ReferredUserID has a value, auto-select the matching option
                        boolean isSelected = isReferredUser && dep.getUserID().equals(ReferredUserID);
                %>
                        <option value="<%=dep.getUserID()%>" <%= isSelected ? "selected" : "" %>><%= dep.getUsername()%></option>
                <%
                    }
                %>                      
            </select>
        </div>
    </div>
<% } %>

        </div>
        <div class="col-md-6">
            <button style="margin-left: 160px;" type="submit" class="btn btn-success">Register</button>
        </div>
    </form>
</div>
<script>
    // Add an event listener to check password match when the confirm password field changes
    document.getElementById("confirm_pass").addEventListener("input", function () {
        validatePasswordMatch();
    });

    // Function to validate password and confirm password match
    function validatePasswordMatch() {
        var password = document.getElementById("password").value;
        var confirmPass = document.getElementById("confirm_pass").value;
        var errorSpan = document.getElementById("passwordError");

        if (password !== confirmPass) {
            errorSpan.textContent = "Passwords do not match";
        } else {
            errorSpan.textContent = "";
        }
    }
</script>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="js/select2.min.js"></script>
<script src="js/app.js"></script>
</div></div></div>
</body>
</html>
