package com.squareGames.squareGamesAPI.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String designation;

    private int price;

    @Transient
    private List<String> notForDto = new ArrayList<>();



    public Product(String designation, int price) {
        notForDto.add("id");
        notForDto.add("notForDto");
        this.designation = designation;
        this.price = price;
    }


    public Product() {

    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getId() {
        return id;
    }

}
