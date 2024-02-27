package com.squareGames.squareGamesAPI.entities;

public class User  {

    private int id;
    private String name;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
