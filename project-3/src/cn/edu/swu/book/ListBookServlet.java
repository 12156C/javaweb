package cn.edu.swu.book;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listBook")
public class ListBookServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            List<Book> books =BookRepo.getInstance().getAll();
            //List<Book> books =DBUtils.getAllBooks();
            response.setContentType("text/html;charset=UTF-8");
            try(Writer writer=response.getWriter()) {
                writer.write(" <center style=\"margin-top:5em\">\n");
                writer.write(" <h1>欢迎访问我的网上书店</h1>");

                writer.write("<table width='60%' border='0' cellpadding=4>");
                //for (Book book:books){
                for (int i=0;i<books.size();++i){
                    Book book=books.get(i);
                    if(i%2==0){
                        writer.write("<tr style='background-color:#F5F5F5;height:2em'>");
                    } else{
                        writer.write("<tr style='background-color:#D6E6F2;height:2em'>");
                    }

                    writer.write(String.format("<td width='30px'>%s</td>",book.getId()));
                    writer.write(String.format("<td width='150px'>%s</td>",book.getName()));
                    writer.write(String.format("<td width='100px'>%s</td>",book.getAuthor()));
                    writer.write(String.format("<td width='60px'>%s</td>",book.getPrice()));
                    writer.write(String.format("<td width='200px'>%s</td>",book.getDescribe()));
                    writer.write(String.format("<td><a href='./deleteBook?id=%s'>删除</a></td>",book.getId()));
                    writer.write("</tr>");

                }
                writer.write("</table><br><br>\n\n");
                writer.write("</center>\n");
                writer.write("<center>");
                writer.write("<a href='index.html'>返回首页</a>");
                writer.write("</center>\n");
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
