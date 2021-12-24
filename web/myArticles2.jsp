<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspresources.jsp" %>
<%
    //get business logical class
    TUser loginuser = (TUser) session.getAttribute("loginuser");


    ArticleDAO adao = new ArticleDaoImpl();
    List<TArticle> list = adao.getAllArticles(null, loginuser.getUserid());
    request.setAttribute("articleList", list);
%>
<!DOCTYPE html>
<!--[if lt IE 9 ]><html class="no-js oldie" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->

<head>

    <!--- basic page needs
    ================================================== -->
    <meta charset="utf-8">
    <title>Article</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- mobile specific metas
    ================================================== -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

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


<body id="top">

<!-- header
================================================== -->
<%--    SYS MENU --%>
<%@ include file="sysmenu.jsp" %>


<!-- page header
================================================== -->
<section class="page-header page-hero" style="background-image:url(../content_img/blog-bg-02.jpg)">

    <div class="row page-header__content">
        <article class="col-full">

            <div class="page-header__info">
                <div class="page-header__cat">
                    <a href="#0">Current User</a><a href="#0"> &nbsp;</a>
                </div>
                <div class="page-header__date">
                    ${loginuser.createtime}
                </div>
            </div>

            <h1 class="page-header__title">
                <a href="#0" title="">
                    ${loginuser.username} - ${loginuser.userid}
                </a>
            </h1>
            <p>${loginuser.mail}<br>
                ${loginuser.mobile}</p>
            <p>
                <a href="editUser.jsp" class="btn btn--stroke page-header__btn">Edit Info</a>
            </p>
        </article>
    </div>

</section> <!-- end page-header -->

<!-- blog
================================================== -->
<section class="blog-content-wrap">

    <div class="row blog-content">
        <div class="col-full">
            <c:if test="${loginuser != null}">
                <a href="editArticle.jsp" class="btn full-width" style=""><i class="fas fa-plus"></i> new article</a>
            </c:if>
            <div class="blog-list block-1-2 block-tab-full">
                <a href="myArticles.jsp"><i class="fas fa-list"></i>list</a>
                <table BORDER="0">
                    <c:forEach items="${articleList}" var="article">
                        <tr>
                            <td style="padding-left: 0px;padding-right: 0px;">
                                <h3 style=" padding: 0; margin: 0;"><a
                                        href="editArticle.jsp?articleid=${article.articleid}">${article.title}</a>
                                </h3>
                            </td>
                            <td>
                                <h5 STYLE="padding: 0; margin: 0;color: #d7d5d5;"><a
                                        href="editArticle.jsp?articleid=${article.articleid}">${article.address}</a>
                                </h5>
                            </td>
                            <td style="float: right; padding-right: 0;">
                                <a style="width: 100px;" href="articleDetail.jsp?articleid=${article.articleid}"
                                   class="btn btn--stroke">Edit</a>
                                <a style="width: 100px; padding: 0;" class="btn btn--stroke"
                                   href="javascript:deletearticle(${article.articleid})">
                                    delete </a>
                            </td>
                        </tr>

                    </c:forEach>

                </table>
            </div> <!-- end blog-list -->

        </div> <!-- end col-full -->
    </div> <!-- end blog-content -->


</section> <!-- end blog-content-wrap -->

<script type="text/javascript">
    function deletearticle(articleid) {
        var a = confirm("ARE YOU SURE ？\nYOU WILL LOSE ALL CONTENTS AND COMMENTS");
        if (a == true) {
            document.location.href = "DeleteArticle.do?articleid=" + articleid;
        }
    }
</script>
<!-- footer
================================================== -->
<%@ include file="sysfooter.jsp" %>

</body>
</html>