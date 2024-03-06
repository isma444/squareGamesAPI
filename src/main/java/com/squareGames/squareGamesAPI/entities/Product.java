package com.squareGames.squareGamesAPI.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Product extends EntityObject{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String designation;

    private int price;




    public Product(String designation, int price) {
        super();
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
