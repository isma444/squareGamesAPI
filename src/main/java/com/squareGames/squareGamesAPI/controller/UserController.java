package com.squareGames.squareGamesAPI.controller;
import com.squareGames.squareGamesAPI.DAO.UserDAO;
import com.squareGames.squareGamesAPI.DTO.UserDTO;
import com.squareGames.squareGamesAPI.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    private UserDTO userToDTo(User user){
        return new UserDTO(user.getName());
    }
    @PostMapping("/users")
    public void add(@RequestBody UserDTO user){
        userDAO.addUser(user);
    }
    @GetMapping("/users")
    public List<User> getAll(){
        return userDAO.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO get(@PathVariable UUID id){
        User user = userDAO.getUserById(id);
        return userToDTo(user);
    }

    @PutMapping("/users/{id}")
    public void update(@RequestBody UserDTO userDTO, @PathVariable UUID id){
        userDAO.updateUser(id ,userDTO);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable UUID id){
        userDAO.deleteUser(id);
    }


}
