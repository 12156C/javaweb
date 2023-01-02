package cn.edu.swu.book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/addToCart")
public class AddToCartServlet  extends HttpServlet {
    public  final static String CART="cart";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookId=request.getParameter("bookId");
        List<Long> cart=this.getCart(request);
        cart.add(Long.valueOf(bookId));

        for(Long id:cart){
            System.out.println("图书ID："+id);
        }

        response.setContentType("application/json;charset=UTF-8");
        try(Writer writer=response.getWriter()) {
            writer.write("{\"code\":200,\"message\":\"sucess\"}");
        }
    }

    private List<Long> getCart(HttpServletRequest request){
        HttpSession session=request.getSession(true);
        session.getAttribute(CART);
        List<Long> cart= (List<Long>)session.getAttribute(CART);
        if(cart==null){
            cart=new ArrayList<Long>();
            session.setAttribute(CART,cart);
        }
        return cart;
    }
}
