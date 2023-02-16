<%--
  Created by IntelliJ IDEA.
  User: minhg
  Date: 2/13/2023
  Time: 9:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Account</title>
    <link rel="stylesheet" href="../css/common.css">
</head>
<body>
    <div class="login">
        <h1 class="loginHeading">Register</h1>
        <form action="/sign" class="login-form" method="post" autocomplete="off">
          <label for="nameLogin" class="login-lable">Tên Đăng Nhập</label>
          <input type="text" class="login-input" name="tenDangNhap" id="nameLogin" placeholder="Eg: Minh Giang">
          <label for="password" class="login-lable">Mật khẩu</label>
          <input type="password" class="login-input" name="password" id="password" placeholder="*******">
          <label for="email" class="login-lable">Email</label>
          <input type="email" class="login-input" name="email" id="email" placeholder="abc@gmail.com">
          <input type="submit" value="Register" class="login-submit">
        </form>
      </div>
</body>
</html>