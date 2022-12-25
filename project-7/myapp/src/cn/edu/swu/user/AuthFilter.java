package cn.edu.swu.user;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter("/*")
public class AuthFilter extends HttpFilter {
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session=request.getSession();
        System.out.println("auth Filter");

        String uri=request.getRequestURI();
        System.out.println(uri);
        if(uri.endsWith("login.html")||uri.endsWith("index.html")||
                uri.endsWith("png")||uri.endsWith("css")||
                uri.endsWith("jpg")||uri.endsWith("login")||uri.endsWith("myapp/")||
                uri.endsWith("verifyCode")){
            chain.doFilter(request,response);
            return;
        }

        if(session==null){
            response.sendRedirect("./login.html");
            System.out.println("auth failed");
        } else{
            Boolean toke=(Boolean) session.getAttribute(LoginServlet.LOGIN_TOKEN);
            if(toke==Boolean.TRUE){
                System.out.println("登陆验证成功");
                chain.doFilter(request,response);
            } else {
                response.sendRedirect("./login.html");
                System.out.println("auth failed");
            }
        }
    }
}
