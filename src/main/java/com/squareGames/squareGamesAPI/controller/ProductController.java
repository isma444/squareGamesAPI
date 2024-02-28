package com.squareGames.squareGamesAPI.controller;

import com.squareGames.squareGamesAPI.DTO.ProductDto;
import com.squareGames.squareGamesAPI.entities.Product;
import com.squareGames.squareGamesAPI.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
public class ProductController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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
    public Map<String, Object> add(@RequestBody ProductDto productDto) {
        LOGGER.info("adding a new product");
        Product product = repository.save(DtoToProduct(productDto));
        ProductDto productdto = new ProductDto(product);
        return productdto.getAttributs();
    }
    @GetMapping("/product")
    public Collection<ProductDto> getAll(){
        LOGGER.info("Getting all products");
        return ToDTOList((Collection<Product>) repository.findAll());
    }
    @GetMapping("/product/{id}")
    public ProductDto get(@PathVariable int id){
        LOGGER.info("Getting the product");
        return repository.findById(id)
                .map(this::productToDTO)
                .orElse(null);
    }
    @PutMapping("/product/{id}")
    public ProductDto update(@PathVariable int id, @RequestBody ProductDto productDto){
        LOGGER.info("updating the product");
        Product product = repository.findById(id).orElse(null);
        product.setDesignation(productDto.designation());
        product.setPrice(productDto.price());

        repository.save(product);

        return productToDTO(product);
    }
    @DeleteMapping("/product/{id}")
    public ProductDto delete(@PathVariable int id){
        LOGGER.info("Destroy the product");
        ProductDto productDto = repository.findById(id).map(this::productToDTO).orElse(null);
        repository.deleteById(id);
        return productDto;
    }
}
