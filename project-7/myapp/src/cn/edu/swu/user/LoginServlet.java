package cn.edu.swu.user;

import com.mysql.cj.Session;
import jakarta.servlet.FilterChain;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

    private void doLogin(HttpServletRequest request, HttpServletResponse response){
        String userName=request.getParameter("user");
        String password=request.getParameter("password");
        try {
            User user=UserRepo.getInstance().auth(userName,password);
            if(user!=null){
                HttpSession session=request.getSession(true);
                session.setAttribute(LOGIN_TOKEN,Boolean.TRUE);
                response.sendRedirect("./admin.html");
            } else{
                response.sendRedirect("./index.html");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
