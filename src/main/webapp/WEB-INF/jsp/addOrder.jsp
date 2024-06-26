<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Purchase Machine Order</title>

    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        var selectedItems = [];

        function loadItems(level) {
            $.get("/itemOrder.htm?level=" + level, function (data) {
                var items = data;
                if (items.length === 0 || level >= 21) {
                    $("#submitBtn").show();
                    return;
                }
                var html = "<select class='itemDropdown'>";
                html += "<option value=''>Select Item</option>";
                for (var i = 0; i < items.length; i++) {
                    html += "<option value='" + items[i].nextLevel + "' data-code='" + items[i].code + "' data-desc='" + items[i].itemDesc + "'>" + items[i].code + " - " + items[i].itemDesc + "</option>";
                }
                html += "</select>";
                $("#selectedItems").append(html);
                $(".itemDropdown").last().change(function () {
                    var nextLevel = $(this).val();
                    if (nextLevel !== '') {
                        $("#selectedItems").children().not(":last").remove();
                        loadItems(nextLevel);
                        selectedItems.push({code: $(this).find("option:selected").data("code"), desc: $(this).find("option:selected").data("desc")});
                        updateSelectedTable();
                        concatenateCodes();
                        $(this).hide(); 
                    }
                });
            });
        }

        function updateSelectedTable() {
            $("#selectedTable").empty();
            $("#selectedTable").append("<tr><th>Code</th><th>Description</th></tr>");
            for (var i = 0; i < selectedItems.length; i++) {
                $("#selectedTable").append("<tr><td>" + selectedItems[i].code +
                        "</td><td>" + selectedItems[i].desc + "</td></tr>");
            }
        }

        function concatenateCodes() {
            var concatenatedCode = selectedItems.map(item => item.code).join("--");
            $("#codeInput").val(concatenatedCode);
        }

        $(document).ready(function () {
            loadItems(0);
            $("#submitBtn").hide();
        });
    </script>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <input type="button" value="Home" onclick="window.location.href = 'index.htm?msg=Cancelled';" />
            <h1>Machine Order</h1>
            <form:form name="order" modelAttribute="order" method="POST" action="addOrder.htm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <form:input type="hidden" path="code" id="codeInput" readonly="readonly" />
                <br>
                <div id="selectedItems"></div>
                <br>
                <input type="submit" value="Submit" id="submitBtn" />
            </form:form>
        </div>
        <div class="table-container">
            <h2>Selected Items</h2>
            <table id="selectedTable">
                <tr><th>Code</th><th>Description</th></tr>
            </table>
        </div>
    </div>
</body>
</html>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f4f4f9;
    }

    .container {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
    }

    .form-container {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        max-width: 600px;
        flex: 1;
        margin-right: 20px;
    }

    .table-container {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        max-width: 600px;
        flex: 1;
    }

    h1, h2 {
        color: #333;
    }

    label {
        display: block;
        margin-top: 10px;
        font-weight: bold;
        color: #555;
    }

    input[type="text"], input[type="date"], select {
        width: calc(100% - 20px);
        padding: 8px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type="submit"], input[type="button"] {
        background-color: #28a745;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
        margin-top: 15px;
    }

    input[type="button"] {
        background-color: #007bff;
    }

    input[type="submit"]:hover, input[type="button"]:hover {
        background-color: #218838;
    }

    input[type="button"]:hover {
        background-color: #0056b3;
    }

    table {
        border-collapse: collapse;
        width: 100%;
        margin-top: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    th, td {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 12px;
        color: #333;
    }

    th {
        background-color: #f2f2f2;
    }

    #selectedItems {
        margin-top: 20px;
    }

    .itemDropdown {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    #submitBtn {
        display: block;
        width: 100%;
        text-align: center;
    }
</style>
