
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Imp.BankDetailsServiceImpl" %>
<%@ page import="beans.BankdetailsBean" %>
<%@ page import="java.util.List" %>
<%@page import="DAO.BankdetailsDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Getting the username from the session
    String username = (String) session.getAttribute("username");
  //  boolean userExists = BankdetailsDAO.isUserExists(username);
   
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
									<h3 class="page-title">BankDetails</h3>
							 <ul class="breadcrumb">
									<li class="breadcrumb-item"><a href="user.jsp">Dashboard</a></li>
									<li class="breadcrumb-item active">Bankdetails</li>
								</ul> 
								
							</div>
							
<%
boolean prof = BankdetailsDAO.isUserExists(username);
%>

<div class="col-auto float-right ml-auto">
    <button class="btn add-btn" data-toggle="modal" <% if (prof) { %>disabled<% } %> data-target="#addbankdetails">
        <i class="fa fa-plus"></i> Add BankDetails
    </button>
</div>



<%-- <div class="col-auto float-right ml-auto">
    <button type="button" class="btn add-btn" id="employeeInformationBtn" <% if (prof) { %> disabled <% } else { %> onclick="window.location.href='bankdetails_add.jsp'" <% } %>>
        <i class="fa fa-plus"></i> Add BankDetails
    </button>
</div> --%>


						</div>
					</div>
					<!-- /Page Header -->
					<!-- Search Filter -->
					
								<table>
									<thead>
										<tr>
										
											<th>userID </th>
									        <th>userName</th>
									        <th>Amount</th>
									        <!-- <th>Edit</th>
									         <th>Delete</th>   -->  
										</tr>
									</thead>
<%

  List<BankdetailsBean>  tax = BankdetailsDAO.getBankdetailsByUsername(username);

for (BankdetailsBean tasks : tax) {
	 
%>
<tr>
  
   <td><%=tasks.getUserID() %></td>
  <td><%=tasks.getUserName() %></td>
  <td><%=tasks.getAmount() %></td>
    <%-- <td>
        <a href="bankdetails_edit.jsp?vehicleID=<%= tasks.getUserID()%>">Edit</a>
    </td>
    <td>
        <a href="DeleteVechicleSrv?VehicleID=<%= tasks.getUserID()%>">Delete</a> 
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
               
				<!-- /Page Content -->
				
				<!-- Add Tax Modal -->
				 <jsp:include page="bankdetails_add.jsp" />
				<!-- /Add Tax Modal -->
				
				<%-- <!-- Edit Tax Modal -->
				 <jsp:include page="edit_tasks.jsp" />
				<!-- /Edit Tax Modal -->
				
				<!-- Delete Tax Modal -->
				 <jsp:include page="delete_task.jsp" />
				<!-- /Delete Tax Modal --> --%>
				
          
	

		
        <script src="js/jquery-3.2.1.min.js"></script>

		
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

		
		<script src="js/jquery.slimscroll.min.js"></script>
		
		
		<script src="js/select2.min.js"></script>

		
		<!-- <script src="js/app.js"></script> -->


    </body>
</html>