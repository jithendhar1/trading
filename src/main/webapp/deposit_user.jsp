
<%@page import="DAO.CustomerDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Imp.DepositServiceImpl"%>
<%@ page import="beans.TransactionBean"%>
<%@ page import="java.util.List"%>
<%@ page import="DAO.DepositDAO"%>
<%
// Getting the username from the session
String username = null;
String uid = request.getParameter("userid");
if (uid != null) {
	String uid2 = CustomerDAO.getUsernameByUserID(uid);
	username = uid2;

} else {
	username = (String) session.getAttribute("username");
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">
<meta name="description" content="Smarthr - Bootstrap Admin Template">
<meta name="keywords"
	content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
<meta name="author" content="Dreamguys - Bootstrap Admin Template">
<meta name="robots" content="noindex, nofollow">
<title>Deposit</title>

<!-- Favicon -->
<link rel="shortcut icon" type="image/x-icon"
	href="assets/img/favicon.png">

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
	function changeButtonColor(button) {
		// Check if the button has a class indicating it has been clicked
		if (!button.classList.contains('clicked')) {
			// Change button color to green
			button.style.backgroundColor = 'green';
			// Add a class to indicate that the button has been clicked
			button.classList.add('clicked');
		}
	}
	
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
    }
</script>
</head>
<body>

	<!-- Main Wrapper -->
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
							<div id="welcomeMessage"
								style="text-align: center; margin-top: 20px; font-size: 24px;">
								Welcome
								<%=username%>!
							</div>
							<h3 class="page-title">Deposit</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="<%=(username.equals("Admin")) ? "admin_dashboard.jsp" : "user.jsp"%>">Dashboard</a></li>
								<li class="breadcrumb-item active">Deposit</li>
							</ul>
						</div>
						<div class="col-auto float-right ml-auto">
							<%
							if (!"Admin".equals(username)) {
							%>
							<a href="#" class="Addbutton" data-toggle="modal"
								data-target="#adddeposit"><i class="fa fa-plus"></i> Add
								Deposit</a>
							<%
							}
							%>
						</div>
					</div>
				</div>


				<table id="DOWNLOAD">
					<thead>
						<tr>


							<th>Transaction ID</th>
							<th  style="width:160px;">Deposit Date</th>
							<th>Amount</th>
							<th>userID</th>
							<th>Status</th>

						</tr>
					</thead>


					<%
					List<TransactionBean> tax = DepositDAO.getDepositsByUsername(username);
					if(tax == null || tax.isEmpty()){
						%>
							   <tr>
						             <td colspan="6" style="text-align: center; white-space: nowrap;"><h1>No Bonus Records Found</h1></td>
						</tr>     
						<%
						}else{
					for (TransactionBean tasks : tax) {
					%>
					<tr>
					
						<td style="width:140px;"><%=tasks.getTransactionID()%></td>
						<td  style="width:170px;"><%=tasks.getTransactiondate()%></td>
						<td><%=tasks.getAmount()%></td>
						<td><%=tasks.getUserID()%></td>
						<%
						String status = tasks.getStatus();
						String statusText = "Approved";

						if ("0".equals(status)) {
							statusText = "Pending";
						}
						%>
						<%
						if (!"Admin".equals(username)) {
						%>
						<td><%=statusText%></td>
						<%
						} else {
						%>
						<td>
						
							<form action="./DepositeStatus" method="post" >
								<input type="hidden" name="depositID"
									value="<%=tasks.getTransactionID()%>"> <input
									type="hidden" name="amount" value="<%=tasks.getAmount()%>">
								<input type="hidden" name="userID"
									value="<%=tasks.getUserID()%>"> <input type="hidden"
									name="username" value="<%=username%>">
									  <button type="submit" name="status" value="1" <% if ("1".equals(status)) { %>disabled<% } %>>Approved</button>
								<%-- <%
								if ("1".equals(status)) {
								%>

								<button type="submit" name="status" disabled>Approved</button>
								<%
								} else {
								%>
								<button type="submit" name="status">pending</button>
								<%
								}
								%> --%>
							</form>

						</td>


						<%
						}
						%>
					</tr>
					<%
					}}
					%>
				</table>
				<br>
                <button onclick="exportToExcel()">Export to Excel</button>
				<button id="downloadPdf">Download PDF</button>
			</div>
		</div>
	</div>

	<jsp:include page="deposit_add.jsp" />
	
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script src="js/select2.min.js"></script>


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