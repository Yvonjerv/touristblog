package controller;

import business.dao.ArticleDAO;
import business.dao.CommentDAO;
import business.dao.ContentDAO;
import business.impl.ArticleDaoImpl;
import business.impl.CommentDaoImpl;
import business.impl.ContentDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteArticleServlet", value = "/DeleteArticle.do")
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleid = request.getParameter("articleid");

        ArticleDAO adao = new ArticleDaoImpl();
        ContentDAO cdao = new ContentDaoImpl();
        CommentDAO ddao = new CommentDaoImpl();


        cdao.deleteContentsByArticleid(Integer.parseInt(articleid));
        System.out.println("contents deleted");

        ddao.deleteCommentsByArticleid(Integer.parseInt(articleid));
        System.out.println("comments deleted");

        if (adao.deleteArticle(Integer.parseInt(articleid))) {
//            response.setContentType("text/html");
//            PrintWriter pw = response.getWriter();
//            pw.println("<script type=\"text/javascript\">");
//            pw.println("alert('DELETED');");
//            pw.println("</script>");
            RequestDispatcher rd = request.getRequestDispatcher("myArticles.jsp");
            rd.include(request, response);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
