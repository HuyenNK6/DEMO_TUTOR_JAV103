<%--
  Created by IntelliJ IDEA.
  User: Huyen
  Date: 5/18/2026
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        function getData(){
            $.ajax({
                url: "/api/ajax/giang-vien",
                type: "get",
                dataType: "json",
                success: function (response){
                    console.log(response)
                    document.getElementById("data").innerHTML =
                        "Mã GV: " + response.ma
                        + " Tên: " + response.ten
                        + " Tuổi: " + response.tuoi
                        + " Giới tính: " + (response.gioiTinh? "Nữ":"Nam")
                        + " Bộ môn: " + response.boMon
                },
                error: function (){
                    console.log("Loi")
                }
            })
        }
    </script>
</head>
<body>
    <h1>DEMO AJAX</h1>
    <button onclick="getData()"> Get data</button>
    <div id="data"></div>
</body>
</html>
