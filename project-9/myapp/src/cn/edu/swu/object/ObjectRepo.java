package cn.edu.swu.object;

import cn.edu.swu.book.Book;
import cn.edu.swu.db.DBEngine;
import cn.edu.swu.db.RecordVisitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ObjectRepo {

    private static ObjectRepo instance=new ObjectRepo();
    private ObjectRepo(){

    }

    public static ObjectRepo getInstance(){
        return instance;
    }

    public void saveObject(Object object) throws SQLException {
        if(object.getId()>0){
            this.updateObject(object);
        } else{
            this.insertObject(object);
        }
    }

    private void insertObject(Object object) throws SQLException {
        String template="INSERT INTO `object`(`name`,`num`,`org`,`user`,`time1`,`time2`,`deal`) " +
                "VALUES(\"%s\",%s,\"%s\",\"%s\",\"%s\",\"%s\",%s) ";
        String sql=String.format(template,object.getName(),object.getNum(), object.getOrg()
                , object.getUser(), object.getTime1(), object.getTime2(), object.getDeal());
        DBEngine.getInstance().execute(sql);
    }

    private void updateObject(Object object) throws SQLException {
        String template="UPDATE `object` SET `name`=\"%s\",`num`=%s,`org`=\"%s\",`user`=\"%s\",`time1`=\"%s\",`time2`=\"%s\",`deal`=%s," +
                "WHERE `id`=%s";
        String sql=String.format(template,object.getName(),object.getNum(), object.getOrg()
                , object.getUser(), object.getTime1(), object.getTime2(), object.getDeal());
        DBEngine.getInstance().execute(sql);
    }


    public void deleteObject(Long id) throws SQLException {
        String template="DELETE FROM `object` WHERE `id`= %s";
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
    public List<Object> getAll() throws SQLException {
        String sql="SELECT `id`,`name`,`num`,`org`,`user`,`time1`,`time2`,`deal` FROM `object`";
        return DBEngine.getInstance().query(sql, new RecordVisitor<Object>() {
            @Override
            public Object visit(ResultSet rs) throws SQLException {
                return ObjectRepo.getObjectFromResultSet(rs);
            }
        });

    }

    public Object getById(String id) throws SQLException {
        String sql="SELECT * FROM `object` WHERE `id`=%s";

        List<Object> objects = DBEngine.getInstance().query(String.format(sql,id), new RecordVisitor<Object>() {
            @Override
            public Object visit(ResultSet rs) throws SQLException {
                return ObjectRepo.getObjectFromResultSet(rs);
            }
        });

        return objects.size()==0?null:objects.get(0);
    }

    private static Object getObjectFromResultSet(ResultSet rs) throws SQLException {
        Object object=new Object();
        object.setId(rs.getLong("id"));
        object.setName(rs.getString("name"));
        object.setNum(rs.getInt("num"));
        object.setOrg(rs.getString("org"));
        object.setUser(rs.getString("user"));
        object.setTime1(rs.getString("time1"));
        object.setTime2(rs.getString("time1"));
        object.setDeal(rs.getInt("deal"));
        return object;
    }

}
