<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Imp.DepositServiceImpl"%>
<%@ page import="beans.DepositBean"%>
<%@ page import="java.util.List"%>
<%@page import="DAO.TransactionDAO"%>
<%@page import="beans.TransactionBean"%>
<%
    // Getting the username from the session
    String username = (String)session.getAttribute("username");
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
<title>Report - template</title>

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
        function updateDropdown(updateType) {
            var selectedValue = "";

            // Retrieve selected value based on updateType
            if (updateType === 'customDate') {
                selectedValue = document.getElementById("Date").value;
            } else if (updateType === 'userID') {
                selectedValue = document.getElementById("UserID").value;
            }else if (updateType === 'date') {
                selectedValue = document.getElementById("transactiondate").value;
            }else if (updateType === 'type') {
                selectedValue = document.getElementById("transactionType").value;
            }

            // Create AJAX request
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    // Update the table content with the response from the server
                    document.getElementById("transactionTableBody").innerHTML = this.responseText;
                }
            };

            // Adjust the servlet URLs based on your actual servlet names
            if (updateType === 'type') {
                xhttp.open("GET", "ReportSrv?transactionType=" + selectedValue, true);
            } else if (updateType === 'date') {
                xhttp.open("GET", "ReporttransactiondateSrv?transactiondate=" + selectedValue, true);
            } else if (updateType === 'userID') {
                xhttp.open("GET", "ReporttransactionuseridSrv?UserID=" + selectedValue, true);
            } else if (updateType === 'customDate') {
                xhttp.open("GET", "ReportDateSrv?Date=" + selectedValue, true);
            }

            xhttp.send();
        }
        
 /* function exportToPDF() {
            var pdf = new jsPDF();
            
            // Use the autotable plugin to generate the table from HTML
            pdf.autoTable({ html: '#transactionTableBody' });

            // Save the PDF with a meaningful name
            pdf.save('transactions.pdf');
        } */
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
								<%= username%>!
							</div>
							<h3 class="page-title">Report</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a href="admin_dashboard.jsp">Dashboard</a></li>
								<li class="breadcrumb-item active">Report</li>
							</ul>
						</div>

					</div>
				</div>

				<label for="transactionType">Transaction Type:</label> <select
					style="width: 120px;" class="input" name="transactionType"
					id="transactionType" onchange="updateDropdown('type')">
					<option>Select</option>
					<option value="deposit">Deposit</option>
					<option value="roi">ROI</option>
					<option value="withdrawal">Withdrawal</option>
					<option value="referral">Referral</option>
					<option value="all">ALL</option>

				</select> <label style="margin-left: 20px;" for="transactiondate">Transaction
					Date:</label> <select style="width: 120px;" class="input"
					name="transactiondate" id="transactiondate"
					onchange="updateDropdown('date')">
					<option>Select</option>
					<option value="yearly">Yearly</option>
					<option value="halfyearly">Halferly</option>
					<option value="quarterly">Quartely</option>
					<option value="monthly">Monthly</option>

				</select> <label style="margin-left: 20px;" for="UserID"> UserID :</label> <select
					style="width: 120px;" class="input" id="UserID" name="UserID"
					onchange="updateDropdown('userID')">
					<option value="Select">Select</option>
					<%
        List<TransactionBean> transactions = TransactionDAO.getAllTransactions();
        for (TransactionBean transaction : transactions) {
    %>
					<option value="<%= transaction.getUserID() %>"><%= transaction.getUserID() %></option>
					<%
        }
    %>
				</select> <label style="margin-left: 20px;" for="Date">Date :</label> <input
					style="width: 120px;" class="input" id="Date" name="Date"
					type="date" onchange="updateDropdown('customDate')"><br>

				<!-- <button onclick="exportToPDF()">Export to PDF</button> -->
				<br>
				<table id="DOWNLOAD">
					<thead>
						<tr>
							<th>userID</th>
							<th>transaction date</th>
							<th>open amount</th>
							<th>TransactionID</th>
							<th>status</th>
							<th>Transaction type</th>
							<th>Amount</th>
							<th>closing amount</th>
						</tr>
					</thead>
					<tbody id="transactionTableBody">
						<% 
                            // Retrieve parameters
                            String updateType = request.getParameter("updateType");
                            String transactionType = request.getParameter("transactionType");
                            String transactiondate = request.getParameter("transactiondate");
                            String userID = request.getParameter("UserID");
                            String customDate = request.getParameter("Date");

                            // Initialize the list of transactions
                            List<TransactionBean> tax = null;

                            // Check the update type and fetch transactions accordingly
                            if ("type".equals(updateType)) {
                                tax = TransactionDAO.getTransactionsByTransactionType(transactionType);
                            } else if ("date".equals(updateType)) {
                                tax = TransactionDAO.getTransactionsByTransactiondate(transactiondate);
                            } else if ("userID".equals(updateType)) {
                                tax = TransactionDAO.getTransactionsByUserID(userID);
                            } else if ("customDate".equals(updateType)) {
                                tax = TransactionDAO.getTransactionsByDate(customDate);
                            } else {
                                // Default case, load all transactions
                                tax = TransactionDAO.getAllTransactions();
                            }

                            // Iterate through transactions
                            for (TransactionBean tasks : tax) {
                            	
                            	
                        %>
						<tr>
					
						
							<td><%=tasks.getUserID() %></td>
							<td><%=tasks.getTransactiondate() %></td>
							<td><%=tasks.getOpenamount() %></td>
							<td><%=tasks.getTransactionID() %></td>
							<%--  <td><%=tasks.getStatus()=="1"?"Approved":"Pending" %></td> --%>
							<td><%= tasks.getStatus().equals("1") ? "Approved" : "Pending" %></td>
							 <td><%=tasks.getTransactiontype() %></td>
							 <td><%=tasks.getAmount() %></td>
							<td><%=tasks.getClosingamount() %></td>
							
							
						</tr>
						<%
                            }
                        %>
					</tbody>
				</table>

				<br>
				<!-- <button onclick="exportToPDF()">Export to PDF</button> -->
				<button onclick="exportToExcel()">Export to Excel</button>
				<button id="downloadPdf">Download PDF</button>

			</div>
		</div>
	</div>


	<script src="js/jquery-3.2.1.min.js"></script>


	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>


	<script src="js/jquery.slimscroll.min.js"></script>


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