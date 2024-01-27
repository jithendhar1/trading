<%@page import="DAO.CustomerDAO"%>
<%@page import="beans.CustomerBean"%>
<%@page import="DAO.ReffertalDAO"%>
<%@ page import="java.util.List" %>
<%@page import="DAO.ROIDAO"%>
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
    <link rel="stylesheet" href="css/styles.css">
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
   
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
   
    <title>Leave List</title>
      <script>
        document.addEventListener("DOMContentLoaded", function() {
            // Get references to the input fields
            var roiaAmountInput = document.querySelector('input[name="ROIAmount"]');
            var openAmountInput = document.querySelector('input[name="OpenAmount"]');
            var closingAmountInput = document.querySelector('input[name="ClosingAmount"]');

            // Add event listeners to ROIAmount and OpenAmount inputs
            roiaAmountInput.addEventListener("input", updateClosingAmount);
            openAmountInput.addEventListener("input", updateClosingAmount);

            // Function to calculate and update ClosingAmount
            function updateClosingAmount() {
                var roiaAmount = parseFloat(roiaAmountInput.value) || 0;
                var openAmount = parseFloat(openAmountInput.value) || 0;
                var closingAmount = roiaAmount * openAmount;

                // Update ClosingAmount input value
                closingAmountInput.value = closingAmount.toFixed(2); // You can adjust the number of decimal places as needed
            }

            // Fetch OpenAmount initially
            fetchOpenAmount();
        });

        function fetchOpenAmount() {
            var userID = document.getElementById('selectedEmployee').value;

            // Make an AJAX request to fetch the OpenAmount based on userID
            var xhr = new XMLHttpRequest();
            xhr.open('GET', '/trading/UseridOpenAmtservlet?userID=' + userID, true);

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        // Update the OpenAmount field with the fetched value
                        var openAmountInput = document.getElementById('OpenAmount');
                        openAmountInput.value = xhr.responseText;

                        // Trigger the closing amount update
                        updateClosingAmount();
                    } else {
                        console.log("Error fetching OpenAmount. Status: " + xhr.status);
                    }
                }
            };

            xhr.send();
        }
    </script>
    
    
    
 
    
</head>
<body>


<form action="./AddROISrv" method="post">
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
 
        <!-- <div class="form-group">
            <label for="TransactionID">TransactionID<span class="text-danger">*</span></label>
            <input id="TransactionID" name="TransactionID" class="form-control" type="text">    
        </div> -->
   
    <% String x=  CustomerDAO.getUserIDByUsername(username);%>
 
        <div class="form-group">
            <label for="userID" class="col-form-label">userID <span class="text-danger">*</span></label>
<%--             <input id="userID"  name="userID"  required class="form-control" type="text" value="<%= x %>" onchange="fetchOpenAmount();">
 --%>            <select id="selectedEmployee" name="userID" class="form-control" onchange="fetchOpenAmount();">
            <%
						List<CustomerBean> dept = ReffertalDAO.getAllEmployees();
						for(CustomerBean dep: dept)
						{
						%>
                       <option><%= dep.getUserID()%></option>
                    <%
      					}
				     %>                       
        </select>
        </div>


   <!-- /*  function fetchOpenAmount() {
        var userID = document.getElementById('userID').value;
		console.log("chintu");
        // Make an AJAX request to fetch the OpenAmount based on userID
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/trading/UseridOpenAmtservlet?userID=' + userID, true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Update the OpenAmount field with the fetched value
                document.getElementById('OpenAmount').value = xhr.responseText;
            }
        };

        xhr.send();
    } */
     -->
   <!--  <script>
    function fetchOpenAmount() {
        var userID = document.getElementById('userID').value;
        console.log("UserID: " + userID); // Debugging statement

        // Make an AJAX request to fetch the OpenAmount based on userID
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/trading/UseridOpenAmtservlet?userID=' + userID, true);

        // Add the onreadystatechange function here
        xhr.onreadystatechange = function () {
            console.log("ReadyState: " + xhr.readyState + ", Status: " + xhr.status); // Debugging statement
            if (xhr.readyState === 4) {
                console.log("Response: " + xhr.responseText); // Debugging statement
                if (xhr.status === 200) {
                    // Update the OpenAmount field with the fetched value
                    var openAmountInput = document.getElementById('OpenAmount');
                    openAmountInput.value = xhr.responseText;

                    // Debugging statement
                    console.log("OpenAmount Updated: " + openAmountInput.value);
                } else {
                    console.log("Error fetching OpenAmount. Status: " + xhr.status);
                }
            }
        };

        xhr.send();
    }
</script> -->
    
        <div class="form-group">
            <label class="col-form-label">ROIAmount <span class="text-danger">*</span></label>
            <input name="ROIAmount" required class="form-control" type="text">
        </div>
  

 
        <div class="form-group">
            <label class="col-form-label">ModifiedDate <span class="text-danger">*</span></label>
            <input name="ModifiedDate" required class="form-control" type="date">
        </div>

     
       <div class="form-group">
    <label class="col-form-label">OpenAmount <span class="text-danger">*</span></label>
    <input id="OpenAmount" readonly name="OpenAmount" required class="form-control" type="text">
</div>

  

 
        <div class="form-group">
            <label class="col-form-label">ClosingAmount <span class="text-danger">*</span></label>
            <input name="ClosingAmount" readonly required class="form-control" type="text">
        </div>

 <div class="submit-section">
    <button type="submit" class="btn btn-primary submit-btn">Submit</button>
  </div>
  
    </div>
  </div>
        </div>
    </div>
</form>

    