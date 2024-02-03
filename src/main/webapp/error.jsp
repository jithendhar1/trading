<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #d9534f;
        }

        p {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Error</h1>
        <p>An error occurred while processing your request. Please try again later.</p>
        
        <%-- Access exception details --%>
        <%@ page isErrorPage="true" %>
        <%
            Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
            if (throwable != null) {
        %>
                <p>Error Details:</p>
                <pre><%= throwable.toString() %></pre>
        <%
            }
        %>
        
        <p>If the problem persists, contact support.</p>
    </div>
</body>
</html>
