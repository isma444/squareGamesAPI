package com.squareGames.squareGamesAPI.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "perso")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {

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
