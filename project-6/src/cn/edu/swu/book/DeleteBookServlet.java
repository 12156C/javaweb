package cn.edu.swu.book;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
