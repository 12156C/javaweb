package cn.edu.swu.user1;

import cn.edu.swu.user.LoginServlet;
import cn.edu.swu.user.User;
import cn.edu.swu.user.UserRepo;
import cn.edu.swu.user.ValidateCodeServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login1")
public class LoginServlet1 extends HttpServlet {
    public final static String LOGIN_TOKEN="USER_LOGIN_TOKEN";

    public void doGet(HttpServletRequest request ,HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("user");
        String password=request.getParameter("password");

        if(userName!=null&&password!=null){
            System.out.println("0");
            this.doLogin(request,response);
        } else{
            HttpSession session=request.getSession();
            if(session==null||(session.getAttribute(LoginServlet1.LOGIN_TOKEN)!=Boolean.TRUE&&session.getAttribute(LoginServlet1.LOGIN_TOKEN)!="123")){
                System.out.println("1");
                response.sendRedirect("./login-form-18/loginnow.html");
            } else{
                System.out.println("2");
                response.sendRedirect("./jumbotron-template.html");
            }
        }

    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("user");
        String password=request.getParameter("password");
        String code=request.getParameter("code");

        String verifyCode=(String) request.getSession(true).getAttribute(ValidateCodeServlet.LOGIN_VERIFY_CODE);
        if(code==null || !code.equals(verifyCode)){
            System.out.println("3");
            //不考虑大小写code.equalsIgnoreCase()
            System.out.println("验证码错误");
            response.sendRedirect("./login-form-18/loginnow.html");
            return;
        }

        try {
            User1 user= UserRepo1.getInstance().auth(userName,password);
            if(user!=null){
                System.out.println("4");
                System.out.println("用户名密码正确");
                HttpSession session=request.getSession();
                System.out.println(userName);
                if(userName.equals("admin")){
                    session.setAttribute(LOGIN_TOKEN,"123");
                }
                else{
                    session.setAttribute(LOGIN_TOKEN,Boolean.TRUE);
                }
                System.out.println("登录赋值");
                System.out.println(session.getAttribute(LoginServlet.LOGIN_TOKEN));
                response.sendRedirect("./jumbotron-template.html");
            } else{
                System.out.println("5");
                response.sendRedirect("./index-simple.html");//提醒密码错误
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
