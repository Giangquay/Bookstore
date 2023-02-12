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
<h1><%= "Hello World!" %>
</h1>
<br/>
<table>
    <thead>
    <tr>
        <th>STT</th>
        <th>Tên Đăng Nhập</th>
        <th>Họ và Tên</th>
        <th>Giới Tính</th>
        <th>Địa chỉ</th>
        <th>Ngày Sinh</th>
        <th>Số điện thoại</th>
        <th>Email</th>
        <th>Image</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.listKH}" var="o" varStatus="STT">
        <tr>
            <td>${STT.index+1}</td>
            <td>${o.tenDangNhap}</td>
            <td>${o.hoVaTen}</td>
            <td>${o.gioiTinh}</td>
            <td>${o.diaChi}</td>
            <td>${o.ngaySinh}</td>
            <td>${o.soDienThoai}</td>
            <td>${o.email}</td>
            <td>${o.duongDanAnh}</td>
            <td><a href="<%=request.getContextPath()%>/edit?id${o.maKhachHang}">Sửa</a></td>
            <td><a href="<%=request.getContextPath()%>/delete?id${o.maKhachHang}">Xóa</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>