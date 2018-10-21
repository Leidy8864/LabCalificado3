package pe.leidy_cs.labcalificado3.repository;

import com.orm.SugarRecord;

import java.util.List;

import pe.leidy_cs.labcalificado3.models.User;

public class UserRepository {
    public static List<User> list(){
        List<User> users = SugarRecord.listAll(User.class);
        return users;
    }

    public static User read(Long id){
        User user = SugarRecord.findById(User.class, id);
        return user;
    }

    public static void create(String username, String name, String email, String password){
        User user = new User(username, name, email, password);
        SugarRecord.save(user);
    }

    public static User login(String username, String password){
        List<User> users = SugarRecord.listAll(User.class);
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public static User getUser(String username){
        List<User> users = SugarRecord.listAll(User.class);
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }

}
