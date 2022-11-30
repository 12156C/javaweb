package cn.edu.swu.book;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/saveBook")
public class SaveBookServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String author=request.getParameter("author");
        Float price=Float.valueOf(request.getParameter("price"));
        String describe=request.getParameter("describe");

        Book book=new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        book.setDescribe(describe);
        System.out.println(String.format("%s %s %s %s",name,author,price,describe));

        if(id!=null){
            book.setId(Long.valueOf(id));
        }

        String message=null;
        try {
            BookRepo.getInstance().saveBook(book);
            message="提交信息保存成功！";
        } catch (SQLException e) {
            message="提交信息保存失败！";
            System.out.println(e);
        }

        response.setContentType("text/html;charset=UTF-8");
        try(Writer writer=response.getWriter()){
            String html="<center style='margin-top:5em'><h1>%s</h1></center><br>"+
                    "<a href='./submit-book.html'><center>录入新图书</center></a>&nbsp;&nbsp;&nbsp;" +
                    "<a href='./listBook'><center>显示列表</center></a>&nbsp;&nbsp;&nbsp;" +
                    "<a href='./index.html'><center>返回首页</center></a><br>";
            writer.write(String.format(html,message));
        }

    }


}
