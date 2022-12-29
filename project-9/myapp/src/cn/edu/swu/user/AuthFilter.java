package cn.edu.swu.user;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter("/login.html")
public class AuthFilter extends HttpFilter {
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session=request.getSession();
        System.out.println("auth Filter");

        String uri=request.getRequestURI();
        System.out.println(uri);



        if(uri.endsWith("login.html")||uri.endsWith("index.html")||
                uri.endsWith("png")||uri.endsWith("css")||
                uri.endsWith("jpg")||uri.endsWith("login")||uri.endsWith("myapp/")||
                uri.endsWith("verifyCode")||uri.endsWith("api/books")){
            chain.doFilter(request,response);
            return;
        }

        if(session==null){
            response.sendRedirect("./login-form-18/loginnow1.html");
            System.out.println("auth failed");
        } else{
            Boolean toke=(Boolean) session.getAttribute(LoginServlet.LOGIN_TOKEN);
            if(toke==Boolean.TRUE){
                System.out.println("登陆验证成功");
                chain.doFilter(request,response);
            } else {
                response.sendRedirect("./login-form-18/loginnow1.html");
                System.out.println("auth failed");
            }
        }
    }
}
