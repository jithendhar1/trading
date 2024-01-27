<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Imp.DepositServiceImpl" %>
<%@ page import="beans.DepositBean" %>
<%@ page import="java.util.List" %>
<%@page import="DAO.TransactionDAO"%>
<%@page import="beans.TransactionBean"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <title>Report -  template</title>
		
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
		
    </head>
    <body>
<!-- Main Wrapper -->
       <div class="main-wrapper">
    <!-- Main Wrapper -->
   <!-- Header -->
    <!-- Include your header HTML here -->
    <jsp:include page="header.jsp" />

    <jsp:include page="sidebar.jsp" />

    <!-- Page Wrapper -->
    <div class="page-wrapper">

        <!-- Page Content -->
        <div class="content container-fluid">

            <!-- Page Header -->
            <div class="page-header">
                <div class="row align-items-center">
                <div class="col">
							<div id="welcomeMessage" style="text-align: center; margin-top: 20px; font-size: 24px;">
                                Welcome  !
                              </div>
								<h3 class="page-title">Report</h3>
								 <ul class="breadcrumb">
									<li class="breadcrumb-item"><a href="admin_dashboard.jsp">Dashboard</a></li>
									<li class="breadcrumb-item active">Report</li>
								</ul>
							</div>
							
						</div>
					</div>
					
					
					
					
					
					
<label for="transactionType">Transaction Type:</label>
<select name="transactionType" id="transactionType" onchange="updateDropdown()">
    <option value="deposit">Deposit</option>
    <option value="roi">ROI</option>
    <option value="withdrawal">Withdrawal</option>
    <option value="referral">Referral</option>
</select>

<script>
function updateDropdown() {
    var selectedValue = document.getElementById("transactionType").value;
    // Use AJAX to send the selected value to the server and update the table content
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            // Update the table content with the response from the server
            document.getElementById("transactionTableBody").innerHTML = this.responseText;
        }
    };
    // Replace 'yourServlet' with the actual servlet or server-side script handling the request
    xhttp.open("GET", "ReportSrv?transactionType=" + selectedValue, true);
    xhttp.send();
}
</script>

<table>
    <thead>
        <tr>
            <th>userID</th>
            <th>open amount</th>
            <th>closing amount</th>
            <th>transaction date</th>
            <th>status</th>  
            <th>Approved by</th>
            <th>TransactionID</th>  
        </tr>
    </thead>
    <tbody id="transactionTableBody">
        <% 
        String transactionType = request.getParameter("transactionType");
        List<TransactionBean>  tax = TransactionDAO.getTransactionsByTransactiontype(transactionType);
        for (TransactionBean tasks : tax) {
        %>	
        <tr>
            <td><%=tasks.getUserID() %></td>
            <td><%=tasks.getOpenamount() %></td>
            <td><%=tasks.getClosingamount() %></td>
            <td><%=tasks.getTransactiondate() %></td>
            <td><%=tasks.getStatus() %></td>
            <td><%=tasks.getApprovedby() %></td>
            <td><%=tasks.getTransactionID() %></td>
        </tr>
        <%
        }
        %>
    </tbody>
</table>

								
							</div>
						</div>
					</div>
               
				
				
				
				
          
	

		
        <script src="js/jquery-3.2.1.min.js"></script>

		
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

		
		<script src="js/jquery.slimscroll.min.js"></script>
		
		
		<script src="js/select2.min.js"></script>

		
		<!-- <script src="js/app.js"></script> -->


    </body>
</html>