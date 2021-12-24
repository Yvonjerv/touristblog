package controller;

import business.dao.ArticleDAO;
import business.dao.ContentDAO;
import business.impl.ArticleDaoImpl;
import business.impl.ContentDaoImpl;
import model.TArticle;
import model.TContent;
import model.TUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SaveContentServlet", value = "/SaveContent.do")
public class SaveContentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //receive all  input value from jsp

        String contentid = request.getParameter("contentid");
        String articleid = request.getParameter("articleid");
        String textcontent = request.getParameter("textcontent");
        String photourl = request.getParameter("photourl");
        String orderid = request.getParameter("orderid");

        HttpSession session = request.getSession();
        TUser loginuser = (TUser) session.getAttribute("loginuser");

        //check parameter
        String errMgs = "";
        String backUrl = "editArticle.jsp";
        if (textcontent == null) {
            errMgs = "textcontent cannot be empty";
        }

        if (errMgs != null && !errMgs.equals("")) {
            RequestDispatcher rd = request.getRequestDispatcher("errors.jsp");
            request.setAttribute("errMsg", errMgs);
            request.setAttribute("backUrl", backUrl);
            rd.forward(request, response);
            return;
        }

        //
        ContentDAO hdao = new ContentDaoImpl();

        if (contentid == null || contentid.equals("")) {//add new content info
            TContent content = new TContent();
            content.setOrderid(Integer.parseInt(orderid));
            content.setPhotourl(photourl);
            content.setArticleid(Integer.parseInt(articleid));
            content.setTextcontent(textcontent);

            if (hdao.addContent(content) > 0) {
                RequestDispatcher rd = request.getRequestDispatcher("prompt.jsp");
                request.setAttribute("promptMsg", "Sucessfully added");
                request.setAttribute("backUrl", "articleDetail.jsp?articleid=" + articleid);
                rd.forward(request, response);
                return;
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("errors.jsp");
                request.setAttribute("errMgs", "Failed");
                request.setAttribute("backUrl", "index.jsp");
                rd.forward(request, response);
                return;
            }

        } else { //update Content info to database

            TContent content = hdao.getContentById(Integer.parseInt(contentid));

            content.setOrderid(Integer.parseInt(orderid));
            content.setPhotourl(photourl);
            content.setTextcontent(textcontent);

            if (hdao.modifyContent(content)) {
                RequestDispatcher rd = request.getRequestDispatcher("prompt.jsp");
                request.setAttribute("promptMsg", "Sucessfully updated");
                request.setAttribute("backUrl", "articleDetail.jsp?articleid=" + articleid);
                rd.forward(request, response);
                return;
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("errors.jsp");
                request.setAttribute("errMsg", "update Failed");
                request.setAttribute("backUrl", "index.jsp");
                rd.forward(request, response);
                return;
            }
        }
    }
}
