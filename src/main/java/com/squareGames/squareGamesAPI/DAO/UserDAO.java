package com.squareGames.squareGamesAPI.DAO;
import com.squareGames.squareGamesAPI.DTO.UserDTO;
import com.squareGames.squareGamesAPI.entities.User;
import com.squareGames.squareGamesAPI.services.UserCreationParams;

import java.util.List;
import java.util.UUID;

public interface UserDAO {
    public List<User> getAllUsers();
    public User getUserById(UUID id);
    public User addUser(UserCreationParams params);
    public User updateUser( UUID id, UserCreationParams params);
    public User deleteUser(UUID id);

}
