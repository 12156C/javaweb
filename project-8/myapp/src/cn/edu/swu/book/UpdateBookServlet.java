package cn.edu.swu.book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        Book book=null;
        try {
            book=BookRepo.getInstance().getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String html="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>My Book Store</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <center>\n" +
                "      <div style=\"margin-top:5em;padding:2em;text-align:center;width:60%;background-color:#EEEEEE\"></div>\n" +
                "        <h2>录入图书信息</h2>\n" +
                "        <form action=\"./saveBook\" method=\"post\">\n" +
                "          编号：<input type=\"text\" name=\"id\" value=\"" + book.getId()+"\"><br><br>\n  " +
                "          书名：<input type=\"text\" name=\"name\" value=\"" + book.getName()+"\"><br><br>\n" +
                "          作者：<input type=\"text\" name=\"author\" value=\"" + book.getAuthor()+"\"><br><br>\n" +
                "          价格：<input type=\"text\" name=\"price\" value=\"" + book.getPrice()+"\"><br><br>\n" +
                "          简介：<textarea name=\"describe\" rows=\"4\" cols=\"22\">" + book.getDescribe()+"</textarea><br><br>\n" +
                "          <input type=\"submit\" value=\"提 交 信 息\">\n" +
                "        </form>\n" +
                "\n" +
                "    </center>\n" +
                "</body>\n" +
                "</html>";
       // html=String.format(html,book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getDescribe());
        response.setContentType("text/html;Charset=utf8");
        try(Writer writer=response.getWriter()){
            writer.write(html);
        }
    }



}
///myapp/project6