package cn.edu.swu.book;

import cn.edu.swu.db.DBEngine;
import cn.edu.swu.db.RecordVisitor;

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
        if(book.getId()>0){
            this.updateBook(book);
        } else{
            this.insertBook(book);
        }
    }

    private void insertBook(Book book) throws SQLException {
        String template="INSERT INTO `book`(`name`,`author`,`price`,`describe`,`picture`) " +
                "VALUES(\"%s\",\"%s\",%s,\"%s\",\"%s\") ";
        String sql=String.format(template,book.getName(),book.getAuthor(),book.getPrice(),book.getDescribe(),book.getPicture());
        DBEngine.getInstance().execute(sql);
    }

    private void updateBook(Book book) throws SQLException {
        String template="UPDATE `book` SET `name`=\"%s\",`author`=\"%s\",`price`=%s,`describe`=\"%s\",`picture`=\"%s\" " +
                "WHERE `id`=%s";
        String sql=String.format(template,book.getName(),
                book.getAuthor(),book.getPrice(),book.getDescribe(),book.getPicture(),book.getId());
        DBEngine.getInstance().execute(sql);
    }


    public void deleteBook(Long id) throws SQLException {
        String template="DELETE FROM `book` WHERE `id`= %s";
        String sql=String.format(template,id);
        DBEngine.getInstance().execute(sql);
    }
//    public Book visit(ResultSet rs) throws SQLException {
//        Book book=new Book();
//        book.setId(rs.getLong("id"));
//        book.setName(rs.getString("name"));
//        book.setAuthor(rs.getString("author"));
//        book.setDescribe(rs.getString("describe"));
//        book.setPrice(rs.getFloat("price"));
//        return book;
//    }
    public List<Book> getAll() throws SQLException {
        String sql="SELECT `id`,`name`,`author`,`price`,`describe`,`picture` FROM `book`";
        return DBEngine.getInstance().query(sql, new RecordVisitor<Book>() {
            @Override
            public Book visit(ResultSet rs) throws SQLException {
                return BookRepo.getBookFromResultSet(rs);
            }
        });

    }

    public Book getById(String id) throws SQLException {
        String sql="SELECT * FROM `book` WHERE `id`=%s";

        List<Book> books = DBEngine.getInstance().query(String.format(sql,id), new RecordVisitor<Book>() {
            @Override
            public Book visit(ResultSet rs) throws SQLException {
                return BookRepo.getBookFromResultSet(rs);
            }
        });

        return books.size()==0?null:books.get(0);
    }
    public List<Book> getByIds(List<Long> ids) throws SQLException {
        String sql="SELECT * FROM `book` WHERE `id` IN (%s)";
        String Strid="";
        for(int i=0;i<ids.size();++i){
            Strid+=((i>0)?",":"")+ids.get(i);
        }
        List<Book> books = DBEngine.getInstance().query(String.format(sql,Strid), new RecordVisitor<Book>() {
//String.join(",",ids)用char
            @Override
            public Book visit(ResultSet rs) throws SQLException {
                return BookRepo.getBookFromResultSet(rs);
            }
        });

        return books;
    }
    private static Book getBookFromResultSet(ResultSet rs) throws SQLException {
        Book book=new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setDescribe(rs.getString("describe"));
        book.setPrice(rs.getFloat("price"));
        book.setPicture(rs.getString("picture"));
        return book;
    }

}
