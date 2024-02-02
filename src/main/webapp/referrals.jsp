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
								<h3 class="page-title">Referals</h3>
								 <ul class="breadcrumb">
									<li class="breadcrumb-item"><a href="admin_dashboard.jsp">Dashboard</a></li>
									<li class="breadcrumb-item active">Referals</li>
								</ul>
							</div>
							<div class="col-auto float-right ml-auto">
<!-- 							<a href="#" class="Addbutton" data-toggle="modal" data-target="#addwithdrawal"><i class="fa fa-plus"></i> Add Withdrawal</a>
 -->							</div>
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
								<table id="DOWNLOAD">
									<thead>
										<tr>
								
											<th>ReferrerUserID</th>
									        <th>ReferredUserID in</th>
									        <th>ReferralDate</th>					            
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
List<Referrals> tax;

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
int recordcount = ReffertalDAO.totalCount();

noOfPages = (int) Math.ceil((double) recordcount / limit);
// pagee
if (!whereClause.isEmpty()) {
    // Apply the whereClause condition
	 tax = ReffertalDAO.getFilteredWithdrawals(whereClause, start, limit);
} else {
    // Retrieve all data based on the limit
	tax = ReffertalDAO.getFilteredWithdrawals("", start, limit);
}
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
								<button onclick="exportToExcel()">Export to Excel</button>
				                  <button id="downloadPdf">Download PDF</button>
<div class="row justify-content-center align-items-center" id = "flag1">
   
   <!-- Pagination links -->

    <% if (pageno > 1) { %>
        <a href="withdrawal.jsp?page=<%=pageno - 1%>">Previous</a>
    <% } %>

    <% for (int i = 1; i <= noOfPages; i++) { %>
        <% if (i == pageno) { %>
            <%=i%>
        <% } else { %>
            <a href="withdrawal.jsp?page=<%=i%>"><%=i%></a>
        <% } %>
    <% } %>

    <% if (pageno < noOfPages) { %>
        <a href="withdrawal.jsp?page=<%=pageno + 1%>">Next</a>
    <% } %>

</div>
							</div>
						</div>
					</div>
               
				<!-- /Page Content -->
				
				<!-- Add Tax Modal -->
				 <jsp:include page="withdrawal_add.jsp" />
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