package cn.edu.swu.book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    public static Connection getConnection() throws  SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        Connection connection= DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/book",
                "root",
                "swu.edu"
        );
        return connection;
    }

    public static void saveBook(Book book) throws SQLException {
        String template="INSERT INTO `book`(`name`,`author`,`price`,`describe`) VALUES ('%s','%s',%s,'%s')";

        try(Connection connection=getConnection()){System.out.println("cao");
            try(Statement statement = connection.createStatement()){
                String sql=String.format(template,book.getName(),book.getAuthor(),book.getPrice(),book.getDescribe());
                System.out.println(sql);
                statement.execute(sql);
            }
        }
    }

    public static List<Book> getAllBooks() throws SQLException {
        String sql="SELECT * FROM book";
        try(Connection connection=getConnection()){
            try(Statement statement=connection.createStatement()){
                try(ResultSet resultSet=statement.executeQuery(sql)){
                    List<Book> books=new ArrayList<>();
                    while(resultSet.next()){
                        long id=resultSet.getLong("id");
                        String name=resultSet.getString("name");
                        String author=resultSet.getString("author");
                        float price=resultSet.getLong("price");
                        String describe=resultSet.getString("describe");

                        Book book=new Book();
                        book.setId(id);
                        book.setName(name);
                        book.setAuthor(author);
                        book.setPrice(price);
                        book.setDescribe(describe);

                        books.add(book);
                    }
                    return books;
                }
            }
        }
    }

}
