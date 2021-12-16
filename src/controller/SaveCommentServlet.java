package controller;

import business.dao.CommentDAO;
import business.impl.CommentDaoImpl;
import model.TComment;
import model.TUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SaveCommentServlet", value = "/SaveComment.do")
public class SaveCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //receive all  input value from jsp
//
//        String contentid = request.getParameter("commentid ");
        String articleid = request.getParameter("articleid");
        String textcomment = request.getParameter("textcomment");
        String userid = request.getParameter("userid");

        HttpSession session = request.getSession();
        TUser loginuser = (TUser) session.getAttribute("loginuser");

        //check parameter
        String errMgs = "";
        String backUrl = "articleDetail.jsp";
        if (textcomment == null) {
            errMgs = "textcomment cannot be empty";
        }

        if (errMgs != null && !errMgs.equals("")) {
            RequestDispatcher rd = request.getRequestDispatcher("errors.jsp");
            request.setAttribute("errMsg", errMgs);
            request.setAttribute("backUrl", backUrl);
            rd.forward(request, response);
            return;
        }

        //
        CommentDAO hdao = new CommentDaoImpl();

        //add new content info
        TComment commt = new TComment();
        commt.setTextcomment(textcomment);
        commt.setArticleid(Integer.parseInt(articleid));
        commt.setUserid(loginuser.getUserid());


        if (hdao.addComment(commt) > 0) {
            response.setContentType("text/html");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert('Successsfull');");
            pw.println("</script>");
            RequestDispatcher rd=request.getRequestDispatcher("articleDetail.jsp?articleid=" + articleid);
            rd.include(request, response);
            return;
        } else {

            RequestDispatcher rd = request.getRequestDispatcher("errors.jsp");
            request.setAttribute("errMsg", "Failed");
            request.setAttribute("backUrl", backUrl);
            rd.forward(request, response);
            return;
        }
    }
}
