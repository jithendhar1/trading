<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*, javax.servlet.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <meta name="description" content="Smarthr - Bootstrap Admin Template">
    <meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
    <meta name="author" content="Bootstrap Admin Template">
    <meta name="robots" content="noindśex, nofollow">
    <title>Login - Weblabs Admin</title>
    <!-- Favicon -->
    <link rel	="shortcut icon" type="image/x-icon" href="assets/logo.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- Main CSS -->
    <link rel="stylesheet" href="css/style.css">       
</head>
<body class="account-page">
    <!-- Main Wrapper -->
    <div class="main-wrapper">
        <div class="account-content"> 
            <div class="container">
                <!-- Account Logo -->
                <div class="account-logo">
                    <a href="index.jsp"><img style="width:100px;" src="assets/WhatsApp Image 2024-01-29 at 19.01.35_802bac3a.jpg" alt="Company Logo"></a>
                </div>
                <!-- /Account Logo -->
                <div class="account-box">
                    <div class="account-wrapper">
                        <h3 class="account-title"> Login</h3>
                        <!-- Account Form -->
                        <form method="post" action="./EmpLoginSrv">
                            <div class="form-group">
                                <label>Email</label>
                                <input class="form-control" id="username"  name="username" required type="text">
                            </div>
                            
                            <% if (request.getAttribute("wrongusername") != null) { %>
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <strong>Oh Snapp!🙃</strong> Alert 
                                    <b class="alert-link">UserName:</b> 
                                    You entered a wrong UserName.
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            <% } %>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col">
                                        <label>Password</label>
                                    </div>
                                </div>
                                <input class="form-control" id="password" name="password" required type="password">
                               
                            </div>
                            <% if (request.getAttribute("wrongpassword") != null) { %>
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <strong>Oh Snapp!😕</strong> Alert 
                                    <b class="alert-link">Password:</b>
                                     You entered wrong password.
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            <% 
                            }
                            %>
                           
 
								   <!-- Existing code ... -->
								<div class="form-group text-center">
								    <div class="row justify-content-center">
								        <div class="col-md-6"> <!-- Adjust the column size as needed -->
								            <button class="btn btn-primary account-btn" name="login" type="submit" style="width: 150px; height: 50px;margin-left: 10px;">Login</button>
								        </div>
								        <div class="col-md-6"> <!-- Adjust the column size as needed -->
								            <a href="registration.jsp" class="btn btn-secondary account-btn" style="width: 150px; height: 50px; margin-left: 10px;">Register</a>
								        </div>
								    </div>
							</div>
								<!-- Existing code ... -->
								   
								    <div class="col-auto pt-2">
								        <a class="text-muted float-right" style="margin-left: 120px;" href="forgot-password.jsp">Forgot password?</a>
								    </div>
								    
					          </form>
								</div>

							</div>
                      
                    </div>
                </div>
            </div>

    <!-- /Main Wrapper -->
    
    <script>
    function validateLogin() {
        // Get the input values
        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;

        // Check if username and password match certain criteria (you can replace this with your validation logic)
        if (username === 'correctUsername' && password === 'correctPassword') {
            // Redirect to a successful login page or perform further actions
            alert('Login successful!');
        } else {
            // Display an alert for invalid username or password
            alert('Invalid username or password. Please try again.');
        }
    }
</script>

    <!-- jQuery -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap Core JS -->
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!-- Custom JS -->
  <script src="js/app.js"></script>
</body>
</html>
