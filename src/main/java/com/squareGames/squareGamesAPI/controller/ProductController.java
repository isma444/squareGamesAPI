package com.squareGames.squareGamesAPI.controller;

import com.squareGames.squareGamesAPI.DTO.ProductDto;
import com.squareGames.squareGamesAPI.entities.Product;
import com.squareGames.squareGamesAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository repository;

    private ProductDto productToDTO(Product product) {
        return new ProductDto(product.getDesignation(), product.getPrice());
    }

    private Product DtoToProduct(ProductDto productDto) {
        return new Product(productDto.designation(),productDto.price());
    }

    private Collection<ProductDto> ToDTOList(Collection<Product> products) {
        return products.stream()
                .map(this::productToDTO)
                .toList();
    }
    @PostMapping("/product")
    public ProductDto add(@RequestBody ProductDto productDto) {
        Product product = repository.save(DtoToProduct(productDto));
        return productToDTO(product);
    }
    @GetMapping("/product")
    public Collection<ProductDto> getAll(){
        return ToDTOList((Collection<Product>) repository.findAll());
    }
    @GetMapping("/product/{id}")
    public ProductDto get(@PathVariable int id){
        return repository.findById(id)
                .map(this::productToDTO)
                .orElse(null);
    }
    @PutMapping("/product/{id}")
    public ProductDto update(@PathVariable int id, @RequestBody ProductDto productDto){
        Product product = repository.findById(id).orElse(null);
        product.setDesignation(productDto.designation());
        product.setPrice(productDto.price());

        repository.save(product);

        return productToDTO(product);
    }
    @DeleteMapping("/product/{id}")
    public ProductDto delete(@PathVariable int id){
        ProductDto productDto = repository.findById(id).map(this::productToDTO).orElse(null);
        repository.deleteById(id);
        return productDto;
    }
}
