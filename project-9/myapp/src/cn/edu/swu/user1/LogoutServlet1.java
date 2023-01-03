package cn.edu.swu.user1;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout1")
public class LogoutServlet1 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        if(session!=null){
            System.out.println("---------退出登录----------");
            session.setAttribute(LoginServlet1.LOGIN_TOKEN,"USER_LOGIN_TOKEN");
            System.out.println(session.getAttribute("password"));
            //session.invalidate();
        }
        //response.sendRedirect("#");
        response.sendRedirect("./login-form-18/loginnow.html");
    }
}
