package com.squareGames.squareGamesAPI.controller;

import com.squareGames.squareGamesAPI.DAO.UserDAO;
import com.squareGames.squareGamesAPI.DTO.UserDTO;
import com.squareGames.squareGamesAPI.entities.User;
import com.squareGames.squareGamesAPI.repository.UserRepository;
import com.squareGames.squareGamesAPI.services.UserCreationParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@RestController
public class UserController {

    //    @Qualifier("userDAOImpl")
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRepository repository;

    private UserDTO userToDTO(User user) {
        return new UserDTO(user.getName(), user.getId());
    }

    private User DtoToUser(UserDTO userDTO) {
        return new User(userDTO.name());
    }

    private Collection<UserDTO> ToDTOList(Collection<User> users) {
        return users.stream()
                .map(this::userToDTO)
                .toList();
    }

    @PostMapping("/users")
    public UserDTO add(@RequestBody UserDTO params) {
        User addedUser = repository.save(DtoToUser(params));
        return userToDTO(addedUser);
    }

    @GetMapping("/users")
    public Collection<UserDTO> getAll() {

        return ToDTOList((Collection<User>) repository.findAll());
    }

    @GetMapping("/users/{id}")
    public UserDTO get(@PathVariable int id) {

        return repository.findById(id)
                .map(this::userToDTO)
                .orElse(null);
    }

    @PutMapping("/users/{id}")
    public UserDTO update(@RequestBody UserDTO params, @PathVariable int id) {

        User user = repository.findById(id).orElse(null);
        user.setName(params.name());

        repository.save(user);

        return userToDTO(user);

    }



    @DeleteMapping("/users/{id}")
    public UserDTO delete(@PathVariable int id) {
        UserDTO user = repository.findById(id).map(this::userToDTO).orElse(null);
        repository.deleteById(id);
        return user;
    }


}
