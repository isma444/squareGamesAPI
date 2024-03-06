package com.squareGames.squareGamesAPI.DTO;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public final class UserDTO {
    @NotBlank(message = "you must give a name")
    private final String name;
    @Min(value = 1, message = "la valeur doit être au moins 1")
    @Max(value = 10000 , message = "la valeur ne dois pas dépasser 10000")
    private final int id;
    public UserDTO(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String name() {
        return name;
    }

    public int id() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserDTO) obj;
        return Objects.equals(this.name, that.name) &&
                this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "UserDTO[" +
                "name=" + name + ", " +
                "id=" + id + ']';
    }


}
