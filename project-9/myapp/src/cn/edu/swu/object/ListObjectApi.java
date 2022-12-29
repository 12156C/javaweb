package cn.edu.swu.object;

import cn.edu.swu.book.Book;
import cn.edu.swu.book.BookRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

/*
 * {
 *   "books":[
 *       {"id":1,"name":"c program","author":"tam","price":33,"describe":"good book","picture":"./upload/1111.jpg"},
 *       {"id":2,"name":"R program","author":"tam","price":22,"describe":"good book","picture":"./upload/1111.jpg"},
 *   ]
 * }
 * */

@WebServlet("/api/objects")
public class ListObjectApi extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            List<Object> objects = ObjectRepo.getInstance().getAll();
            response.setContentType("application/json;charset=UTF-8");
            try(Writer writer=response.getWriter()){
                this.writeJsonByJackson(response.getWriter(),objects);
            }
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private void writeJsonByJackson(Writer writer,List<Object> objects) throws IOException {
            String json=new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(objects);
            System.out.println(json);
            writer.write(json);
    }

    private void writeJson(Writer writer,List<Object> objects) throws IOException {
            writer.write("{");
            writer.write("\"objects\":[");
            for(int i=0;i< objects.size();++i){
                if(i>0) writer.write(",");
                Object object=objects.get(i);
                writer.write(String.format("{\"id\":%s,\"name\":\"%s\",\"num\":%s,\"org\":\"%s\",\"user\":\"%s\",\"time1\":\"%s\",\"time2\":\"%s\",\"deal\":%s}",
                        object.getId(),object.getName(),object.getNum(), object.getOrg()
                        , object.getUser(), object.getTime1(), object.getTime2(), object.getDeal()));
            }
            writer.write("]");
            writer.write("}");
    }
}
