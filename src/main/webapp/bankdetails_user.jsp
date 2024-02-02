
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
        <title>Bank Details</title>
		
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
				<script type="text/javascript" src="js/jspdf.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
<script src="https://unpkg.com/jspdf@latest/dist/jspdf.umd.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.13/jspdf.plugin.autotable.min.js"></script>
<script
	src="https://cdn.rawgit.com/eKoopmans/html2pdf/v0.9.3/dist/html2pdf.bundle.js"></script>
<script
	src="https://cdn.rawgit.com/eKoopmans/html2pdf/master/dist/html2pdf.bundle.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2.0.5/FileSaver.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.9/xlsx.full.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/TableExport/5.2.0/js/tableexport.min.js"></script>
	<script>
	function exportToExcel() {
        TableExport(document.getElementById('DOWNLOAD'), {
            headers: true,                      // (Boolean), display table headers (th or td elements) in the <thead>, (default: true)
            footers: true,                      // (Boolean), display table footers (th or td elements) in the <tfoot>, (default: false)
            formats: ['xlsx'],                  // (String[]), filetype(s) for the export, (default: ['xlsx', 'csv', 'txt'])
            filename: 'transactions',           // (id, String), filename for the downloaded file, (default: 'id')
            bootstrap: false,                   // (Boolean), style buttons using bootstrap, (default: true)
            position: 'bottom',                 // (top, bottom), position of the caption element relative to table, (default: 'bottom')
            ignoreRows: null,                   // (Number, Number[]), row indices to exclude from the exported file(s) (default: null)
            ignoreCols: null,                   // (Number, Number[]), column indices to exclude from the exported file(s) (default: null)
            trimWhitespace: true                // (Boolean), remove white spaces from beginning and end of cell contents, (default: true)
        });
    }</script>
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
									<h3 class="page-title">Account Details</h3>
							 <ul class="breadcrumb">
									<!-- <li class="breadcrumb-item"><a href="user.jsp">Dashboard</a></li> -->
									<li class="breadcrumb-item"><a href="<%= (username.equals("Admin")) ? "admin_dashboard.jsp" : "user.jsp" %>">Dashboard</a></li>
									<li class="breadcrumb-item active">Acountdetails</li>
								</ul> 
								
							</div>
							
<%
boolean prof = BankdetailsDAO.isUserExists(username);
%>

<%-- <div class="col-auto float-right ml-auto">
<% if (!"Admin".equals(username)) { %>
    
    <button class="btn add-btn" data-toggle="modal" data-target="#addbankdetails">
        <i class="fa fa-plus"></i> Add Account Details
    </button>
    <%} %>
</div> --%>



<%-- <div class="col-auto float-right ml-auto">
    <button type="button" class="btn add-btn" id="employeeInformationBtn" <% if (prof) { %> disabled <% } else { %> onclick="window.location.href='bankdetails_add.jsp'" <% } %>>
        <i class="fa fa-plus"></i> Add BankDetails
    </button>
</div> --%>


						</div>
					</div>
					<!-- /Page Header -->
					<!-- Search Filter -->
					
								<table id="DOWNLOAD">
									<thead>
										<tr>
										
											<th>userID </th>
									        <th>userName</th>
									        <th>Amount</th>
									        <th>AcountNumber</th>
									       <!--  <th>BankName</th> -->
									        <!-- <th>Edit</th>
									         <th>Delete</th>   -->  
										</tr>
									</thead>
<%

  List<BankdetailsBean>  tax = BankdetailsDAO.getBankdetailsByUsername(username);
if(tax == null || tax.isEmpty()){
%>
	   <tr>
             <td colspan="6" style="text-align: center; white-space: nowrap;"><h1>No Bonus Records Found</h1></td>
</tr>     
<%
}else{
for (BankdetailsBean tasks : tax) {
	 
%>
<tr>
  
   <td><%=tasks.getUserID() %></td>
  <td><%=tasks.getUserName() %></td>
  <td><%=tasks.getAmount() %></td>
  <td><%=tasks.getAcountNumber() %></td>
  <%-- <td><%=tasks.getBankName() %></td> --%>
    <%-- <td>
        <a href="bankdetails_edit.jsp?vehicleID=<%= tasks.getUserID()%>">Edit</a>
    </td>
    <td>
        <a href="DeleteVechicleSrv?VehicleID=<%= tasks.getUserID()%>">Delete</a> 
    </td> --%>
</tr>
<%
}}
%>

								</table>
								<button onclick="exportToExcel()">Export to Excel</button>
				                  <button id="downloadPdf">Download PDF</button>
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

<script type="text/javascript">
 		document.getElementById('downloadPdf').addEventListener('click', function() {
 		      const invoiceElement = document.getElementById('DOWNLOAD');
 		      const options = {
 		        margin: 1,
 		        filename: 'Transaction.pdf',
 		        image: { type: 'jpeg', quality: 0.98 },
 		        html2canvas: { scale: 2 },
 		        jsPDF: { unit: 'mm', format: 'a4', orientation: 'landscape' }
 		      };
 		      // Then call html2pdf with the element and options
 		      html2pdf().from(invoiceElement).set(options).save();
 		    });
 		</script>
    </body>
</html>