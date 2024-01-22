<%
HttpSession sdsession = request.getSession(true);

// Retrieve the username attribute from the session
String username = (String) sdsession.getAttribute("username");
String roleIDString = (String) sdsession.getAttribute("RoleID");
// Check if the user is logged in or redirect to the login page
if (roleIDString == null) {
response.sendRedirect("login.jsp"); // Change "login.jsp" to your actual login page
} else {
   int roleid = Integer.parseInt(roleIDString);

%>


<!DOCTYPE html>
<%-- <%@page import="com.weblabs.service.impl.csDAO"%>
<%@page import="com.weblabs.beans.CompanySettingsBean"%> --%>
<%@ page import="java.util.List"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>


<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Page Title</title>
    <!-- Add your CSS and other meta tags here -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/M.css">
    
</head>
<body>
    <div class="header">
        <!-- Logo -->
        <div class="header-left">
        <a href="index.jsp" class="logo">
        <%-- <%
       List<CompanySettingsBean> company = csDAO.getAllCS();
        
        for(CompanySettingsBean com:company){
        %> --%>
          <img src="<%-- <%=com.getlogoUrl(request)%> --%>assets/logo.png" width="110" height="58" alt="">
            </a>
            <%} %>
        </div>
        <!-- /Logo -->

        <!-- <a id="toggle_btn" href="javascript:void(0);">
            <span class="bar-icon">
                <span></span>
                <span></span>
                <span></span>
            </span>
        </a> -->

        <!-- Header Title -->
        <div class="page-title-box">
            <h3>Weblabs Technologies</h3>
        </div>
        <!-- /Header Title -->

        <a id="mobile_btn" class="mobile_btn" href="#sidebar"><i class="fa fa-bars"></i></a>

        <!-- Header Menu -->
        <ul class="nav user-menu">

            
            <li class="nav-item dropdown">
               <!--  <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                    <i class="fa fa-bell-o"></i> <span class="badge badge-pill">3</span>
                </a> -->
                <div class="dropdown-menu notifications">
                    <div class="topnav-dropdown-header">
                        <span class="notification-title">Notifications</span>
                        <a href="javascript:void(0)" class="clear-noti"> Clear All </a>
                    </div>
                    <div class="noti-content">
                        <ul class="notification-list">
                            <li class="notification-message">
                                <a href="activities.jsp">
                                    <div class="media" >
                                        <span class="avatar">
                                            <img alt="" src="assets/profiles/avatar-02.jpg">
                                        </span>
                                        <div class="media-body">
                                            <p class="noti-details"><span class="noti-title">John Doe</span> added new task <span class="noti-title">Patient appointment booking</span></p>
                                            <p class="noti-time"><span class="notification-time">4 mins ago</span></p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- Add more notification items here -->
                        </ul>
                    </div>
                    <div class="topnav-dropdown-footer">
                        <a href="activities.jsp">View all Notifications</a>
                    </div>
                </div>
            </li>
            <!-- /Notifications -->

            <!-- Message Notifications -->
           <!--  <li class="nav-item dropdown">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                    <i class="fa fa-comment-o"></i> <span class="badge badge-pill">8</span>
                </a>
                <div class="dropdown-menu notifications">
                    <div class="topnav-dropdown-header">
                        <span class="notification-title">Messages</span>
                        <a href="javascript:void(0)" class="clear-noti"> Clear All </a>
                    </div>
                     <div class="noti-content">
                        <ul class="notification-list">
                            <li class="notification-message">
                                <a href="chat.jsp">
                                    <div class="list-item">
                                        Message notification content goes here
                                    </div> 
                                </a>
                            </li>
                            Add more message notification items here
                        </ul>
                    </div>
                    <div class="topnav-dropdown-footer">
                        <a href="chat.jsp">View all Messages</a>
                    </div>
                </div>
            </li> -->
            <!-- /Message Notifications -->

            <!-- User Profile -->
            <li class="nav-item dropdown has-arrow main-drop">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                   <div class="user-img">
                            <img src="assets/user.jpg" alt="User Picture">
                              <span class="status online"></span>
                   </div>
                   

                </a>
                <div class="dropdown-menu">
                 <a class="dropdown-item" href="<%= (username.equals("admin") ? "profile-list.jsp" : "profile-list-emp.jsp") %>">My Profile</a>
                   <!--  <a class="dropdown-item" href="profile-list-emp.jsp">My Profile</a> -->
                <!--     <a class="dropdown-item" href="settings.jsp">Settings</a> -->
                    <a class="dropdown-item" href="logout.jsp">Logout</a>
                </div>
            </li>
            <!-- /User Profile -->
        </ul>
        <!-- /Header Menu -->

        <!-- Mobile Menu -->
        <div class="dropdown mobile-user-menu">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
            <div class="dropdown-menu dropdown-menu-right">
                <a class="dropdown-item" href="profile-list-emp.jsp">My Profile</a>
                <a class="dropdown-item" href="settings.jsp">Settings</a>
                <a class="dropdown-item" href="login.jsp">Logout</a>
            </div>
        </div>
        <!-- /Mobile Menu -->
    </div>

    <!-- Add your content here -->

    <!-- Add your JavaScript includes and scripts here -->
     <script src="js/jquery-3.2.1.min.js"></script>

    <!-- Bootstrap Core JS -->
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <!-- Slimscroll JS -->
    <script src="js/jquery.slimscroll.min.js"></script>

    <!-- Select2 JS -->
    <script src="js/select2.min.js"></script>

    <!-- Datetimepicker JS -->
    <script src="js/moment.min.js"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>

    <!-- Custom JS -->
    <!-- <script src="js/app.js"></script> -->
</body>

</html>