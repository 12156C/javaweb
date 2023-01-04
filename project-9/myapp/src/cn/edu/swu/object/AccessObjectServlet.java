package cn.edu.swu.object;

import cn.edu.swu.book.Book;
import cn.edu.swu.book.BookRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/api/changeBook")
public class AccessObjectServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String objectId=request.getParameter("objectId");
        Object object=null;
        try {
            object=ObjectRepo.getInstance().getById(objectId);
            System.out.println(object.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(object==null){
            System.out.println("查找失败");
        }
        else{
            try {
                Book book= BookRepo.getInstance().getByName(object.getName());
                System.out.println(object.getId());
                System.out.println(book);
                if(book==null){
                    System.out.println("名称有误~~~~~~~~~~~~");
                    ObjectRepo.getInstance().dealObject(object.getId(),5);
                } else if(book.getPrice()>=object.getNum()){
                    System.out.println("成功借用啦~~~~~~~~~~~~");
                    BookRepo.getInstance().changeBook(book,object.getNum());
                    ObjectRepo.getInstance().dealObject(object.getId(),1);
                } else{
                    System.out.println("数量不足啦~~~~~~~~~~~~");
                    ObjectRepo.getInstance().dealObject(object.getId(),2);
                }
            } catch (SQLException e) {
                try {
                    ObjectRepo.getInstance().dealObject(object.getId(),3);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            }
        }
    }
}