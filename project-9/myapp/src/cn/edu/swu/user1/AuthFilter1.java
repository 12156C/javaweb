package cn.edu.swu.user1;

import cn.edu.swu.user.LoginServlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/*")
public class AuthFilter1 extends HttpFilter {
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session=request.getSession();
        System.out.println("auth Filter");
        String uri=request.getRequestURI();
        System.out.println(uri);
        if(uri.endsWith("login.html")||
                uri.endsWith("index.html")||
                uri.endsWith("office.html")||
                uri.endsWith("new.html")||
                uri.endsWith("faq.html")||
                uri.endsWith("fonts")||
                uri.endsWith("loginnow.html")||
                uri.endsWith("loginnow1.html")||
                uri.endsWith("register.html")||
                uri.endsWith("/register")||
                uri.endsWith("login1")||
                uri.endsWith("admin-1.html")||
                uri.endsWith("login")||
                uri.endsWith("remember1")||
                uri.endsWith("png")||
                uri.endsWith("css")||
                uri.endsWith("jpg")||
                uri.endsWith("js")||
                uri.endsWith("myapp/")||
                uri.endsWith("verifyCode")){
            chain.doFilter(request,response);
            return;
        }

        if(session==null){
            response.sendRedirect("./login-form-18/loginnow.html");
            System.out.println("auth failed");
        } else{
            if(session.getAttribute(LoginServlet.LOGIN_TOKEN)==Boolean.TRUE||session.getAttribute(LoginServlet.LOGIN_TOKEN)=="123"){
                System.out.println("登陆验证成功");
                chain.doFilter(request,response);
            } else {
                response.sendRedirect("./login-form-18/loginnow.html");
                System.out.println("auth failed");
            }
        }
    }
}

