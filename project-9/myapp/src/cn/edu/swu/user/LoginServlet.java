package cn.edu.swu.user;

import com.mysql.cj.Session;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public final static String LOGIN_TOKEN="USER_LOGIN_TOKEN";

    public void doGet(HttpServletRequest request ,HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("user");
        String password=request.getParameter("password");

        if(userName!=null&&password!=null){
            this.doLogin(request,response);
        } else{
            HttpSession session=request.getSession();
            if(session==null||session.getAttribute(LoginServlet.LOGIN_TOKEN)!=Boolean.TRUE){
                response.sendRedirect("./login.html");
            } else{
                response.sendRedirect("./admin.html");
            }
        }

    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("user");
        String password=request.getParameter("password");
        String code=request.getParameter("code");

        String verifyCode=(String) request.getSession(true).getAttribute(ValidateCodeServlet.LOGIN_VERIFY_CODE);
        if(code==null || !code.equals(verifyCode)){
            //不考虑大小写code.equalsIgnoreCase()
            System.out.println("验证码错误");
            response.sendRedirect("./login.html");
            return;
        }

        try {
            User user=UserRepo.getInstance().auth(userName,password);
            if(user!=null){
                System.out.println("用户名密码正确");
                HttpSession session=request.getSession();
                session.setAttribute(LOGIN_TOKEN,Boolean.TRUE);
                response.sendRedirect("./admin.html");
            } else{
                response.sendRedirect("./index-simple.html");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
