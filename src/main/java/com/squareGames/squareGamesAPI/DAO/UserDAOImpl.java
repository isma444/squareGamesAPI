package com.squareGames.squareGamesAPI.DAO;
import com.squareGames.squareGamesAPI.entities.User;
import com.squareGames.squareGamesAPI.services.UserCreationParams;
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
        return users.stream()
                .filter(user -> user.getId().equals(id))
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
    public User updateUser(UUID id, UserCreationParams params) {

        User userToUpdate = this.users.stream()
                .filter(user -> user.getId().equals(id))
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
    public User deleteUser(UUID id) {
        User userToRemove = this.users.stream()
                .filter(user -> user.getId().equals(id))
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
