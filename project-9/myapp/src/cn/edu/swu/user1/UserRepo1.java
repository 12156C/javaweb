package cn.edu.swu.user1;

import cn.edu.swu.book.Book;
import cn.edu.swu.book.BookRepo;
import cn.edu.swu.db.DBEngine;
import cn.edu.swu.db.RecordVisitor;
import cn.edu.swu.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepo1 {

    private static UserRepo1 instance=new UserRepo1();

    private UserRepo1(){

    }
    public static UserRepo1 getInstance(){
        return instance;
    }

    public void save(User1 user) throws SQLException {
        String template="INSERT INTO `user1`(id,name,user,password) " +
                "VALUES(\"%s\",\"%s\",\"%s\",md5(\"%s\")) ";
        String sql=String.format(template,user.getId(),user.getName(),user.getUser(),user.getPassword());
        DBEngine.getInstance().execute(sql);
    }

    public void delete(User1 user) throws SQLException {
        String template="DELETE from `user1` WHERE `id`=\"%s\"";
        DBEngine.getInstance().execute(String.format(template,user.getId()));
    }

    public List<User1> getAll() throws SQLException {
        String sql="SELECT `id`,`name`,`user`,`password` FROM `user1`";
        return DBEngine.getInstance().query(sql, new RecordVisitor<User1>() {
            @Override
            public User1 visit(ResultSet rs) throws SQLException {
                return UserRepo1.getUserByResultSet(rs);
            }
        });
    }

    public User1 auth(String user,String password) throws SQLException {
        String template="SELECT * FROM `user1` WHERE `user`= \"%s\" AND `password`= MD5(\"%s\")";
        String sql=String.format(template,user,password);
        List<User1> users=DBEngine.getInstance().query(sql, new RecordVisitor<User1>() {
            @Override
            public User1 visit(ResultSet rs) throws SQLException {
                return UserRepo1.getUserByResultSet(rs);
            }
        });

        return users.size()==0?null:users.get(0);
    }

    public List<User1> getByName(String name) throws SQLException {
        String template="SELECT * FROM `user1` WHERE `name`= \"%s\"";
        String sql=String.format(template,name);
        System.out.println(sql);
        List<User1> user1s=DBEngine.getInstance().query(sql, new RecordVisitor<User1>() {
            @Override
            public User1 visit(ResultSet rs) throws SQLException {
                return UserRepo1.getUserByResultSet(rs);
            }
        });
        return user1s;
    }
    private static User1 getUserByResultSet(ResultSet rs) throws SQLException {
        User1 user=new User1();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setUser(rs.getString("user"));
        user.setPassword(rs.getString("password"));
        return user;
    }

}
