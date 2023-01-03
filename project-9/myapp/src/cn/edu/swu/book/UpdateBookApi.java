package cn.edu.swu.book;

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

@WebServlet("/api/updateBook")
public class UpdateBookApi  extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookId=request.getParameter("bookId");
        List<Book> books=new ArrayList<>();
        Book book=null;
        try {
            book=BookRepo.getInstance().getById(bookId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        books.add(book);
        response.setContentType("application/json;charset=UTF-8");
        try(Writer writer=response.getWriter()){
            this.writeJsonByJackson(response.getWriter(),books);
        }
    }
    private void writeJsonByJackson(Writer writer,List<Book> books) throws IOException {
        String json=new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(books);
        System.out.println(json);
        writer.write(json);
    }
}
