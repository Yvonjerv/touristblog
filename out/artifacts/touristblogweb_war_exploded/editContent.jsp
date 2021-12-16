<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspresources.jsp" %>
<%
    String id = request.getParameter("contentid");
    String articleid = request.getParameter("articleid");

    //get current login user and all hotel information of this login user
    TUser loginuser = (TUser) session.getAttribute("loginuser");
    //
    if (id == null || id.equals("")) {
        //do nothing
    } else {
        int contentid = Integer.parseInt(id);
        ContentDAO hdao = new ContentDaoImpl();
        TContent content = hdao.getContentById(contentid);

        if (content != null || content.getContentid() != 0) {
            request.setAttribute("content", content);
        }
    }
    request.setAttribute("articleid", articleid);

%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="js/ajaxfileupload.js"></script>
    <script type="text/javascript">
        $(function () {
            /*After the upload button is clicked, the
            file control is invoked to open the file selector.
            */
            $("#choicePhoto").click(function () {
                $("#fileToUpload").click();
            });
            /*When a file is selected, the change method of the
            file control is started
             */
            $("#fileToUpload").change(function (e) {
                $.ajaxFileUpload({
                    url: 'fileuploadservlet.do',
                    type: "post",
                    secureuri: false,
                    fileElementId: 'fileToUpload',
                    dataType: 'text',//return data's type
                    data: null,
                    success: function (data) {
                        alert("file upload successfully"); //display messge
                        var dataNew = "../content_img/" + data
                        $('#upload').attr("src", dataNew)
                        $("#photourl").val(data);
                    },
                    error: function (data, status, e) {
                        alert("file upload failed");//display messge
                    }
                });
            });
        });
    </script>
</head>
<body>

<%--    SYS MENU --%>
<%@ include file="sysmenu.jsp" %>
<section id="contact" class="s-contact target-section">
    <div class="overlay"></div>
    <div class="row narrow section-intro">
        <div class="col-full">
            <h3>Edit Content</h3>
            <h1>Editing Article No${articleid} </h1>

        </div>
    </div>

    <div class="row contact__main">
        <div class="col-seven tab-full contact__form">
            <form name="contactForm" id="contactForm" method="post" action="SaveContent.do">
                <fieldset>

                    <div class="form-field" style="display: none;">
                        <input name="articleid" type="number" readonly="readonly" id="userid" placeholder="articleid"
                               value="${articleid}" minlength="2"
                               required="" aria-required="true" class="full-width">
                    </div>

                    <div class="form-field">
                        <input name="contentid" type="number" readonly="readonly" placeholder="contentid"
                               value="${content.contentid}" minlength="2"
                               required="" aria-required="true" class="full-width">
                    </div>

                    <div class="form-field">
                        <input name="orderid" type="number" placeholder="orderid" value="${content.orderid}"
                               minlength="2"
                               required="" aria-required="true" class="full-width">
                    </div>
                    <div class="form-field">
                        <%--     <input style="color: white" rows="3" cols="70" value="${content.textcontent}" name="textcontent">--%>
                        <input id="textcontent" name="textcontent" type="text" placeholder="textcontent"
                               value="${content.textcontent}" minlength="2"
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

        </div>
        <div class="col-five">
            <section style=" overflow: hidden">
                <div style="width:100%">

                    <c:if test="${content==null}">
                        <div><img id="upload" width="450px" height="300px" src="../content_img/nopicture.jpg"></div>
                    </c:if>
                    <c:if test="${content!=null}">
                        <div><img id="upload" width="450px" height="300px" src="../content_img/${content.photourl}">
                        </div>
                    </c:if>
                </div>

                <div>
                    <input id="photourl" class="editbox2" style="width: 100%; color: #c5bec1;" type="text"
                           name="photourl" value="${content.photourl}"
                           readonly="readonly">
                    <input class="btn btn--stroke" type="button" value="Choice Picture" id="choicePhoto"
                           style="width: 90%; color: #c5bec1; ">
                    <!-- Hide file tag -->
                    <input id="fileToUpload" style="display:none" type="file" name="upfile"/>
                </div>
                </form>

            </section>
        </div>
    </div>

</section>

</body>

</html>
