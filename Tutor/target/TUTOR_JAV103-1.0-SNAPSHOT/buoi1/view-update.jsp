<%--
  Created by IntelliJ IDEA.
  User: Huyen
  Date: 5/15/2026
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/sinh-vien/update" method="post">
    Mã: <input type="text" name="id" value="${sv.id}"> <br/>
    Mã: <input type="text" name="ma" value="${sv.ma}"> <br/>
    Tên: <input type="text" name="ten" value="${sv.ten}"> <br/>
    Tuổi: <input type="text" name="tuoi" value="${sv.tuoi}"> <br/>
    Địa chỉ: <input type="text" name="diaChi" value="${sv.diaChi}"> <br/>
    <%--Giới tính: <input type="text" name="id" value="${sv.gioiTinh}"> <br/>--%>
    Giới tính:
    <input type="radio" name="gioiTinh" value="true" ${sv.gioiTinh? "checked" : ""}> Nam
    <input type="radio" name="gioiTinh" value="false" ${!sv.gioiTinh? "checked": ""}> Nữ <br/>

    <%--Lớp id: <input type="text" name="id" value="${sv.lopId}"> <br/>--%>
    Lớp:
    <select name="lopId">
        <c:forEach items="${listLop}" var="lop" varStatus="i">
            <%--            <option value="${lop.id}" label="${lop.ten}" ${sv.lopId == lop.id ? "selected":""}></option>--%>
            <option value="${lop.id}" label="${lop.ten}" ${sv.lop.id == lop.id ? "selected":""}></option>
        </c:forEach>
    </select>
    <button type="submit">Update</button>
</form>
</body>
</html>
