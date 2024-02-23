package com.squareGames.squareGamesAPI.DAO;
import com.squareGames.squareGamesAPI.DTO.UserDTO;
import com.squareGames.squareGamesAPI.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserDAOImpl implements UserDAO{

    private List<User> users = new ArrayList<User>();
    @Override
    public List<User> getAllUsers() {
        return this.users;
    }

    @Override
    public User getUserById(UUID id) {
        for(User user : this.users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = new User(userDTO.name());
        users.add(user);
    }

    @Override
    public void updateUser(UUID id, UserDTO userDTO) {
        for(User oldUser : this.users){
            if(id.equals(oldUser.getId())){
                oldUser.setName(userDTO.name());
            }
        }
    }

    @Override
    public void deleteUser(UUID id) {
        for(User user : this.users){
            if(user.getId().equals(id)){
                this.users.remove(user);
            }
        }
    }
}
