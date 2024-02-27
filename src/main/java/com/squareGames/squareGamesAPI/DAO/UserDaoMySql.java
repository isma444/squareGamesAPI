package com.squareGames.squareGamesAPI.DAO;

import com.squareGames.squareGamesAPI.connection.DbAccess;
import com.squareGames.squareGamesAPI.entities.User;
import com.squareGames.squareGamesAPI.services.UserCreationParams;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Primary
public class UserDaoMySql implements UserDAO {


    private DbAccess dbAccess = DbAccess.getInstance();
    @Override
    public Collection<User> getAllUsers() {
        Connection conn = dbAccess.getConnection();
        String getUsers = "SELECT * FROM users";
        List<User> listUser = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(getUsers);
            ResultSet res = pstmt.executeQuery();

            while(res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                User user = new User(name, id);
                listUser.add(user);
            }

        }catch(Exception e){
            System.out.println(e);
        }
        return listUser;
    }

    @Override
    public User getUserById(int id) {
       Connection conn = dbAccess.getConnection();
       String getUser = "SELECT * FROM users WHERE id = ?";
        User user = null;
        try{
           PreparedStatement pstmt = conn.prepareStatement(getUser);
           pstmt.setInt(1, id);
           ResultSet res = pstmt.executeQuery();

           while(res.next()){
               int idUser = res.getInt("id");
               String name = res.getString("name");
               user = new User(name, idUser);
           }

       }catch(Exception e){
           System.out.println(e);
       }
        return user;
    }

    @Override
    public User addUser(UserCreationParams params) {
        Connection conn = dbAccess.getConnection();
        String addUser = "INSERT INTO users (name) VALUES (?)";
        User user = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(addUser);
            pstmt.setString(1, params.name());
            pstmt.executeUpdate();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT LAST_INSERT_ID()");

            if(res.next()){
                int lastId = res.getInt(1);
                user = new User(params.name(), lastId);
            }


        }catch(Exception e){
            System.err.println(e);
        }
        return user;
    }

    @Override
    public User updateUser(int id, UserCreationParams params) {

        Connection conn = dbAccess.getConnection();
        String UpdatedUser = "UPDATE users SET name = ? WHERE id = ?";
        User user = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(UpdatedUser);
            pstmt.setString(1, params.name());
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

            user = new User(params.name(), id);


        }catch(Exception e){
            System.err.println(e);
        }
        return user;
    }

    @Override
    public User deleteUser(int id) {
        Connection conn = dbAccess.getConnection();

        String deletedUser = "DELETE FROM users WHERE id = ?";
        User user = getUserById(id);

        try {
            PreparedStatement pstmt = conn.prepareStatement(deletedUser);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        }catch(Exception e){
            System.err.println(e);
        }
        return user;
    }
}
