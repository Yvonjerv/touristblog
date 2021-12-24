<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspresources.jsp" %>
<%
    //get business logical class
    String searchIn = request.getParameter("searchin");
    ArticleDAO adao = new ArticleDaoImpl();
    List<TArticle> list;

//out.println("kkkk");
    if (searchIn != null && !searchIn.equals("")) {
        list = adao.getArticleListByCondition(searchIn, searchIn);
        //   out.println(list.size());
    } else {
        list = adao.getArticleListByCondition(null, null);
        TArticle artiTop = adao.getArticleWithHighestComment();
        request.setAttribute("articleTop", artiTop);
    }
    //out.println(artiTop.getTitle());
    request.setAttribute("searchIn", searchIn);
    request.setAttribute("searchCount", list.size());
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
    <title>Blog</title>
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


    <!-- favicons
    ================================================== -->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="icon" href="favicon.ico" type="image/x-icon">

</head>


<body id="top">

<!-- header
================================================== -->
<%--    SYS MENU --%>
<%@ include file="sysmenu.jsp" %>


<!-- page header
================================================== -->
<c:if test="${searchIn ==null || searchIn.equals('')}">

<section class="page-header page-hero" style="background-image:url(../content_img/${articleTop.firstContent.photourl})">

    <div class="row page-header__content">
        <article class="col-full">

            <div class="page-header__info">
                <div class="page-header__cat">
                    <a href="#0">MOST COMMENTED ARTICLE</a><a href="#0"> </a>
                </div>
                <div class="page-header__date">
                        ${articleTop.publishtime}
                </div>
            </div>

            <h1 class="page-header__title">
                <a href="articleDetail.jsp?articleid=${articleTop.articleid}" title="">
                        ${articleTop.title}
                </a>
            </h1>
            <p>${articleTop.firstContent.textcontent}</p>
            <p>
                <a href="articleDetail.jsp?articleid=${articleTop.articleid}" class="btn btn--stroke page-header__btn">Read
                    More</a>
            </p>
        </article>
    </div>

</section>
<!-- end page-header -->
</c:if>
<!-- blog
================================================== -->
<section class="blog-content-wrap">

    <div class="row blog-content">
        <div class="col-full">
            <c:if test="${searchIn !=null && !searchIn.equals('')}">
                <h2 class="h01">We found ${searchCount} articles containing '${searchIn}'</h2>
            </c:if>
            <div class="blog-list block-1-2 block-tab-full">
                <c:forEach items="${articleList}" var="article">
                    <article class="col-block">

                        <div class="blog-date">
                            <a href="articleDetail.jsp?articleid=${article.articleid}">${article.publishtime}</a>
                        </div>

                        <h2 class="h01"><a href="articleDetail.jsp?articleid=${article.articleid}">${article.title}</a>
                        </h2>
                        <div><a href="articleDetail.jsp?articleid=${article.articleid}">
                            <img style="width: 100%; overflow: hidden; object-fit: contain; margin-bottom:20px; "
                                 src="../content_img/${article.firstContent.photourl}">
                        </a></div>
                        <p>
                                ${article.firstContent.textcontent} </p>

                        <div class="blog-cat">
                            <a href="articleDetail.jsp?articleid=${article.articleid}">${article.address}</a>
                        </div>

                    </article>
                </c:forEach>

            </div> <!-- end blog-list -->

        </div> <!-- end col-full -->
    </div> <!-- end blog-content -->


</section> <!-- end blog-content-wrap -->


<!-- footer
================================================== -->
<%@ include file="sysfooter.jsp" %>
<!-- Java Script
<%--================================================== -->--%>
<%--<script src="js/jquery-3.2.1.min.js"></script>--%>
<%--<script src="js/plugins.js"></script>--%>
<%--<script src="js/main.js"></script>--%>

</body>

</html>

