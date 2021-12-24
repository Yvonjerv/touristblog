<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspresources.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Log in</title>

</head>

<body>
<%--    SYS MENU --%>
<%@ include file="sysmenu.jsp" %>
<section id="contact" class="s-contact target-section">
    <div class="overlay"></div>
    <div class="row narrow section-intro">
        <c:if test="${loginuser ==null}">
            <div class="col-full">
                <h3>REGISTER</h3>
                <h1>Welcome to tourism blog </h1>
            </div>
        </c:if>
        <c:if test="${loginuser !=null}">
            <div class="col-full">
                <h3>Edit</h3>
                <h1>Editing profile</h1>
            </div>
        </c:if>
    </div>

    <div class="row contact__main">
        <div class="col-twelve tab-full contact__form">
            <form name="contactForm" id="contactForm" method="post" action="SaveUser.do">
                <fieldset>
                    <c:if test="${loginuser ==null}">
                        <div class="form-field">
                            <input name="userid" type="text" id="userid" placeholder="userid"
                                   value="${loginuser.userid}"
                                   minlength="2"
                                   required="" aria-required="true" class="full-width">
                        </div>
                    </c:if>
                    <div class="form-field">
                        <input name="username" type="text" placeholder="fullname" value="${loginuser.username}"
                               minlength="2" required="" aria-required="true" class="full-width">
                    </div>
                    <div class="form-field">
                        <input name="pwd" type="password" id="password" placeholder="password" value="${loginuser.pwd}"
                               required=""
                               aria-required="true" class="full-width">
                    </div>
                    <div class="form-field">
                        <input name="pwd2" type="password" placeholder="confirm password" value="${loginuser.pwd}"
                               required=""
                               aria-required="true" class="full-width">
                    </div>
                    <div class="form-field">
                        <input name="mail" type="text" placeholder="mail" value="${loginuser.mail}" minlength="2"
                               required="" aria-required="true" class="full-width">
                    </div>
                    <div class="form-field">
                        <input name="mobile" type="text" placeholder="mobile" value="${loginuser.mobile}" minlength="2"
                               required="" aria-required="true" class="full-width">
                    </div>

                    <div style="height: 50px"></div>
                    <div class="form-field">
                        <input type="submit" class="full-width btn--primary" value="SAVE">
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