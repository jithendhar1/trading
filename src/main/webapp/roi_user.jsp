
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Imp.ROIServiceImpl" %>
<%@ page import="beans.ROIBean" %>
<%@ page import="java.util.List" %>
<%@page import="DAO.ROIDAO"%>
<%
    // Getting the username from the session
    String username = (String)session.getAttribute("username");
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
		
	    <jsp:include page="header.jsp" />
		  <jsp:include page="sidebar.jsp" />
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
								<h3 class="page-title">ROI</h3>
								<!-- <ul class="breadcrumb">
									<li class="breadcrumb-item"><a href="index.jsp">Dashboard</a></li>
									<li class="breadcrumb-item active">Tasks</li>
								</ul> -->
							</div>
							<div class="col-auto float-right ml-auto">
							<a href="#" class="btn add-btn" data-toggle="modal" data-target="#addroi"><i class="fa fa-plus"></i> Add ROI</a>
							</div>
						</div>
					</div>
					
								<table>
									<thead>
										<tr>
											<th>TransactionID</th>
											<th>userID </th>
									        <th>ROIAmount</th>
									        <th>Date</th>
									        <th>OpenAmount</th>
									        <th>ClosingAmount</th>
									        <!-- <th>Edit</th>
									         <th>Delete</th>  -->   
										</tr>
									</thead>
<%

  List<ROIBean>  tax = ROIDAO.getROIByUsername(username);

for (ROIBean tasks : tax) {
	 
%>
<tr>
    <td><%=tasks.getTransactionID() %></td>
   <td><%=tasks.getUserID() %></td>
   <td><%=tasks.getROIAmount() %></td>
   <td><%=tasks.getModifiedDate() %></td>
   <td><%=tasks.getOpenAmount() %></td>
   <td><%=tasks.getClosingAmount() %></td>
   <%--  <td>
        <a href="deposit_edit.jsp?vehicleID=<%= tasks.getTransactionID()%>">Edit</a>
    </td>
    <td>
        <a href="DeleteVechicleSrv?VehicleID=<%= tasks.getTransactionID()%>">Delete</a> 
    </td> --%>
</tr>
<%
}
%>

								</table>
<div class="row justify-content-center align-items-center" id = "flag1">
   
  

</div>
							</div>
						</div>
					</div>
               
			
				 <jsp:include page="roi_add.jsp" />
				
          
	

		
        <script src="js/jquery-3.2.1.min.js"></script>

		
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

		
		<script src="js/jquery.slimscroll.min.js"></script>
		
		
		<script src="js/select2.min.js"></script>

		
		<!-- <script src="js/app.js"></script> -->


    </body>
</html>