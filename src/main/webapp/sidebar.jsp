<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet" %>
<%@ page import="javax.servlet.ServletException, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.Connection, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException" %>
<%@ page import="utility.DBUtil" %>
<%@ page import="java.util.List" %>

<%
		HttpSession sdsession = request.getSession(true);

    // Retrieve the username attribute from the session
    String username = (String) sdsession.getAttribute("userID");
	  String roleIDString = (String) sdsession.getAttribute("RoleID");
    // Check if the user is logged in or redirect to the login page
    if (roleIDString == null) {
        response.sendRedirect("login.jsp"); // Change "login.jsp" to your actual login page
    } else {
    	   int roleid = Integer.parseInt(roleIDString);

%> 


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Page Title</title>
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
    <!-- table styles CSS -->
    <link rel="stylesheet" href="css/tstyles.css">
<style>
    /* Set a fixed height for the sidebar and enable scrollbar */
    #sidebar {
        height: 1000 px; /* Set a fixed height */
        overflow-y: auto; /* Enable vertical scrollbar */
    }
</style>
<script>
    function toggleSubMenu(element) {
        var submenu = element.nextElementSibling;
        submenu.style.display = (submenu.style.display === 'block') ? 'none' : 'block';
    }
</script>
    
</head>

<body>
    <div class="sidebar" id="sidebar">
    <div class="sidebar-inner slimscroll">
         <div id="sidebar-menu" class="sidebar-menu">
           
                 
                 <ul>
                        <li class="submenu">
                            <a href="#"><i class="la la-dashboard"></i>
                                <span> Dashboard</span>
                                <span class="menu-arrow"></span>
                            </a>
                            <ul style="display: none;">
                                <li><a href="admin_dashboard.jsp">Admin Dashboard</a></li>
                                <!-- <li><a href="employee_dashboard.jsp">User Dashboard</a></li> -->
                            </ul>
                        </li>
                        <li class="menu-title">
                            <span>Main</span>
                        </li>
                        
                        
                        <%
                            try {
                            	
                          Connection con = DBUtil.provideConnection();
                        	    PreparedStatement ps = null;
                                String sql = "SELECT roles.RoleName, rolepermissions.ModuleName, rolepermissions.FormName, rolepermissions.DisplayName FROM roles "
                                        + "INNER JOIN rolepermissions ON roles.RoleID = rolepermissions.RoleID "
                                        + "WHERE roles.RoleID >= ?   ORDER BY roles.RoleID, rolepermissions.ModuleName";
                                
                                 PreparedStatement statement = con.prepareStatement(sql);
                                 statement.setInt(1, roleid);
                                 //ResultSet resultSet = statement.executeQuery(sql);
                                  ResultSet resultSet = statement.executeQuery(); // Corrected line
                                 String currentModule = null;

                                while (resultSet.next()) {
                                    String DisplayName = resultSet.getString("DisplayName");
                                    String moduleName = resultSet.getString("ModuleName");
                                    String formName = resultSet.getString("FormName");
                                    String link = formName ;

                                    // Check if the module has changed
                                    if (!moduleName.equals(currentModule)) {
                                        if (currentModule != null) {
                                            // Close the previous module's <ul>
                        %>
                                        </ul>
                                    </li>
                                  
                        <%
                                        }
                        %>
                                    <li class="submenu">
                                        <a href="#" class="noti-dot onclick="toggleSubMenu(this); return false;">
                                            <!-- <i class="la la-user"></i> -->
                                            <span><%= moduleName %></span>
                                            <!-- <span class="menu-arrow"></span> -->
                                        </a>
                                        <ul style="display: auto;">
                        <%
                                    }
                        %>
                                        <li><a href="<%= link %>"><%= DisplayName %></a></li>
                        <%
                               
                                    // Update the current module
                                  currentModule = moduleName;
                                }
                               
                                // Close the last module's <ul>
                                if (currentModule != null) {
                        %>
                                        </ul> 
                                    </li>
                                    
                        <%
                                }

                                resultSet.close();
                                statement.close();
                                con.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                       
                        </li>
                        </ul> 
                 
                </div> 
            </div>
        </div>
   

    <!-- jQuery -->
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
    <script src="js/app.js"></script>
    <!-- <script>
        // JavaScript for expand/collapse functionality
        var submenuItems = document.querySelectorAll(".submenu");
        submenuItems.forEach(function (item) {
            item.addEventListener("click", function () {
                this.classList.toggle("open");
            });
        });
    </script> -->
    
    <script>
    // JavaScript for expand/collapse functionality
    var submenuItems = document.querySelectorAll(".submenu");
    submenuItems.forEach(function (item) {
        item.addEventListener("click", function () {
            this.classList.toggle("open");
        });
    });
</script>
</body>

</html>
<% }
%>