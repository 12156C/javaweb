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


@WebFilter("/*")
public class AuthFilter extends HttpFilter {
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session=request.getSession();
        System.out.println("auth Filter");

        String uri=request.getRequestURI();
        System.out.println(uri);


        if(uri.endsWith("admin-1.html")||uri.endsWith("/register")){
            System.out.println("**************已经被拦截啦");
            if(session==null){
                response.sendRedirect("./login-form-18/loginnow1.html");
                System.out.println("auth failed");
            } else{
                if(session.getAttribute(LoginServlet.LOGIN_TOKEN)==Boolean.TRUE){
                    response.sendRedirect("./login-form-18/loginnow1.html");
                    System.out.println("auth failed");
                } else {
                    String token=(String)session.getAttribute(LoginServlet.LOGIN_TOKEN);
                    if(token==null){
                        response.sendRedirect("./login-form-18/loginnow1.html");
                        System.out.println("auth failed");
                    }
                    else if(token.equals("123")){
                        System.out.println(session.getAttribute(LoginServlet.LOGIN_TOKEN));
                        System.out.println("***********登陆验证成功");
                        chain.doFilter(request,response);
                    } else{
                        response.sendRedirect("./login-form-18/loginnow1.html");
                        System.out.println("auth failed");
                    }

                }
            }

        } else if(uri.endsWith("register.html")){
            System.out.println("**************已经被拦截啦");
            System.out.println(session.getAttribute(LoginServlet.LOGIN_TOKEN));
            if(session==null){
                response.sendRedirect("./loginnow1.html");
                System.out.println("auth failed");
            } else{
                if(session.getAttribute(LoginServlet.LOGIN_TOKEN)==Boolean.TRUE){
                    response.sendRedirect("./loginnow1.html");
                    System.out.println("auth failed");
                } else {
                    String token=(String)session.getAttribute(LoginServlet.LOGIN_TOKEN);
                    if(token==null){
                        response.sendRedirect("./loginnow1.html");
                        System.out.println("auth failed");
                    }
                    else if(token.equals("123")){
                        System.out.println("***********登陆验证成功");
                        chain.doFilter(request,response);
                    }else {
                        response.sendRedirect("./loginnow1.html");
                        System.out.println("auth failed");
                    }
                }

            }
        } else if(uri.endsWith("list.html")||uri.endsWith("/borrow/table/index.html")){
            System.out.println("**************已经被拦截啦");
            System.out.println(session.getAttribute(LoginServlet.LOGIN_TOKEN));
            if(session==null){
                response.sendRedirect("../../login-form-18/loginnow1.html");
                System.out.println("auth failed");
            } else{
                if(session.getAttribute(LoginServlet.LOGIN_TOKEN)==Boolean.TRUE){
                    response.sendRedirect("../../login-form-18/loginnow1.html");
                    System.out.println("auth failed");
                } else {
                    String token=(String)session.getAttribute(LoginServlet.LOGIN_TOKEN);
                    if(token==null){
                        response.sendRedirect("../../login-form-18/loginnow1.html");
                        System.out.println("auth failed");
                    }
                    else if(token.equals("123")){
                        System.out.println("***********登陆验证成功");
                        chain.doFilter(request,response);
                    } else{
                        response.sendRedirect("../../login-form-18/loginnow1.html");
                        System.out.println("auth failed");
                    }

                }
            }
        }else{
                chain.doFilter(request,response);
                return;
            }


    }
}
