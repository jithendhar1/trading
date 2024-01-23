<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="DAO.CustomerDAO"%>
    <%
    // Getting the username from the session
    String username = (String)session.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <meta name="description" content="Smarthr- Bootstrap Admin Template">
    <meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
    <meta name="author" content="Dreamguys - Bootstrap Admin Template">
    <meta name="robots" content="noindex, nofollow">
    <title>Leaves - HRMS admin template</title>

    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="assets/favicon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="css/font-awesome.min.css">

    <!-- Lineawesome CSS -->
    <link rel="stylesheet" href="css/line-awesome.min.css">

    <!-- Select2 CSS -->
    <link rel="stylesheet" href="css/select2.min.css">

    <!-- Datetimepicker CSS -->
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">

    <!-- Main CSS -->
    <link rel="stylesheet" href="css/style.css">
    
    <!-- Table styles CSS -->
    <link rel="stylesheet" href="css/styles.css">
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
   
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
   
    <title>Leave List</title>
</head>
<body>


<form action="./AddBankDetailsSrvSrv" method="post">
<div id="addbankdetails" class="modal custom-modal fade" role="dialog">
 <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
      
            <div class="modal-header">
                <h5 class="modal-title">Add BankDetails</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            
            <div class="modal-body">
 
        
        <% String x=  CustomerDAO.getUserIDByUsername( username);
            		
            		
%>
 
        <div class="form-group">
            <label class="col-form-label">userID <span class="text-danger">*</span></label>
            <input name="userID" readonly required class="form-control" type="text" value="<%= x %>">
        </div>

  

    
        <div class="form-group">
            <label class="col-form-label">userName <span class="text-danger">*</span></label>
            <input name="userName" readonly required class="form-control" type="text" value="<%= username %>">
        </div>
  

 
        <div class="form-group">
            <label class="col-form-label">Amount <span class="text-danger">*</span></label>
            <input name="Amount" required class="form-control" type="text">
        </div>


 <div class="submit-section">
    <button type="submit" class="btn btn-primary submit-btn">Submit</button>
  </div>
  
  </div></div>
        </div>
    </div>
</form>
