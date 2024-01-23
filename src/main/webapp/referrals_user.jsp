<%@page import="beans.Referrals"%>
<%@page import="DAO.ReffertalDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Imp.WithdrawalServiceImpl" %>
<%@ page import="java.util.List" %>
<%@page import="DAO.WithdrawalDAO"%>
<%
    // Getting the username from the session
    String username = (String)session.getAttribute("customername");
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
        <title>vechicle -  template</title>
		
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
                                Welcome  <%= username%>!
                              </div>
								<h3 class="page-title">Withdrawal</h3>
								 <ul class="breadcrumb">
									<li class="breadcrumb-item"><a href="admin_dashboard.jsp">Dashboard</a></li>
									<li class="breadcrumb-item active">Withdrawal</li>
								</ul>
							</div>
							<div class="col-auto float-right ml-auto">
 							<a href="#" class="Addbutton" data-toggle="modal" data-target="#addrefferal"><i class="fa fa-plus"></i> Add refferal</a>
 							</div>
						</div>
					</div>
					<!-- /Page Header -->
					<!-- Search Filter -->
						<table>
									<thead>
										<tr>
								
											<th>ReferrerUserID</th>
									        <th>ReferredUserID in</th>
									        <th>ReferralDate</th>					            
										</tr>
									</thead>
<%

  List<Referrals>  tax = ReffertalDAO.getReferralsByUsername(username);

for (Referrals tasks : tax) {
	 
%>
<tr>
    <td><%=tasks.getReferrerUserID()%></td>
    <td><%=tasks.getReferredUserID()%></td>
    <td><%=tasks.getReferralDate()%></td>
    
    <%-- <td>
        <a href="withdrawal_edit.jsp?vehicleID=<%= tasks.getWithdrawalID()%>">Edit</a>
    </td>
    <td>
        <a href="DeleteVechicleSrv?VehicleID=<%= tasks.getWithdrawalID()%>">Delete</a> 
    </td> --%>
</tr>
<%
}
%>

								</table>

							</div>
						</div>
					</div>
               
		
				 <jsp:include page="add_Refferal.jsp" />

				
          
	

		
        <script src="js/jquery-3.2.1.min.js"></script>

		
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

		
		<script src="js/jquery.slimscroll.min.js"></script>
		
		
		<script src="js/select2.min.js"></script>

		
		


    </body>
</html>