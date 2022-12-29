package cn.edu.swu.book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                writer.write(" <h1>欢迎访问校会办公室物资清单</h1>");

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
                    writer.write(String.format("<td>%s</td>",book.getDescribe()));
                    writer.write(String.format("<td><img src='./upload/%s' style='width:50px'></td>",book.getPicture()));
                    writer.write(String.format("<td width='10px'><a href='./deleteBook?id=%s'>" +
                            "<img src='./images/delete.png' width='20px'></a></td>",book.getId()));
                    writer.write(String.format("<td width='10px'><a href='./updateBook?id=%s'>" +
                            "<img src='./images/edit.png' width='20px'></a></td>",book.getId()));
                    writer.write("</tr>");

                }
                writer.write("</table><br><br>\n\n");
                writer.write("</center>\n");
                writer.write("<center>");
                writer.write("<a href='admin.html'>返回首页</a>");
                writer.write("</center>\n");
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
