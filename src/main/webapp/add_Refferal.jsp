<%@page import="DAO.ReffertalDAO"%>
<%@page import="beans.CustomerBean"%>
<%@ page import="java.util.List" %>
<%@page import="srv.RandomAccountIDGenerator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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


<form action="./AddRefferal" method="post">
<div id="addrefferal" class="modal custom-modal fade" role="dialog">
 <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
        
            <div class="modal-header">
                <h5 class="modal-title">Add Refferal</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            
            <div class="modal-body">
 
        <!-- <div class="form-group">
            <label for="AccountID">Account ID <span class="text-danger">*</span></label>
            <input id="AccountID" name="AccountID" class="form-control" type="text">    
        </div> -->
   <div class="form-group">
                        <label for="AccountID">Refferal ID <span class="text-danger">*</span></label>
                        <%-- Use the scriptlet to generate a random account ID --%>
                        <%
                            String randomAccountID = RandomAccountIDGenerator.generateRandomAccountID();
                        %>
                        <input readonly id="ReferralID" name="AccountID" class="form-control" type="text" value="<%= randomAccountID %>">
                    </div>

   
        <div class="form-group">
            <lab1el for="ReferralDate">ReferralDate <span class="text-danger">*</span></label>
            <input id="ReferralDate" name="ReferralDate" class="form-control" type="date">
        </div>
   

    
        <div class="form-group">
            <label class="col-form-label">ReferrerUserID <span class="text-danger">*</span></label>
            <select id="selectedEmployee" name="selectedEmployee" class="form-control">
            
            <%
						List<CustomerBean> dept = ReffertalDAO.getAllEmployees();
						for(CustomerBean dep: dept)
						{
						%>
                       <option><%= dep.getUsername() %></option>
                    <%
      					}
				     %>                       
        </select>
            
            <input name="ReferrerUserID" required class="form-control" type="text">
        </div>
  
<% String x=  CustomerDAO.getUserIDByUsername( username);
            		
            		
%>     <div class="form-group">
            <label class="col-form-label">ReferredUserID <span class="text-danger">*</span></label>
            <input name="ReferredUserID" readonly required class="form-control" type="text" value="<%= x %>">
        </div>
</div>

 <div class="submit-section">
    <button type="submit" class="btn btn-primary submit-btn">Submit</button>
  </div>
  </div>
        </div>
    </div>
</form>
