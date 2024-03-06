package com.squareGames.squareGamesAPI.DTO;

import com.squareGames.squareGamesAPI.entities.EntityObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


public final class EntityDto {


    private Map<String, Object> attributs = new HashMap<>();

    public Map<String, Object> getAttributs() {
        return attributs;
    }


    public EntityDto(){

    }
    public EntityDto(EntityObject entity)  {
        Field[] fields = entity.getClass().getDeclaredFields();

        Arrays.stream(fields)
                .filter(field -> !entity.getNotForDto().contains(field.getName()))
                .forEach(field -> {
                    if(!field.isAccessible()) field.setAccessible(true);
                    try {
                        String name = field.getName();
                        Object value = field.get(entity);
                        attributs.put(name, value);
                    }catch(Exception e){
                        System.out.println(e);
                    }

                });
    }

    public void convert(Map<String , Object> data){
        this.attributs = data;
    }

    public Map<String, Object> entityToDTO(EntityObject object) {
        return new EntityDto(object).getAttributs();
    }

    public <T extends EntityObject> T dtoToEntity(T entity) {

        AtomicReference<T> atomicEntity = new AtomicReference<>(entity);

        Field[] fields = atomicEntity.get().getClass().getDeclaredFields();
        Map<String, Object> fieldsDto = this.getAttributs();

        Arrays.stream(fields)
                .filter(field -> !atomicEntity.get().getNotForDto().contains(field.getName()) && fieldsDto.containsKey(field.getName()))
                .forEach(field -> {

                    try {

                        field.setAccessible(true);
                        field.set(atomicEntity.get(), fieldsDto.get(field.getName()));

                    }catch(Exception e){
                        System.out.println(e);

                    }
                });
        return atomicEntity.get();

    }


}
