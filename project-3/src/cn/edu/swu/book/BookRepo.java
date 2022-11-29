package cn.edu.swu.book;

import cn.edu.swu.db.DBEngine;
import cn.edu.swu.db.RecordVisitor;
import cn.edu.swu.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookRepo {

    private static BookRepo instance=new BookRepo();
    private BookRepo(){

    }

    public static BookRepo getInstance(){
        return instance;
    }

    public void saveBook(Book book) throws SQLException {
        String template="INSERT INTO `book`(`name`,`author`,`price`,`describe`) " +
                "VALUES(\"%s\",\"%s\",%s,\"%s\") ";
        String sql=String.format(template,book.getName(),book.getAuthor(),book.getPrice(),book.getDescribe());
        DBEngine.getInstance().execute(sql);
    }

    public void deleteBook(Book book) throws SQLException {
        String template="DELETE FROM `book` WHERE `id`= \"%s\"";
        String sql=String.format(template,book.getId());
        DBEngine.getInstance().execute(sql);
    }

    public void deleteBook(Long id) throws SQLException {
        String template="DELETE FROM `book` WHERE `id`= %s";
        String sql=String.format(template,id);
        DBEngine.getInstance().execute(sql);
    }

    public List<Book> getAll() throws SQLException {
        String sql="SELECT `id`,`name`,`author`,`price`,`describe` FROM `book`";
        return DBEngine.getInstance().query(sql, new RecordVisitor<Book>() {
            @Override
            public Book visit(ResultSet rs) throws SQLException {
                Book book=new Book();
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setDescribe(rs.getString("describe"));
                book.setPrice(rs.getFloat("price"));
                return book;
            }
        });

    }

}
