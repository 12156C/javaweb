package cn.edu.swu.object;

import cn.edu.swu.book.Book;
import cn.edu.swu.book.BookRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/updateObject")
public class UpdateObjectServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        Object object=null;
        try {
            object= ObjectRepo.getInstance().getById(id);
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
                "        <h2>录入物资信息</h2>\n" +
                "        <form action=\"./saveBook\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                "          编号：<input type=\"text\" name=\"id\" value=\"" + object.getId()+"\"><br><br>\n  " +
                "          名称：<input type=\"text\" name=\"name\" value=\"" + object.getName()+"\"><br><br>\n" +
                "          位置：<input type=\"text\" name=\"author\" value=\"" + object.getOrg()+"\"><br><br>\n" +
                "          数量：<input type=\"text\" name=\"price\" value=\"" + object.getUser()+"\"><br><br>\n" +
                "          单位：<textarea name=\"describe\" rows=\"4\" cols=\"22\">" + object.getTime1()+"</textarea><br><br>\n" +
                "          <span style=\"padding-left:84px\"></span>图片：<input type=\"file\" name=\"picture\" width=\"100px\" value=\"" + object.getDeal()+"\"><br><br>\n" +
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