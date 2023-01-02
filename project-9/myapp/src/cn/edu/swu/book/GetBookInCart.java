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

@WebServlet("/api/getBooksInCart")
public class GetBookInCart extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession(true);
        List<Long> cart= (List<Long>)session.getAttribute(AddToCartServlet.CART);
        List<Book> books=new ArrayList<>();
        if(cart!=null){
            try {
                books=BookRepo.getInstance().getByIds(cart);
            } catch (SQLException e) {
                throw new IOException(e.getMessage());
            }
        }

        response.setContentType("application/json;charset=UTF-8");
        try(Writer writer=response.getWriter()){
            this.writeJsonByJackson(response.getWriter(),books);
        }

    }

    private void writeJsonByJackson(Writer writer, List<Book> books) throws IOException {
        String json=new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(books);
        System.out.println(json);
        writer.write(json);
    }
}
