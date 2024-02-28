package com.squareGames.squareGamesAPI.entities;

import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityObject {

    @Transient
    protected List<String> notForDto = new ArrayList<>();

    public  List<String> getNotForDto() {
        return notForDto;
    }
}
