package cn.edu.swu;

import cn.edu.swu.book.Book;
import cn.edu.swu.book.BookRepo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/index.html")
public class Index extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Book> books=null;
        try {
            books= BookRepo.getInstance().getAll();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb=new StringBuilder();
        sb.append("<div class='book-group'>");
        for(Book book:books){
            sb.append("<div class='book-div'>");
            sb.append("<div class='book-pic'><img src=\"./images/book-pic.jpg\" width=50%></div>");
            sb.append("<div class='book-name'>"+book.getName()+"</div>");
            sb.append("<div class='book-author'>"+book.getAuthor()+"</div");
            sb.append("<div class='book-price'>"+book.getPrice()+"</div>");
            sb.append("</div>");
        }
        sb.append("</div><br><br>");
        String page="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>My Java Web App</title>\n" +
                "    <link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">" +
                "</head>\n" +
                "<body>\n" +
                "    <center style=\"margin-top:3em\">\n" +
                "        <h1>欢迎访问我的网上书店</h1>\n" +
                "        <div style=\"margin-top:4em;width:50%\">\n" +
//                "            <div>\n" +
//                "                <div style=\"float:left;padding-right:3em\"><a href=\"./listBook\">查看书目</a></div><br><br><br>\n" +
//                "            </div>\n" +
                "        </div>\n" +
                sb.toString()+
                "            <br><br><br><br>\n" +
                "<div width:100%></div>" +
                "            <div style=\"padding-top:1em;font-size:0.9em\"><a href=\"./login\">管理员登录</a></div>\n" +
                "    </center>\n" +
                "</body>\n" +
                "</html>";
        response.setContentType("text/html;charset=UTF-8");
        try(Writer writer=response.getWriter()){
            writer.write(page);
        }



    }
}
