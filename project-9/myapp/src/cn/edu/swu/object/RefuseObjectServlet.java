package cn.edu.swu.object;

import cn.edu.swu.book.Book;
import cn.edu.swu.book.BookRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/api/refuseObject")
public class RefuseObjectServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String objectId=request.getParameter("objectId");
        try {
            ObjectRepo.getInstance().dealObject(Long.valueOf(objectId),4);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}