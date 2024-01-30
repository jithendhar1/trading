<%@page import="DAO.CustomerDAO"%>
<%@page import="beans.CustomerBean"%>
<%@page import="DAO.ReffertalDAO"%>
<%@ page import="java.util.List" %>
<%@page import="DAO.ROIDAO"%>
<%@page import="srv.RandomAccountIDGenerator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <link rel="stylesheet" href="css/tstyles.css">
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
   
 <!--    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script> -->
   
    <title>Leave List</title>
   
    
    
   <script>
    function submitForm(event) {
        event.preventDefault();  // Prevent the form from submitting normally

        var xhr = new XMLHttpRequest();
        xhr.open('POST', './AddROISrv', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    handleResponse(xhr.responseText);
                } else {
                    console.log("Error submitting the form. Status: " + xhr.status);
                }
            }
        };

        var formData = new FormData(document.querySelector('form'));
        var serializedData = new URLSearchParams(formData).toString();

        xhr.send(serializedData);
    }

    function handleResponse(response) {
        var responseObject = JSON.parse(response);

        if (responseObject.status === "success") {
            alert(responseObject.message);
        } else {
            if (responseObject.message.includes("already added")) {
                // Show a specific alert when ROI is already added
                alert("ROI Amount is already added for today.");
            } else {
                // Handle other failure cases
                alert("An error occurred: " + responseObject.message);
            }
        }
    }
</script>


 
    
</head>
<body>


<form action="./AddROISrv" method="post" onsubmit="submitForm(event)">
<div id="addroi" class="modal custom-modal fade" role="dialog">
 <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
        
            <div class="modal-header">
                <h5 class="modal-title">Add ROI</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            
            <div class="modal-body">
        <div class="form-group">
                        <label for="DepositTransactionID">ROI TransactionID <span class="text-danger">*</span></label>
                        <%-- Use the scriptlet to generate a random account ID --%>
                        <%
                            String randomAccountID = RandomAccountIDGenerator.generateRandomAccountID();
                        %>
                        <input readonly id="DepositTransactionID" name="TransactionID" class="form-control" type="text" value="<%= randomAccountID %>">
                    </div>
   
    <div class="form-group">
            <label class="col-form-label">ROIAmount <span class="text-danger">*</span></label>
            <input name="ROIAmount" required class="form-control" type="text">
        </div>
  
 <div class="submit-section">
    <button type="submit" class="btn btn-primary submit-btn">Submit</button>
  </div>
  
    </div>
  </div>
        </div>
    </div>
</form>

    