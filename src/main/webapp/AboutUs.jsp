<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Imp.DepositServiceImpl" %>
<%@ page import="beans.DepositBean" %>
<%@ page import="java.util.List" %>
<%@page import="DAO.TransactionDAO"%>
<%@page import="beans.TransactionBean"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <title>Report -  template</title>
		
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
                                Welcome  !
                              </div>
								<h3 class="page-title">About US </h3>
								 <ul class="breadcrumb">
									<li class="breadcrumb-item"><a href="admin_dashboard.jsp">Dashboard</a></li>
									<li class="breadcrumb-item active">About Us</li>
								</ul>
							</div>
							
						</div>
					</div>
					
					
					
					
			 <main id="main">
      <section id="about" class="about" style="padding-bottom: 0;">
          <div class="container">
              <div class="section-title" data-aos="zoom-out">
                  <p style="color: maroon; text-align: center;">Trading</p><br>
              </div>
              <div class="row content" data-aos="fade-up" style="display: flex; flex-direction: row; align-items: center;">
                  <div class="col-lg-6" style="text-align: justify;">
                    <p>Welcome to our cutting-edge Forex AI Trading Bot, where advanced technology meets the dynamic world of foreign exchange trading! At XM-S1 AI Trading Bot we are dedicated to providing you with a powerful and intelligent solution to enhance your trading experience and maximize your potential for success.</p>
                      <p>
                         Our XM S1 Forex AI Trading Bot leverages the power of artificial intelligence and machine learning algorithms to analyze vast amounts of market data in real-time. By utilizing sophisticated trading strategies and predictive modeling, our bot is designed to identify profitable trading opportunities with precision and speed. It continuously adapts and evolves, learning from past trades and market trends to optimize its performance over time.
                      </p>
                      <!-- Remove the second paragraph here -->
                      <ul>
                          <li><i class="ri-check-double-line"></i>Advanced Data Analysis</li>
                          <li><i class="ri-check-double-line"></i>Automated Trading </li>
                           <li><i class="ri-check-double-line"></i> Risk Management </li> 
                      </ul>
                  </div>
                  <div class="col-lg-6 order-1 order-lg-2 text-center" style="transition: transform 0.3s; display: flex; justify-content: center; align-items: center;">
                      <br> <br> <br><img src="assets/profiles/trade.jpg" alt="" class="img-fluid" style="max-width: 350px; height: 350px;">
                  </div>
              </div>
          </div>
      </section>
  </main>
	
								</div>
						</div>
					</div>
					  <div>
               <jsp:include page="footer.jsp" />
				  </div>
        <script src="js/jquery-3.2.1.min.js"></script>

		
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

		
		<script src="js/jquery.slimscroll.min.js"></script>
		
		
		<script src="js/select2.min.js"></script>

		
		<!-- <script src="js/app.js"></script> -->


</body>
</html>