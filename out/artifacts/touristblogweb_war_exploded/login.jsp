<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspresources.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <!--begin to css of page-->
    <%--    <title>Log in</title>--%>
    <%--    <meta charset="utf-8">--%>
    <%--    <title></title>--%>
</head>
<body>
<%--    SYS MENU --%>
<%@ include file="sysmenu.jsp" %>
<section id="contact" class="s-contact target-section">
    <div class="overlay"></div>
    <div class="row narrow section-intro">
        <div class="col-full">
            <h3>LOGIN</h3>
            <h1>Welcome to tourism blog </h1>

        </div>
    </div>

    <div class="row contact__main">
        <div class="col-twelve tab-full contact__form">
            <form name="contactForm" id="contactForm" method="post" action="login.do">
                <fieldset>
                    <div class="form-field">
                        <input name="userid" type="text" id="userid" placeholder="userid" value="" minlength="2"
                               required="" aria-required="true" class="full-width">
                    </div>
                    <div class="form-field">
                        <input name="pwd" type="password" id="password" placeholder="password" value="" required=""
                               aria-required="true" class="full-width">
                    </div>
                    <div style="height: 50px"></div>
                    <div class="form-field">
                        <input type="submit" class="full-width btn--primary" value="LOGIN">
                        <div class="submit-loader">
                            <div class="text-loader">Sending...</div>
                            <div class="s-loader">
                                <div class="bounce1"></div>
                                <div class="bounce2"></div>
                                <div class="bounce3"></div>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>

</section> <!-- end s-contact -->


</body>
</html>
