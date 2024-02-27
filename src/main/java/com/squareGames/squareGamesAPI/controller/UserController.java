package com.squareGames.squareGamesAPI.controller;
import com.squareGames.squareGamesAPI.DAO.UserDAO;
import com.squareGames.squareGamesAPI.DTO.UserDTO;
import com.squareGames.squareGamesAPI.entities.User;
import com.squareGames.squareGamesAPI.services.UserCreationParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;


@RestController
public class UserController {

//    @Qualifier("userDAOImpl")
    @Autowired
    private UserDAO userDAO;

    private UserDTO userToDTO(User user){
        return new UserDTO(user.getName(), user.getId());
    }
    private Collection<UserDTO> ToDTOList(Collection<User> users){
            return users.stream()
                    .map(this::userToDTO)
                    .toList();
    }
    @PostMapping("/users")
    public UserDTO add(@RequestBody UserCreationParams params){
        User addedUser = userDAO.addUser(params);
        return userToDTO(addedUser);
    }
    @GetMapping("/users")
    public Collection<UserDTO> getAll(){
        return ToDTOList(userDAO.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public UserDTO get(@PathVariable int id){
        User user = userDAO.getUserById(id);
        return userToDTO(user);
    }

    @PutMapping("/users/{id}")
    public UserDTO update(@RequestBody UserCreationParams params, @PathVariable int id){
        User updatedUser = userDAO.updateUser(id ,params);
        return userToDTO(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public UserDTO delete(@PathVariable int id){
        User deletedUser = userDAO.deleteUser(id);
        return userToDTO(deletedUser);
    }


}
