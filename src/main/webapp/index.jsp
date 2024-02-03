<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('assets/fd0c9376e8c15097b446b32177a32cfb.jpg');
            background-repeat: no-repeat;
            background-size: cover;
            background-position: center top; /* Move the background image to the bottom */
            height: 100vh;
        }

        nav {
            overflow: hidden;
            padding: 10px;
        }

        nav a {
            float: right;
            display: block;
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            background-color: white;
            margin-left: 10px;
        }

        nav a:hover {
            background-color: #ddd;
            color: black;
        }

        .welcome-message {
            color: white;
            margin-left: 300px;
            font-size: 34px;
            display: none; /* Initially hide the welcome message */
        }
    </style>
</head>
<body>

<nav>
    <a href="registration.jsp">Registration</a>
    <a href="login.jsp">Login</a>
    <a href="index.jsp">Home</a>
</nav>

<div class="welcome-message">
    <h1>Welcome to Trading Platform</h1>
</div>

<script>
    // Get the current page URL
    var currentPage = window.location.href;

    // Check if the current page is the home page
    if (currentPage.endsWith("index.jsp")) {
        // Show the welcome message
        document.querySelector(".welcome-message").style.display = "block";
    }
</script>

</body>
</html>
