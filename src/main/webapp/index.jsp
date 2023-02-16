<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <h1>Day la trang giao dien</h1>
    <a href="views/user/login.jsp">login</a>
    <jsp:include page="footer.jsp"/>
</body>
</html>