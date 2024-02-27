package com.squareGames.squareGamesAPI.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbAccess {
    private static DbAccess instance;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:6603/square_games";
    private static final String USER = "root";
    private static final String PASSWD = "helloworld";

    public static synchronized DbAccess getInstance() {
        if (instance == null) {
            instance = new DbAccess();
        }
        return instance;
    }

    private DbAccess() {

    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
                connection = DriverManager.getConnection(URL, USER, PASSWD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }
}