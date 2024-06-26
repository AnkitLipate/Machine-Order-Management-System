<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add User</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                color: #333;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .container {
                background-color: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 600px;
                width: 100%;
            }

            h1 {
                margin-bottom: 20px;
                font-size: 28px;
                color: #333;
                text-align: center;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                display: block;
                margin-bottom: 8px;
                font-weight: bold;
                color: #555;
            }

            .form-group input[type="text"], .form-group input[type="hidden"] {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
                box-sizing: border-box;
            }

            .form-buttons {
                margin-top: 20px;
                display: flex;
                justify-content: space-between;
            }

            .form-buttons input[type="submit"], .form-buttons input[type="button"] {
                padding: 12px 20px;
                border: none;
                border-radius: 5px;
                background-color: #007bff;
                color: #fff;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s ease;
            }

            .form-buttons input[type="button"] {
                background-color: #6c757d;
            }

            .form-buttons input[type="submit"]:hover {
                background-color: #0056b3;
            }

            .form-buttons input[type="button"]:hover {
                background-color: #5a6268;
            }

            .message {
                margin-top: 20px;
                font-size: 16px;
                color: #d9534f;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Add New Item</h1>
            <form:form modelAttribute="addItem" method="POST" action="addItems.htm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="form-group">
                    <label>Level:</label>
                    <form:input path="level" />
                </div>
                <div class="form-group">
                    <label>Code:</label>
                    <form:input path="code" />
                </div>
                <div class="form-group">
                    <label>Item Desc:</label>
                    <form:input path="itemDesc" />
                </div>
                <div class="form-group">
                    <label>Next Level:</label>
                    <form:input path="nextLevel" />
                </div>
                <div class="form-buttons">
                    <input type="submit" value="Submit" />
                    <input type="button" value="Cancel" onclick="window.location.href = 'items.htm?msg=Cancelled';" />
                </div>
            </form:form>
            <div class="message">${msg}${param.saurabh}</div>
        </div>
    </body>
</html>
