package controller;

import business.dao.ArticleDAO;
import business.dao.UserDAO;
import business.impl.ArticleDaoImpl;
import business.impl.UserDaoImpl;
import model.TArticle;
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

@WebServlet(name = "SaveUserServlet", value = "/SaveUser.do")
public class SaveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //receive all  input value from jsp
        HttpSession session = request.getSession();
        TUser loginuser = (TUser) session.getAttribute("loginuser");

        String userid = request.getParameter("userid");
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        String mail = request.getParameter("mail");
        String mobile = request.getParameter("mobile");

        //check parameter
        String errMgs = "";
        String backUrl = "login.jsp";
        if (username == null) {
            errMgs = "username cannot be empty";
        }

        if (errMgs != null && !errMgs.equals("")) {
            RequestDispatcher rd = request.getRequestDispatcher("errors.jsp");
            request.setAttribute("errMsg", errMgs);
            request.setAttribute("backUrl", backUrl);
            rd.forward(request, response);
            return;
        }

        //
        UserDAO udao = new UserDaoImpl();

        if (loginuser == null) {//add new user info || loginuser.getUserid().equals("")

            TUser user = new TUser();
            user.setUserid(userid);
            user.setUsername(username);
            user.setPwd(pwd);
            user.setMail(mail);
            user.setMobile(mobile);

            if (udao.registerUser(user) <= 0) {
                System.out.println("failed");
                RequestDispatcher rd = request.getRequestDispatcher("errors.jsp");
                request.setAttribute("errMsg", "Failed");
                request.setAttribute("backUrl", backUrl);
                rd.forward(request, response);
                response.sendRedirect("login.jsp");
                return;
            } else {
                response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Successsfull');");
                pw.println("</script>");
                RequestDispatcher rd=request.getRequestDispatcher("login.jsp" );
                rd.include(request, response);


//                System.out.println("register");
//                RequestDispatcher rd = request.getRequestDispatcher("prompt.jsp");
//                request.setAttribute("promptMsg", "Sucessfully added");
//                request.setAttribute("backUrl", "index.jsp");
//                rd.forward(request, response);
//                response.sendRedirect("login.jsp");
                return;
            }

        } else { //update user info to database

            TUser user = udao.getTUserById(loginuser.getUserid());

            user.setUsername(username);
            user.setPwd(pwd);
            user.setMail(mail);
            user.setMobile(mobile);


            if (udao.modifyUser(user)) {
                RequestDispatcher rd = request.getRequestDispatcher("prompt.jsp");
                request.setAttribute("promptMsg", "Sucessfully updated");
                request.setAttribute("backUrl", "index.jsp");
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
