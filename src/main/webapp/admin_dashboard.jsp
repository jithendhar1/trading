
<%@ page import="java.util.Map" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.math.BigDecimal" %>
<%
    // Getting the username from the session
    String username = (String)session.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <meta name="description" content="Trading Admin Dashboard">
    <meta name="keywords" content="admin, trading, bootstrap, business, corporate, management, html5, responsive, CRM">
    <meta name="author" content="Your Company Name">

    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="assets/logo.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="css/font-awesome.min.css">

    <!-- Lineawesome CSS -->
    <link rel="stylesheet" href="css/line-awesome.min.css">

    <!-- Main CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
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
                        <div class="col-sm-12">
                            <!-- Display welcome message -->
                            <div id="welcomeMessage" style="text-align: center; margin-top: 20px; font-size: 24px;">
                                Welcome <%= username %>!
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item active">Dashboard</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                        <div class="card dash-widget">
                            <div class="card-body">
                                <a href="customers.jsp" style="text-decoration: none; color: inherit;">
                                    <span class="dash-widget-icon"><i class="fa fa-users"></i></span>
                                    <div class="dash-widget-info">
                                        <% int customerCount = 500; %>
                                        <h3><%= customerCount %></h3>
                                        <span>Customers</span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                        <div class="card dash-widget">
                            <div class="card-body">
                                <a href="deposit.jsp" style="text-decoration: none; color: inherit;">
                                    <span class="dash-widget-icon"><i class="fa fa-money"></i></span>
                                    <div class="dash-widget-info">
                                        <% BigDecimal totalDeposits = new BigDecimal("1000000.00"); %>
                                        <h3><%= totalDeposits %></h3>
                                        <span>Total Deposits</span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                        <div class="card dash-widget">
                            <div class="card-body">
                                <a href="withdrawal.jsp" style="text-decoration: none; color: inherit;">
                                    <span class="dash-widget-icon"><i class="fa fa-bank"></i></span>
                                    <div class="dash-widget-info">
                                        <% BigDecimal totalWithdrawals = new BigDecimal("500000.00"); %>
                                        <h3><%= totalWithdrawals %></h3>
                                        <span>Total Withdrawals</span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                        <div class="card dash-widget">
                            <div class="card-body">
                                <a href="referrals.jsp" style="text-decoration: none; color: inherit;">
                                    <span class="dash-widget-icon"><i class="fa fa-share"></i></span>
                                    <div class="dash-widget-info">
                                        <% int referralCount = 50; %>
                                        <h3><%= referralCount %></h3>
                                        <span>Referrals</span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>
</div>
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.slimscroll.min.js"></script>
        <script src="plugins/morris/morris.min.js"></script>
        <script src="js/chart.js"></script>
        <script src="js/app.js"></script>
    </body>
</html>