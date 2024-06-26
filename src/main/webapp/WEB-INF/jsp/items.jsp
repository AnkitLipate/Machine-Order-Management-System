<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>

        <title>Item List</title>
    </head>
    <body>

    </a><br/><br/>
    <h2>Item List</h2>
    <table border="1">
        <a href="index.htm"id="home" >Home</a>&nbsp;&nbsp;
        <a href="level.htm" id="home">Search By Level</a>&nbsp;&nbsp;
          <a href="addItems.htm" id="home">Add Items</a>&nbsp;&nbsp;
        <br/><br/>

        <thead>
            <tr>
                <th>Item ID</th>
                <th>Level</th>
                <th>Code</th>
                <th>Description</th>
                <th>Next Level</th>
                <th>Edit Items</th>
            </tr>
        </thead>
        <tbody>
        <div>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.itemId}</td>
                    <td>${item.level}</td>
                    <td>${item.code}</td>
                    <td>${item.itemDesc}</td>
                    <td>${item.nextLevel}</td>
                    <td><a class="editLink"  href="javascript:editItem(${item.itemId});">Edit</a></td>
                </tr>
            </c:forEach>
                
               
    </tbody>
    
</table>
    
     
            
<form name="editForm" id="editForm" method="POST" action="showEditItem.htm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" name="itemId" id="itemId"/>
</form>
</body>
</html>
<script type="text/javascript">
    function editItem(itemId) {
//            console.log("jai")
        document.getElementById("itemId").value = itemId;
        document.getElementById("editForm").submit();
    }
</script>
<style>body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 20px;
        text-align: center;
    }

    #home{
        color: white;
        background-color:  #007bff;

    }
    #home:hover{
        background-color: #0056b3;

    }


    .editLink {
        color: white;

        background-color: #007bff;
    }

    .editLink:hover {
        background-color:orangered;

    }

    a {

        color: #007bff;
        text-decoration: none;
        padding: 5px 10px;

        border-radius: 4px;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    h2 {
        text-align: center;
        color: #333;
    }

    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    th, td {
        padding: 10px;
        text-align: center;
        border: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
        color: #333;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    tr:hover {
        background-color: #f1f1f1;
    }

    input[type="button"] {
        display: block;
        margin: 20px auto;
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }

    input[type="button"]:hover {
        background-color: #0056b3;
    }
</style>