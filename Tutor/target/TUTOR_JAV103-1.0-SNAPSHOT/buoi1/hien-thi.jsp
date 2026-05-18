<%--
  Created by IntelliJ IDEA.
  User: Huyen
  Date: 5/15/2026
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/sinh-vien/search">
    Tìm kiếm theo tên:
    <input type="text" name="tenTimKiem"><br>
    <button type="submit">Search</button>
</form>
<br>
<form action="/sinh-vien/add" method="post">
    Mã: <input type="text" name="ma" value="${sv.ma}"> <br/>
    Tên: <input type="text" name="ten" value="${sv.ten}"> <br/>
    Tuổi: <input type="text" name="tuoi" value="${sv.tuoi}"> <br/>
    Địa chỉ: <input type="text" name="diaChi" value="${sv.diaChi}"> <br/>
    <%--Giới tính: <input type="text" name="id" value="${sv.gioiTinh}"> <br/>--%>
    Giới tính:
    <input type="radio" name="gioiTinh" value="true" ${sv.gioiTinh? "checked" : ""}> Nam
    <input type="radio" name="gioiTinh" value="false" ${sv.gioiTinh? "" : "checked"}> Nữ <br/>

    <%--Lớp id: <input type="text" name="id" value="${sv.lopId}"> <br/>--%>
    Lớp:
    <select name="lopId">
        <c:forEach items="${listLop}" var="lop" varStatus="i">
<%--            <option value="${lop.id}" label="${lop.ten}" ${sv.lopId == lop.id ? "selected":""}></option>--%>
            <option value="${lop.id}" label="${lop.ten}" ${sv.lop.id == lop.id ? "selected":""}></option>
        </c:forEach>
    </select>
    <button type="submit"> Add </button>
</form>
<table border="1px solid black">
    <thead>
    <tr>
        <td>STT</td>
        <td>MÃ</td>
        <td>TÊN</td>
        <td>TUỔI</td>
        <td>ĐỊA CHỈ</td>
        <td>GIỚI TÍNH</td>
        <td>LỚP</td>
        <td>HÀNH ĐỘNG</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listSV}" var="sv" varStatus="i">
        <tr>
            <td>${i.index+1}</td>
            <td>${sv.ma}</td>
            <td>${sv.ten}</td>
            <td>${sv.tuoi}</td>
            <td>${sv.diaChi}</td>
            <td>${sv.gioiTinh}</td>
            <td>${sv.lop.id}</td>
            <td>
                <a href="/sinh-vien/detail?id=${sv.id}">Detail</a>
                <a href="/sinh-vien/delete?id=${sv.id}">Delete</a>
                <a href="/sinh-vien/view-update?id=${sv.id}">Update</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <a href="/sinh-vien/paging?pageNumber=${pageNumber - 1}">Prev</a>
        <c:forEach begin="1" end="${totalPage}" var="i">
            <a href="/sinh-vien/paging?pageNumber=${i}">${i}</a>
        </c:forEach>
    <a href="/sinh-vien/paging?pageNumber=${pageNumber + 1}">Next</a>
</body>
</html>
