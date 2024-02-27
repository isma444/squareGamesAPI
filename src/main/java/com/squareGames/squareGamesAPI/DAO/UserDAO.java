package com.squareGames.squareGamesAPI.DAO;
import com.squareGames.squareGamesAPI.entities.User;
import com.squareGames.squareGamesAPI.services.UserCreationParams;

import java.util.Collection;

public interface UserDAO  {
    public Collection<User> getAllUsers();
    public User getUserById(int id);
    public User addUser(UserCreationParams params);
    public User updateUser(int id, UserCreationParams params);
    public User deleteUser(int id);

}
