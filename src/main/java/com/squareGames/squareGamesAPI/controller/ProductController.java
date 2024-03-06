package com.squareGames.squareGamesAPI.controller;


import com.squareGames.squareGamesAPI.DTO.EntityDto;
import com.squareGames.squareGamesAPI.entities.EntityObject;
import com.squareGames.squareGamesAPI.entities.Product;
import com.squareGames.squareGamesAPI.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.function.Function;
import java.util.stream.StreamSupport;


@RestController
public class ProductController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ProductRepository repository;

    private EntityDto dto = new EntityDto() ;



    private Collection<Map<String, Object>> ToDTOList(Collection<EntityObject> entities) {
        return entities.stream()
                .map(entity -> dto.entityToDTO(entity))
                .toList();
    }
    @PostMapping("/product")
    public Map<String, Object> add(@RequestBody Map<String, Object> response) {

        LOGGER.info("adding a new product");
        EntityDto entityDto = new EntityDto();
        entityDto.convert(response);
        Product emptyProduct = new Product();
        EntityObject product = repository.save((Product) entityDto.dtoToEntity(emptyProduct));
        EntityDto productdto = new EntityDto(product);
        return productdto.getAttributs();

    }
    @GetMapping("/product")
    public Collection<Map<String, Object>>getAll(){
        LOGGER.info("Getting all products");
        List<Map<String, Object>> entityList = new ArrayList<>();
        StreamSupport.stream(repository.findAll().spliterator(), false)
                .forEach(entity ->{
                    entityList.add(dto.entityToDTO(entity));

                });
        return entityList;
    }
    @GetMapping("/product/{id}")
    public Map<String, Object> get(@PathVariable int id){
        LOGGER.info("Getting the product");
        return repository.findById(id)
                .map(entity -> dto.entityToDTO(entity))
                .orElse(null);
    }
    @PutMapping("/product/{id}")
    public Map<String, Object> update(@PathVariable int id, @RequestBody Map<String , Object> response){
        LOGGER.info("updating the product");

        Product product = repository.findById(id).get();
        dto.convert(response);
        Product newProduct =  dto.dtoToEntity(product);
        repository.save(newProduct);
        return dto.entityToDTO(newProduct);
    }
    @DeleteMapping("/product/{id}")
    public Map<String,Object> delete(@PathVariable int id){
        LOGGER.info("Destroy the product");
        Map<String, Object> entityDto = repository.findById(id).map(entity -> dto.entityToDTO(entity)).orElse(null);
        repository.deleteById(id);
        return entityDto;
    }
}
