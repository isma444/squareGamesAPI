package com.squareGames.squareGamesAPI.DTO;

import com.squareGames.squareGamesAPI.entities.EntityObject;
import com.squareGames.squareGamesAPI.entities.Product;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ProductDto extends EntityObject {
    private  String designation ;
    private  int price ;

    private Map<String, Object> attributs = new HashMap<>();

    public Map<String, Object> getAttributs() {
        return attributs;
    }

    public ProductDto(
            String designation,
            int price
    ) {
        this.designation = designation;
        this.price = price;
    }

    public ProductDto(Product product)  {
        Field[] fields = product.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try{
          if(!product.getNotForDto().contains(field.getName())){
              System.out.println(field.getName());
              String nameAttribut = field.getName();
              Object value = field.get(product);
              attributs.put(nameAttribut, value);
            }
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }

    public String designation() {
        return designation;
    }

    public int price() {
        return price;
    }




}
