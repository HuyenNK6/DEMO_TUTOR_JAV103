<%--
  Created by IntelliJ IDEA.
  User: Huyen
  Date: 5/18/2026
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${message}</h1>
    Chào mừng: ${sessionScope.taiKhoan}, bạn đang truy cập với quyền: ${sessionScope.chucVu}
    <br>
    <a href="/demo-filter/logout">Logout</a>
</body>
</html>
