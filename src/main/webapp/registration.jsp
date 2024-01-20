<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
        <title>worker -  template</title>
		
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
    
    
    	<div class="row"
			style="margin-top: 5px; margin-left: 2px; margin-right: 2px;">

			   <form action="./AddCustomerSrv" method="post"
				 class="col-md-6 col-md-offset-3"
				style="border: 2px solid black; border-radius: 10px; background-color:#ADD8E6; padding: 10px;">  
			
				<div style="font-weight: bold;" class="text-center">
 					<h2 style="color: navy blue;">Registration Form</h2>
				</div>	

        <div class="row">
				 <div class="col-md-6">
        <div class="form-group">
            <label>customername  <span class="text-danger">*</span></label>
            <input  name="customername" class="form-control" type="text">
        </div>
    </div>

    <div class="col-sm-6">
        <div class="form-group">
            <label >email  <span class="text-danger">*</span></label>
            <input  name="email" class="form-control" type="text">
        </div>
    </div>

  <div class="col-md-6">
            <div class="form-group">
                <label class="col-form-label">phno <span class="text-danger">*</span></label>
                <input name="phno" required class="form-control" type="text">
                <font color="red"><%= request.getAttribute("warningMessage") %></font>
            </div>
        </div>

    <div class="col-md-6">
        <div class="form-group">
            <label class="col-form-label">firstname <span class="text-danger">*</span></label>
            <input name="firstname" required class="form-control" type="text">
        </div>
    </div>
 <div class="col-md-6">
        <div class="form-group">
            <label class="col-form-label">lastname <span class="text-danger">*</span></label>
            <input name="lastname" required class="form-control" type="text">
        </div>
    </div>
    
			
       </div>              
					<div class="col-md-6" >
						<button type="submit" class="btn btn-success">Register</button>
					</div>

          

             </form>

       </div> 


    
    
    
    
    
    
    
    
    
    
    
    	
        <script src="js/jquery-3.2.1.min.js"></script>

		
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

		
		<script src="js/jquery.slimscroll.min.js"></script>
		
		
		<script src="js/select2.min.js"></script>

		
		<script src="js/app.js"></script>


    </body>
</html>