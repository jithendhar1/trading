
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Imp.BankDetailsServiceImpl" %>
<%@ page import="beans.BankdetailsBean" %>
<%@ page import="java.util.List" %>
<%@page import="DAO.BankdetailsDAO"%>
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

<%
HttpSession sessionRec = request.getSession(true);

// Initialize recordsPerPage and currentPage as Java variables
String recordsPerPageStr = (String) sessionRec.getAttribute("recordsPerPage");
String currentPageStr = (String) sessionRec.getAttribute("currentPage");

if (recordsPerPageStr == null || "0".equals(recordsPerPageStr)) {
    recordsPerPageStr = "10"; // Set a default value, e.g., 1
    sessionRec.setAttribute("recordsPerPage", recordsPerPageStr);
}
int recordsPerPage = Integer.parseInt(recordsPerPageStr);

if (currentPageStr == null || "0".equals(currentPageStr)) {
    currentPageStr = "1"; // Set a default value, e.g., 1
    sessionRec.setAttribute("currentPage", currentPageStr);
}
int currentPage = Integer.parseInt(currentPageStr);

// Handle the change in recordsPerPage here
int newRecordsPerPage = 10; // Default value
String newRecordsPerPageParam = request.getParameter("newRecordsPerPage");
if (newRecordsPerPageParam != null) {
    newRecordsPerPage = Integer.parseInt(newRecordsPerPageParam);
    sessionRec.setAttribute("recordsPerPage", String.valueOf(newRecordsPerPage));
    //currentPage = 1; // Reset to the first page when changing recordsPerPage
    //sessionRec.setAttribute("currentPage", "1");
}

%>
<script>
    var recordsPerPage = <%= recordsPerPage %>; // Use Java variable in JavaScript
    var currentPage = <%= currentPage %>; 
 
    function changeRecordsPerPage() {
        var recordsPerPageSelect = document.getElementById("recordsPerPage");
        var selectedValue = recordsPerPageSelect.value;
        
        // Update the URL with the selected "recordsPerPage" value and navigate to it
        var baseUrl = window.location.href.split('?')[0];
        var newUrl = baseUrl + '?newRecordsPerPage=' + selectedValue;
        window.location.href = newUrl;
    }
</script>



		<!-- Main Wrapper -->
    <div class="main-wrapper">

        <!-- Header -->
         <jsp:include page="header.jsp" />

        <!-- Sidebar -->
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
								<h3 class="page-title">BankDetails</h3>
							 <ul class="breadcrumb">
									<li class="breadcrumb-item"><a href="admin_dashboard.jsp">Dashboard</a></li>
									<li class="breadcrumb-item active">BankDetails</li>
								</ul> 
							</div>
							<div class="col-auto float-right ml-auto">
							<a href="#" class="Addbutton" data-toggle="modal" data-target="#addbankdetails"><i class="fa fa-plus"></i> Add BankDetails</a>
							</div>
						</div>
					</div>
					<!-- /Page Header -->
					<!-- Search Filter -->
<%-- <form action="./SearchVechicleSrv" method="post">
    <div class="row filter-row">
    <div class="col-sm-6 col-md-3" id = "flag">
        <label>Records per page:</label>
        <select id="recordsPerPage" onchange="changeRecordsPerPage()">
            
            <option value="10">10</option>
           
          
        </select>
        
    </div>
        <div class="col-sm-6 col-md-3">
            <div class="form-group form-focus select-focus">
                <label for="id">vehicleID:</label>
                <input class="input" type="text" name="DepositID" id="vehicleID">
            </div>
        </div>
        
      <div class="col-sm-6 col-md-3">
				    <input class="search" type="submit" value="SEARCH">
	</div>
	
    </div>
    <input type="hidden" name="start" value="<%= currentPage %>">
    <input type="hidden" name="limit" value="<%= newRecordsPerPage %>">

    
</form> --%>
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
int start = currentPage;
int limit = newRecordsPerPage;
// pagenation code start
int pageno = 1;
int noOfPages = 0;

String pageNoStr = request.getParameter("page");

if (pageNoStr != null) {
    pageno = Integer.parseInt(pageNoStr);
}

start = (pageno - 1) * limit;
// pagenation code ended
String usernameFilter = request.getParameter("AccountID");
String idFilter = request.getParameter("DepositID");
List<BankdetailsBean> tax;

String whereClause = ""; // Initialize an empty whereClause

if (usernameFilter != null && !usernameFilter.isEmpty()) {
    whereClause = "AccountID like '%" + usernameFilter + "%'";
}

if (idFilter != null && !idFilter.isEmpty()) {
    if (!whereClause.isEmpty()) {
        whereClause += " or ";
    }
    whereClause += "DepositID = '" + idFilter + "'";
}
// page
int recordcount = BankdetailsDAO.totalCount();

noOfPages = (int) Math.ceil((double) recordcount / limit);
// pagee
if (!whereClause.isEmpty()) {
    // Apply the whereClause condition
	 tax = BankdetailsDAO.getFilteredBankdetails(whereClause, start, limit);
} else {
    // Retrieve all data based on the limit
	tax = BankdetailsDAO.getFilteredBankdetails("", start, limit);
}
for (BankdetailsBean tasks : tax) {
	
%>
<tr>
  
   <td><%=tasks.getUserID() %></td>
  <td><%=tasks.getUserName() %></td>
  <td><%=tasks.getAmount() %></td>
   <%--  <td>
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
   
   <!-- Pagination links -->

    <% if (pageno > 1) { %>
        <a href="bankdetails.jsp?page=<%=pageno - 1%>">Previous</a>
    <% } %>

    <% for (int i = 1; i <= noOfPages; i++) { %>
        <% if (i == pageno) { %>
            <%=i%>
        <% } else { %>
            <a href="bankdetails.jsp?page=<%=i%>"><%=i%></a>
        <% } %>
    <% } %>

    <% if (pageno < noOfPages) { %>
        <a href="bankdetails.jsp?page=<%=pageno + 1%>">Next</a>
    <% } %>

</div>
							</div>
						</div>
					</div>
               
				<!-- /Page Content -->
				
				<!-- Add Tax Modal -->
				 <%-- <jsp:include page="bankdetails_add.jsp" /> --%>
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