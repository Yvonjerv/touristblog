package controller;

import business.dao.ContentDAO;
import business.impl.ContentDaoImpl;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteContentServlet", value = "/DeleteContent.do")
public class DeleteContentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contentid = request.getParameter("contentid");
        String articleid = request.getParameter("articleid");

        ContentDAO cdao = new ContentDaoImpl();

        if (cdao.deleteContent(Integer.parseInt(contentid))) {
//            response.setContentType("text/html");
//            PrintWriter pw = response.getWriter();
//            pw.println("<script type=\"text/javascript\">");
//            pw.println("alert('DELETED');");
//            pw.println("</script>");
            RequestDispatcher rd = request.getRequestDispatcher("articleDetail.jsp?articleid=" + articleid);
            rd.include(request, response);
            return;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
