
<%--  Created by IntelliJ IDEA.--%>
<%--  User: minhg--%>
<%--  Date: 2/13/2023--%>
<%--  Time: 9:04 AM--%>
<%--  To change this template use File | Settings | File Templates.--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.getRequest().getContextPath()}/css/common.css">
</head>
<body>
    <%
        String erro = request.getAttribute("error")+"";
        erro =(erro.equals("null")?"":erro);
        String tenDangNhap= request.getAttribute("userName")+"";
        tenDangNhap = (tenDangNhap.equals("null"))?"":tenDangNhap;
    %>
    <div class="login">
      <span style="color: red"><%=erro%></span>
      <h1 class="loginHeading">Login</h1>
      <form action="${pageContext.getRequest().getContextPath()}/login" class="login-form" method="post" autocomplete="off">
        <label for="nameLogin" class="login-lable">Tên Đăng Nhập</label>
        <input type="text" class="login-input" name="tenDangNhap" id="nameLogin" placeholder="Eg: Minh Giang" required value="<%=tenDangNhap%>">
        <label for="password" class="login-lable">Mật khẩu</label>
        <input type="password" class="login-input" name="password" id="password" placeholder="*******">
        <input type="submit" value="Login" class="login-submit">
      </form>
      <p class="signup">
        <span>Đăng ký tài khoản ở đây </span>
        <a href="/sign" class="register_user" target="_blank">here</a>
      </p>
    </div>
</body>
</html>
