package com.squareGames.squareGamesAPI.controller;

import com.squareGames.squareGamesAPI.DTO.EntityDto;
import com.squareGames.squareGamesAPI.entities.EntityObject;
import com.squareGames.squareGamesAPI.entities.Role;
import com.squareGames.squareGamesAPI.entities.UserEntity;
import com.squareGames.squareGamesAPI.repository.RoleRepository;
import com.squareGames.squareGamesAPI.repository.UserEntityRepository;
import org.hibernate.sql.ast.tree.SqlAstTreeLogger_$logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/userentity/")
public class UserEntityController {

    @Autowired
    private UserEntityRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    private EntityDto dto = new EntityDto() ;
    private Collection<Map<String, Object>> ToDTOList(Collection<EntityObject> entities) {
        return entities.stream()
                .map(entity -> dto.entityToDTO(entity))
                .toList();
    }
    @PostMapping("add")
    public Map<String, Object> add(@RequestBody Map<String, Object> response) {

        Object password = response.get("password");
        response.put("password", passwordEncoder.encode((CharSequence) password));
        EntityDto entityDto = new EntityDto();
        entityDto.convert(response);
        UserEntity emptyUser = new UserEntity();
        Optional<Role> roleOptionnal = roleRepository.findByName(String.valueOf(response.get("role")));
        Role role = roleOptionnal.orElse(null);
        emptyUser.getRoles().add(role);
        EntityObject user = repository.save(entityDto.dtoToEntity(emptyUser));
        EntityDto userdto = new EntityDto(user);
        return userdto.getAttributs();

    }


    @GetMapping("all")
    public Collection<Map<String, Object>>getAll(){

        List<Map<String, Object>> entityList = new ArrayList<>();
        StreamSupport.stream(repository.findAll().spliterator(), false)
                .forEach(entity ->{
                    entityList.add(dto.entityToDTO(entity));

                });
        return entityList;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String getAdmin(){
        return "admin";
    }
}
