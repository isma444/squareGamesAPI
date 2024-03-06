package com.squareGames.squareGamesAPI.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntity> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
