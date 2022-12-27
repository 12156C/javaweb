package cn.edu.swu.book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        try {
            BookRepo.getInstance().deleteBook(Long.valueOf(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("./listBook");
    }
}
