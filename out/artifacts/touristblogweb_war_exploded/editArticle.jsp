<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspresources.jsp" %>

<%
    String id = request.getParameter("articleid");
    // request.setAttribute("articleid", id);
    //get current login user and all hotel information of this login user
    TUser loginuser = (TUser) session.getAttribute("loginuser");
    //
    if (id == null || id.equals("")) {
        //do nothing
    } else {
        int articleid = Integer.parseInt(id);

        ArticleDAO hdao = new ArticleDaoImpl();
        TArticle article = hdao.getArticleById(articleid);

        if (article != null || article.getTitle() != null) {
            request.setAttribute("article", article);
        }
    }

%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--    SYS MENU --%>
<%@ include file="sysmenu.jsp" %>
<section id="contact" class="s-contact target-section">
    <div class="overlay"></div>
    <div class="row narrow section-intro">
        <c:if test="${article!=null}">
            <div class="col-full">
                <h3>Edit Article</h3>
                <h1>Editing article No ${article.articleid} </h1>
            </div>
        </c:if>
        <c:if test="${article == null}">
            <div class="col-full">
                <h3>New Article</h3>
                <h1>Creating a new article </h1>
            </div>
        </c:if>
    </div>

    <div class="row contact__main">
        <div class="col-twelve tab-full contact__form">
            <form name="contactForm" id="contactForm" method="post" action="SaveArticle.do">
                <fieldset>
                    <div class="form-field" style="display: none">
                        <input name="articleid" type="text" id="userid" placeholder="userid"
                               value="${article.articleid}"
                               class="full-width">
                    </div>

                    <div class="form-field">
                        <input name="title" type="text" placeholder="title" value="${article.title}" minlength="2"
                               required="" aria-required="true" class="full-width">
                    </div>

                    <div class="form-field">
                        <input name="address" type="text" placeholder="address" value="${article.address}" minlength="2"
                               required="" aria-required="true" class="full-width">
                    </div>

                    <div style="height: 50px"></div>
                    <div class="form-field">
                        <input type="submit" class="full-width btn--primary" value="SAVE">

                    </div>
                </fieldset>
            </form>
        </div>
    </div>

</section>

</body>

</html>

