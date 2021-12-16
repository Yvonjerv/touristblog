<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspresources.jsp" %>
<%
    String articleid = request.getParameter("articleid");
    //get business logical class
    int articlid = Integer.parseInt(articleid);

    ArticleDAO adao = new ArticleDaoImpl();
    TArticle article = adao.getArticleById(articlid);
    request.setAttribute("article", article);

    ContentDAO cdao = new ContentDaoImpl();
    List<TContent> list = cdao.getAllContents(articlid, null, 0);
    request.setAttribute("contentList", list);

    CommentDAO bdao = new CommentDaoImpl();
    List<TComment> listComment = bdao.getAllComments(articlid, null);
    request.setAttribute("commentList", listComment);
    request.setAttribute("commentSize", listComment.size());
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
    <title>Blog Single</title>
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


<article class="blog-single">

    <!-- page header/blog hero
    ================================================== -->
    <div class="page-header page-header--single page-hero"
         style="background-image:url(../content_img/${article.firstContent.photourl})">

        <div class="row page-header__content narrow">
            <article class="col-full">
                <div class="page-header__info">
                    <div class="page-header__cat">
                        <a href="#0">${article.address}</a><a href="#0">...</a>
                    </div>
                </div>
                <h1 class="page-header__title">
                    <a href="#0" title="">
                        ${article.title}
                    </a>
                </h1>
                <ul class="page-header__meta">
                    <li class="date">${article.publishtime}</li>
                    <li class="author">
                        By
                        <span>${article.userid}</span>
                    </li>
                </ul>

            </article>
        </div>

    </div> <!-- end page-header -->

    <div class="row blog-content">
        <div class="col-full blog-content__main" style="margin-bottom:50px ">
            <c:if test="${article.userid==loginuser.userid}">
                <a href="editContent.jsp?articleid=${article.articleid}" class="btn full-width" style="">new content</a>
            </c:if>
            <c:forEach items="${contentList}" var="content">
                <c:if test="${content.orderid!=1}">
                    <p style="padding-left: 60px; width: 100%; object-fit: scale-down; overflow: hidden">
                        <img style="" src="../content_img/${content.photourl}"
                             srcset="../content_img/${content.photourl} 2000w,
                        ../content_img/${content.photourl} 1000w,
                        ../content_img/${content.photourl} 500w"
                             sizes="(max-width: 2000px) 100vw, 2000px" alt="">
                    </p>
                </c:if>

                <c:if test="${article.userid==loginuser.userid}">
                    <p><a class="btn btn--stroke"
                          href="editContent.jsp?articleid=${content.articleid}&contentid=${content.contentid}">
                        <i class="fas fa-paint-brush"></i>edit content</a>
                        <a class="btn btn--stroke2"
                           href="javascript:deletecontent(${content.articleid},${content.contentid})">
<%--                           href="DeleteContent.do?articleid=${content.articleid}&contentid=${content.contentid}">--%>
                            <i class="fas fa-trash-alt"></i>delete content</a></p>
                </c:if>
                <p class="lead">${content.textcontent} </p>

            </c:forEach>
        </div>

        <div>
            <h2 style="color: crimson;">&#128525; Comments <sup>${commentSize}</sup></h2>
        </div>
        <div>
            <c:forEach items="${commentList}" var="commt">
                <h3>${commt.userid}</h3>
                <p>${commt.textcomment}
                    <c:if test="${commt.userid==loginuser.userid}"> -
                        <a class="btn btn--stroke"

                           href="javascript:deletecomment(${commt.articleid},${commt.commentid})">
                            <i class="fas fa-trash-alt"></i></i>delete</a>
                    </c:if>
                </p>
            </c:forEach>
        </div>
        <c:if test="${loginuser!=null}">
            <div style=" padding: 20px 50px ; background: #DDDDDD; width: 50%;">
                <h2>Add New Comment</h2>
                <form method="post" action="SaveComment.do">
                    <div class="form-field">
                        <input name="textcomment" type="text" placeholder="comment" value="" minlength="2" required=""
                               aria-required="true" class="full-width">
                    </div>
                    <div class="form-field" style="display: none">
                        <input name="userid" type="text" placeholder="fullname" value="${loginuser.userid}"
                               minlength="2"
                               required="" aria-required="true" class="full-width">
                    </div>
                    <div class="form-field" style="display: none">
                        <input name="articleid" type="text" placeholder="fullname" value="${article.articleid}"
                               minlength="2" required="" aria-required="true" class="full-width">
                    </div>

                    <input type="submit" class="full-width btn--primary" value="SAVE">

                </form>
            </div>
        </c:if>
    </div>
</article>
<script type="text/javascript">
    function deletecomment(articleid, commentid) {
        var a = confirm("ARE YOU SURE ？");
        if (a == true) {
            document.location.href = "DeleteComment.do?articleid=" + articleid + "&commentid=" + commentid;
        }
    }

    function deletecontent(articleid, contentid) {
        var a = confirm("ARE YOU SURE ？");
        if (a == true) {
            document.location.href = "DeleteContent.do?articleid=" + articleid + "&contentid=" + contentid;
        }
    }
</script>
<!-- footer
================================================== -->
<%@ include file="sysfooter.jsp" %>
</body>

</html>
