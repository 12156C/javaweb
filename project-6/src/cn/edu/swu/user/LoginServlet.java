package cn.edu.swu.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String userName=request.getParameter("user");
        String password=request.getParameter("password");
        try {
            User user=UserRepo.getInstance().auth(userName,password);
            if(user!=null){
                response.sendRedirect("./admin.html");
            } else{
                response.sendRedirect("./index.html");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }



    }
}
