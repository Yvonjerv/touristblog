package controller;

import business.dao.ArticleDAO;
import business.impl.ArticleDaoImpl;
import model.TArticle;
import model.TUser;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "LoginFilter", value = "/LoginFilter")
public class LoginFilter extends HttpServlet implements Filter {
    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        // Define interception rules and what URL requests can run through
        HttpServletRequest request = (HttpServletRequest) sRequest;
        HttpServletResponse response = (HttpServletResponse) sResponse;
        HttpSession session = request.getSession();

        //get request jsp url address
        String url = request.getServletPath();
        String url2 = request.getRequestURL().toString();

        System.out.println(url + "|" + url2);
        Enumeration<String> paras = request.getParameterNames();
        TArticle article = null;
        TUser loginuser = (TUser) session.getAttribute("loginuser");
        while (paras.hasMoreElements()) {
            String parasName = paras.nextElement();
            System.out.println(parasName);
            // if param is articleid, we need to get article object based on this articleid

            if (parasName.equals("articleid")) {
                String articleid = request.getParameter("articleid");
                if (articleid != null &&  !articleid.equals("")) {
                    ArticleDAO hdao = new ArticleDaoImpl();
                    article = hdao.getArticleById(Integer.parseInt(articleid));
                }
            }
        }
        if (url.equals("")) url += "/";

        int pos = url.lastIndexOf(".");
        String extendsName = url.substring(pos);

        //Select the URL file extension to block
        if (extendsName.equals(".jsp") || extendsName.equals(".do")) {

            if (url.endsWith("myArticles.jsp")) {
                //Permission check
                if (loginuser == null) {
                    RequestDispatcher rd = request.getRequestDispatcher("prompt.jsp");
                    request.setAttribute("promptMsg", "You don't have permission");
                    request.setAttribute("backUrl", "login.jsp");
                    rd.forward(request, response);
                    return;
                }

            } else if (url.endsWith("editArticle.jsp") || url.endsWith("editContent.jsp")) {
                //Permission check
                if (loginuser == null) {
                    RequestDispatcher rd =
                            request.getRequestDispatcher("prompt.jsp");
                    request.setAttribute("promptMsg", "You don't have permission");

                    request.setAttribute("backUrl", "login.jsp");
                    rd.forward(request, response);
                    return;
                } else {
                    if (article == null) {
                        //it represent this page will add new article
                    } else {
                        if (!article.getUserid().equals(loginuser.getUserid())) {
                            RequestDispatcher rd = request.getRequestDispatcher("prompt.jsp");
                            request.setAttribute("promptMsg", "You don't have permission");
                            request.setAttribute("backUrl", "index.jsp");
                            rd.forward(request, response);
                            return;
                        }
                    }
                }
            }
        }
        //通过的URL继续访问servlet容器，实现正常的请求
        filterChain.doFilter(sRequest, sResponse);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }
}
