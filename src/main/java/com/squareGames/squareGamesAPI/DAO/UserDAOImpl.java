package com.squareGames.squareGamesAPI.DAO;
import com.squareGames.squareGamesAPI.entities.User;
import com.squareGames.squareGamesAPI.services.UserCreationParams;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDAOImpl implements UserDAO{

    private List<User> users = new ArrayList<User>();
    @Override
    public List<User> getAllUsers() {
        return this.users;
    }

    @Override
    public User getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User addUser(UserCreationParams params) {
        User user = new User(params.name());
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(int id, UserCreationParams params) {

        User userToUpdate = this.users.stream()
                .filter(user -> user.getId() == id )
                .findFirst()
                .orElse(null);
            userToUpdate.setName(params.name());
            return userToUpdate;
//        for(User oldUser : this.users){
//            if(id.equals(oldUser.getId())){
//                oldUser.setName(userDTO.name());
//            }
//        }

    }

    @Override
    public User deleteUser(int id) {
        User userToRemove = this.users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
//        for(User user : this.users){
//            if(user.getId().equals(id)){
//                userToRemove = user;
//            }
//        }
        if(userToRemove != null){
            this.users.remove(userToRemove);
        }
        return userToRemove;
    }
}
