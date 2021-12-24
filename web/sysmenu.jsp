<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspresources.jsp" %>
<%
    session.getAttribute("loginuser");
%>
<head>

    <!-- CSS
  ================================================== -->
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/vendor.css">
    <link rel="stylesheet" href="css/main.css">

    <!-- script
    ================================================== -->
    <script src="js/modernizr.js"></script>
    <script src="js/pace.min.js"></script>

    <!-- favicons
    ================================================== -->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src=" https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"></script>
</head>
<!-- header
================================================== -->
<header class="s-header" style="position: fixed ; background: rgba(10,8,9,0.25)">

    <div class="header-logo">
        <a class="site-logo" href="index.jsp"><img src="../content_img/logo.png" alt="Homepage"></a>
    </div>

    <nav class="header-nav-wrap">
        <ul class="header-nav">
            <form method="get" action="index.jsp">


                <c:if test="${loginuser==null}">
                    <li><a href="index.jsp" title="home">Home</a></li>
                    <li><a href="editUser.jsp" title="about">Register</a></li>
                    <li><a href="login.jsp" title="blog">Login</a></li>
                </c:if>

                <c:if test="${loginuser!=null}">
                    <li class="current" style="color: #862121">Welcome, ${loginuser.userid}</li>
                    <li><a href="index.jsp" title="home">Home</a></li>
                    <li><a href="myArticles.jsp" title="works">My articles</a></li>
                    <li><a href="editUser.jsp" title="about">Edit profile</a></li>
                    <li><a href="logout.do" title="contact">LOGOUT</a></li>
                </c:if>
                <li><input
                        style="background: rgba(114,114,114,0.3); color: white; border-radius: 50px; padding: 0 5px; height: 40px;"
                        type="text" placeholder="search" name="searchin"></li>
            </form>
        </ul>
    </nav>

    <a class="header-menu-toggle" href="#0"><span>Menu</span></a>

</header>
<!-- end s-header -->
