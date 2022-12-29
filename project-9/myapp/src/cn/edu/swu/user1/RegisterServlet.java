package cn.edu.swu.user1;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String userName=request.getParameter("user");
        String password=request.getParameter("password");
        User1 user1=new User1();
        user1.setId(UUID.randomUUID().toString());
        user1.setName("用户");
        user1.setUser(userName);
        user1.setPassword(password);
        try {
            UserRepo1.getInstance().save(user1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("./login-form-18/loginnow.html");
    }

}
