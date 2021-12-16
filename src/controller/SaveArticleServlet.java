package controller;

import business.dao.ArticleDAO;
import business.impl.ArticleDaoImpl;
import model.TArticle;
import model.TUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SaveArticleServlet", value = "/SaveArticle.do")
public class SaveArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //receive all  input value from jsp

        String articleid = request.getParameter("articleid");
        String title = request.getParameter("title");
        String address = request.getParameter("address");
        // String userid = request.getParameter("userid");


        HttpSession session = request.getSession();
        TUser loginuser = (TUser) session.getAttribute("loginuser");

        //check parameter
        String errMgs = "";
        String backUrl = "editArticle.jsp";
        if (title == null) {
            errMgs = "title cannot be empty";
        }

        if (errMgs != null && !errMgs.equals("")) {
            RequestDispatcher rd = request.getRequestDispatcher("errors.jsp");
            request.setAttribute("errMsg", errMgs);
            request.setAttribute("backUrl", backUrl);
            rd.forward(request, response);
            return;
        }

        //
        ArticleDAO hdao = new ArticleDaoImpl();

        if (articleid == null || articleid.equals("")) {//add new article info
            TArticle article = new TArticle();
            article.setTitle(title);
            article.setAddress(address);
            article.setUserid(loginuser.getUserid());

            if (hdao.addArticle(article) > 0) {
                RequestDispatcher rd = request.getRequestDispatcher("prompt.jsp");
                request.setAttribute("promptMsg", "Sucessfully added");
                request.setAttribute("backUrl", "myArticles.jsp" );
                rd.forward(request, response);
                return;
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("errors.jsp");
                request.setAttribute("errMsg", "Failed");
                request.setAttribute("backUrl", backUrl);
                rd.forward(request, response);
                return;
            }

        } else { //update hotel info to database

            TArticle article = hdao.getArticleById(Integer.parseInt(articleid));
            article.setTitle(title);
            article.setAddress(address);
            article.setUserid(loginuser.getUserid());

            if (hdao.modifyArticle(article)) {
                RequestDispatcher rd = request.getRequestDispatcher("prompt.jsp");
                request.setAttribute("promptMsg", "Sucessfully updated");
                request.setAttribute("backUrl", "articleDetail.jsp?articleid="+articleid);
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
