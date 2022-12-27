package cn.edu.swu.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class JsonTest {

    @Test
    public void  booksToJsonTest() throws SQLException, JsonProcessingException {
        List<Book> books =BookRepo.getInstance().getAll();

        String json=new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(books);

        System.out.println(json);
    }
}
