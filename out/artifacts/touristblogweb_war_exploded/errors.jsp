<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspresources.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>title</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/vendor.css">
    <link rel="stylesheet" href="css/main.css">
    <style>
        section {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            margin: auto;
        }
    </style>
</head>
<body>
<section style="background: #ffffff;">
    <div style="margin-top: 70px">
        <img style="height: 400px;width: 400px; object-fit: contain; " src="../content_img/error.png">
    </div>
    <div>
        <h2 style="color: #b00225; font-weight: bolder; font-size: 30px ">${errMgs}</h2>
    </div>

</section>
<section style="background: #b00225">
    <a style="margin: 20px" class="btn btn--stroke page-header__btn" href="${backUrl}">Back to web page</a>
</section>
</body>
</html>
