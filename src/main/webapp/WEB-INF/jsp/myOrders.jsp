
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Order List</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                line-height: 1.6;
                background-color: #f4f4f4;
                padding: 20px;
            }

            a {
                color: #007bff;
                text-decoration: none;
                padding: 5px 10px;
            background-color: #007bff;
                color: white;
                border-radius: 4px;
                transition: background-color 0.3s ease, color 0.3s ease;
            }
            #home:hover{
                background-color: #0056b3;
                border: 1px solid #0056b3;
            }
            #home{
                color:white;
                background-color: #007bff;
                border: 1px solid #007bff
            }
            #log{
                color: white;
                background-color: red;
                border: 1px solid red;
            }

            a:hover {
                color: white;
                background-color: blue;
                text-decoration: none;
            }


            fieldset {
                border: 1px solid #ddd;
                border-radius: 8px;
                padding: 20px;
                background-color: #fff;
            }

            legend {
                font-size: 24px;
                color: #333;
                padding: 0 10px;
                width: auto;
                border-bottom: none;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
                background-color: #fff;
            }

            table, th, td {
                border: 1px solid #ddd;
            }

            th, td {
                padding: 12px;
                  text-align: center;
            }

            th {
                background-color: #f2f2f2;
            }

            tbody tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            tbody tr:hover {
                background-color: #f1f1f1;
            }

            .editLink {
                color: green;
                border: 1px solid green;
            }

            .editLink:hover {
                background-color: green;
                color: white;
            }

            .msg {
                color: #ff0000;
                margin-top: 20px;
            }

            #log:hover{
                background-color:#D2122E;
                border: 1px solid #D2122E;
                color: white;
            }
            #changes{
                color: green;
            }

            #delete:hover{
                color: white;
                background-color: red;
              
            }


        </style>

    </head>
    <body>
        <sec:authentication property="principal" var="curUser" scope="request"/>
        <a href="index.htm"id="home" >Home</a>&nbsp;&nbsp;
        <a href="logout" id="log">Logout</a>&nbsp;&nbsp;
        <a href="addOrder.htm"id="home" >Purchase Order</a><br/><br/>


        <fieldset>
            <legend>My Order List</legend>
            <div id="changes">${msg}${param.msg}</div>
            <table>
                <thead>
                    <tr>
                        <th>Order Id</th>
                        <th>Code</th>
                        <th>Order Date</th>
                        <th>Name</th>
                        <th>User Id</th>
                               <sec:authorize access="hasAnyRole('ROLE_BF_MANAGE_USER')">
                            <th>Delete Order</th>
                            </sec:authorize>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${myOrderList}" var="odr">
                        <tr>

                            <td>${odr.orderId}</td>
                            <td>${odr.code}</td>
                            <td>${odr.orderDate}</td>
                            <td>${odr.user.label}</td>
                            <td>${odr.user.id}</td>
                           <sec:authorize access="hasAnyRole('ROLE_BF_MANAGE_USER')">
                                <td><a  id="delete" href="javascript:deleteOrder(${odr.orderId});">Delete Order</a></td>
                            </sec:authorize>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form name="deleteOrderForm" id="deleteOrderForm" method="POST" action="deleteMyOrder.htm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" name="deleteOrderId" id="deleteOrderId" value=""/>
            </form>

        </fieldset>
    </body>
</html>

<script type="text/javascript">


    function deleteOrder(orderId) {
        document.getElementById("deleteOrderId").value = orderId;
        document.getElementById("deleteOrderForm").submit();
            alert("Your Order Is Now Deleted");

    }
</script>
