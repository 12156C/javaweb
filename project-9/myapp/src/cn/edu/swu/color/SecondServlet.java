package cn.edu.swu.color;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

//annotation 注释方式
@WebServlet("/second")
public class SecondServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        try(Writer writer=response.getWriter()){
            writer.write("<center><h1>This message come from SecondServlet !!</h1></center>");
        }

    }
}
