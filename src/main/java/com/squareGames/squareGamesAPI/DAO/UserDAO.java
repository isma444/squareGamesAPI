package com.squareGames.squareGamesAPI.DAO;
import com.squareGames.squareGamesAPI.DTO.UserDTO;
import com.squareGames.squareGamesAPI.entities.User;
import java.util.List;
import java.util.UUID;

public interface UserDAO {
    public List<User> getAllUsers();
    public User getUserById(UUID id);
    public void addUser(UserDTO userDTO);
    public void updateUser( UUID id, UserDTO userDTO);
    public void deleteUser(UUID id);

}
