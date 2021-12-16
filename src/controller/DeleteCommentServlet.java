package controller;

import business.dao.CommentDAO;
import business.impl.CommentDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteCommentServlet", value = "/DeleteComment.do")
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentid = request.getParameter("commentid");
        String articleid = request.getParameter("articleid");

        CommentDAO cdao = new CommentDaoImpl();
        //TComment tComment = cdao.getCommentById(Integer.parseInt(commentid));
        if(cdao.deleteComment(Integer.parseInt(commentid))){
            response.setContentType("text/html");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert('DELETED');");
            pw.println("</script>");
            RequestDispatcher rd=request.getRequestDispatcher("articleDetail.jsp?articleid=" + articleid);
            rd.include(request, response);
            return;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response);
    }
}
