<%@ page import="com.weblabs.DAO.ServiceDAO" %>
<%@ page import="com.weblabs.DAO.AppointmentDAO" %>
<%@ page import="com.weblabs.DAO.WorkerDAO" %>
<%@ page import="com.weblabs.DAO.VechicleDAO" %>
<%@ page import="com.weblabs.DAO.CustomerDAO" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.math.BigDecimal" %>
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
    <title>Dashboard - HRMS admin template</title>

    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="assets/logo.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="css/font-awesome.min.css">

    <!-- Lineawesome CSS -->
    <link rel="stylesheet" href="css/line-awesome.min.css">

    <!-- Chart CSS -->
    <link rel="stylesheet" href="plugins/morris/morris.css">

    <!-- Main CSS -->
    <link rel="stylesheet" href="css/style.css">

  
</head>
<body>
    <!-- Main Wrapper -->
    <div class="main-wrapper">

    
          <jsp:include page="sidebar.jsp" />
        <!-- /Sidebar -->

        <!-- Page Wrapper -->
        <div class="page-wrapper">

            <!-- Page Content -->
            <div class="content container-fluid">

                <!-- Page Header -->
                <div class="page-header">
                    <div class="row">
                        <div class="col-sm-12">
                            <!-- Display welcome message -->
                                <div id="welcomeMessage" style="text-align: center; margin-top: 20px; font-size: 24px;">
                                           Welcome <%= username%>!
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
                <a href="worker.jsp" style="text-decoration: none; color: inherit;">
                    <span class="dash-widget-icon"><i class="fa fa-cubes"></i></span>
                    <div class="dash-widget-info">
                        <% int WCount = WorkerDAO.totalCount(); %>
                        <h3><%=WCount %></h3>
                        <span>Workers</span>
                    </div>
                </a>
            </div>
        </div>
    </div>

                    
                    
                    <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                        <div class="card dash-widget">
                            <div class="card-body">
                             <a href="appointment.jsp" style="text-decoration: none; color: inherit;">
                                <span class="dash-widget-icon"><i class="fa fa-users"></i></span>
                                <div class="dash-widget-info">
                                  <%int BCount = AppointmentDAO.totalCount(); %>
                                    <h3><%=BCount %></h3>
                                    <span>appointments</span>
                                </div>
                                  </a>
                            </div>
                        </div>
                    </div>
                    
                    
                  <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                        <div class="card dash-widget">
                            <div class="card-body">
                                <a href="customerprofile.jsp" style="text-decoration: none; color: inherit;">
                                <span class="dash-widget-icon"><i class="fa fa-diamond"></i></span>
                                <div class="dash-widget-info">
                                    <%int CCount = CustomerDAO.totalCount(); %>
                                    <h3><%=CCount %></h3>
                                    <span>Customers</span>
                                </div>
                                  </a>
                            </div>
                        </div>
                    </div>  
                    
                    
                    <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                        <div class="card dash-widget">
                            <div class="card-body">
                                <a href="vechicle.jsp" style="text-decoration: none; color: inherit;">
                                <span class="dash-widget-icon"><i class="fa fa-user"></i></span>
                                <div class="dash-widget-info">
                                   <%int VCount = VechicleDAO.totalCount(); %>
                                    <h3><%=VCount %></h3>
                                    <span>Vechicles</span>
                                </div>
                                
                                  </a>
                            </div>
                        </div>
                    </div>
</div>














                <div class="row  justify-content-centeralign-items-center">
                    <div class="col-md-12 d-flex">
                        <div class="card card-table flex-fill">
                            <div class="card-header  text-center">
                           
                                <a href="service.jsp"  class="btn btn-primary ">View sevices</a>
                                
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