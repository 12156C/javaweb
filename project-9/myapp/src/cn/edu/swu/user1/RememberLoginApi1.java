package cn.edu.swu.user1;

import cn.edu.swu.book.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * {
 *   "books":[
 *       {"id":1,"name":"c program","author":"tam","price":33,"describe":"good book","picture":"./upload/1111.jpg"},
 *       {"id":2,"name":"R program","author":"tam","price":22,"describe":"good book","picture":"./upload/1111.jpg"},
 *   ]
 * }
 * */

@WebServlet("/api/remember1")
public class RememberLoginApi1 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        String password=(String)session.getAttribute("password");
        System.out.println(username);
        System.out.println(password);
        List<User1> user1s =new ArrayList<>();
        User1 user1=new User1();
        user1.setUser(username);
        user1.setPassword(password);
        response.setContentType("application/json;charset=UTF-8");
        user1s.add(user1);
        try(Writer writer=response.getWriter()){
            this.writeJsonByJackson(response.getWriter(),user1s);
        }
    }

    private void writeJsonByJackson(Writer writer,List<User1> user1s) throws IOException {
        String json=new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user1s);
        System.out.println(json);
        writer.write(json);
    }
}
