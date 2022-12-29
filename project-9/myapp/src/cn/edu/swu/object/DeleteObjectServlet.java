package cn.edu.swu.object;

import cn.edu.swu.book.BookRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteObject")
public class DeleteObjectServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        try {
            ObjectRepo.getInstance().deleteObject(Long.valueOf(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("./listObject");
    }
}
