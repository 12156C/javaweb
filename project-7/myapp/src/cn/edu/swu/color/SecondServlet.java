package cn.edu.swu.color;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
