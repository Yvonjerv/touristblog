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
        <img style="height: 400px;width: 400px; object-fit: contain; " src="../content_img/success.png">
    </div>
    <div>
        <h2 style="color: #017e52; font-weight: bolder; font-size: 30px ">${promptMsg}</h2>
    </div>

</section>
<section style="background: #017e52;">
    <a style="margin: 20px" class="btn btn--stroke page-header__btn" href="${backUrl}">Back to web page</a>
</section>
</body>
</html>
