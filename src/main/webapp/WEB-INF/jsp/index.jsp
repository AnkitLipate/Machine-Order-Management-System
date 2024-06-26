<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .container {
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                text-align: center;
            }

            h1 {
                font-size: 24px;
                color: #333;
                margin-bottom: 20px;
            }

            .user-info {
                font-size: 18px;
                color: #666;
                margin-bottom: 20px;
            }

            a {
                text-decoration: none;
                color: #007bff;
                font-weight: bold;
                margin: 0 10px;
            }



            .nav-links {
                margin-bottom: 20px;
            }

            .nav-links a {
                display: inline-block;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border-radius: 4px;
                transition: background-color 0.3s;
            }

            .nav-links a:hover {
                background-color: #0056b3;
            }

            #log:hover{
                background-color:#D2122E;
            }
            #log{
                background-color: red;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <sec:authentication property="principal" var="curUser" scope="request"/>
            <h1>Welcome</h1>
            <div class="user-info">${curUser.name} - ${curUser.authorities}</div>
            <div class="nav-links">
                <sec:authorize access="hasAnyRole('ROLE_BF_MANAGE_USER')">
                    <a href="addUser.htm">Add User</a>
                </sec:authorize>

                <sec:authorize access="hasAnyRole('ROLE_BF_MANAGE_USER')">
                    <a href="listUser.htm">List Of User</a> 
                </sec:authorize>
                   
                <sec:authorize access="hasAnyRole('ROLE_BF_MANAGE_USER')">
                    <a href="items.htm"> List Of Items</a> 
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ROLE_BF_MANAGE_USER')">
                    <a href="level.htm">Search Items by level</a> 
                </sec:authorize>
                <a href="addOrder.htm">Purchase Order</a> 
                <sec:authorize access="hasAnyRole('ROLE_BF_MANAGE_USER')">
                    <a href="listOrder.htm">  List Of Order</a> 
                </sec:authorize>
                    
                <a href="myOrder.htm">My Order List</a> 

            </div>
            <div class="nav-links">
                
                <a href="logout" id="log" >Logout</a>
            </div>

        </div>

    </body>
</html>
