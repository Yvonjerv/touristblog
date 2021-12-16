<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspresources.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>title</title>
    <style>
        .errorcontent{
            margin-left: 100px;
            width:1000px;
        }
        a{
            text-decoration: none;
            /*font: "宋体";*/
            font-size: 16px;
            color:#EE7621;
        }

    </style>
</head>
<body>
<table class="errorcontent">
    <tr>
        <td rowspan="2" style="width:350px">
            <img src="../content_img/error.jpg">
        </td>
        <td><center>
            <p><h2>${errMsg}</h2></p>
        </center>
        </td>
    </tr>
    <tr>
        <td>
            <center>
                <a href="${backUrl}">Back to web page</a>
            </center>
        </td>
    </tr>
</table>
</body>
</html>
