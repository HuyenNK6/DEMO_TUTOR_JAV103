<%--
  Created by IntelliJ IDEA.
  User: Huyen
  Date: 5/18/2026
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>FORM LOGIN</h1>
    <form action="/demo-filter/login" method="post">
        username: <input type="text" name="username"><br>
        password: <input type="text" name="password"><br>
        <button type="submit">Login</button>
    </form>
    ${messageLogin}
</body>
</html>
