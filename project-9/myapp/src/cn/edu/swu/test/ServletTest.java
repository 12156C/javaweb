package cn.edu.swu.test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class ServletTest extends HttpServlet {
    public  void  doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getServletContext().getRealPath("./"));
        System.out.println(request.getRequestURI());
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestedSessionId());
    }
}
