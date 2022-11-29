package cn.edu.swu.user;

import cn.edu.swu.db.DBEngine;
import cn.edu.swu.db.RecordVisitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepo {

    private static UserRepo instance=new UserRepo();

    private UserRepo(){

    }
    public static UserRepo getInstance(){
        return instance;
    }

    public void save(User user) throws SQLException {
        String template="INSERT INTO `user`(id,name,user,password) " +
                "VALUES(\"%s\",\"%s\",\"%s\",md5(\"%s\")) ";
        String sql=String.format(template,user.getId(),user.getName(),user.getUser(),user.getPassword());
        DBEngine.getInstance().execute(sql);
    }

    public void delete(User user) throws SQLException {
        String template="DELETE from `user` WHERE `id`=\"%s\"";
        DBEngine.getInstance().execute(String.format(template,user.getId()));
    }

    public List<User> getAll() throws SQLException {
        String sql="SELECT `id`,`name`,`user`,`password` FROM `user`";
        return DBEngine.getInstance().query(sql, new RecordVisitor<User>() {
            @Override
            public User visit(ResultSet rs) throws SQLException {
                User user=new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });

    }
}
