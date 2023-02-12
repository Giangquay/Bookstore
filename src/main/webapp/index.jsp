<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        td,th{
            padding: 15px;
        }
        tr:nth-child(even) {background-color: #f2f2f2;}
        th {
            background-color: #04AA6D;
            color: white;
        }
        table, td, th {
            border: 1px solid #ddd;
            text-align: center;
        }
        table {
            border-collapse: collapse;
            margin: 50px;
         }
        tr:hover{
            background-color: deepskyblue;
            color: white;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <h1>Day la trang giao dien</h1>
    <jsp:include page="footer.jsp"/>
</body>
</html>