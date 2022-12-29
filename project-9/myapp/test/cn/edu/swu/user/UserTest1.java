package cn.edu.swu.user;

import cn.edu.swu.user1.User1;
import cn.edu.swu.user1.UserRepo1;

import java.sql.SQLException;
import java.util.UUID;

public class UserTest1 {

    public static void main(String[] args) throws SQLException {
        User1 admin=new User1();
        admin.setId(UUID.randomUUID().toString());
        admin.setName("用户");
        admin.setUser("admin");
        admin.setPassword("swu.edu");

//        User1 guest=new User1();
//        guest.setId(UUID.randomUUID().toString());
//        guest.setName("匿名用户");
//        guest.setUser("guest");
//        guest.setPassword("guest");

        UserRepo1.getInstance().save(admin);
//        UserRepo1.getInstance().save(guest);
        //UserRepo userRepo; 可列为单立
//        List<User> users=UserRepo.getInstance().getAll();
//        for(User user:users){
//            System.out.println("删除"+user.getName());
//            UserRepo.getInstance().delete(user);
//        }
    }
}
