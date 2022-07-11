package com.epam.rd.tasks.zoo.repository.animal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.food.Food;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AnimalMapper {

    public static <T extends Animal> T fromRawDataToAnimal(ResultSet data) throws ClassNotFoundException {
        T animal = null;
        try {
            while (data.next()) {
                if(animal == null){
                    Constructor<?> constructor = Class.forName(data.getString("animaltype")).getConstructor();
                    animal = (T) constructor.newInstance();
                }
                animal.setName(data.getString("name"));
                animal.setDescribe(data.getString("describe"));
                animal.setAge(data.getInt("age"));
                if(!animal.getClimateZone().contains(ClimateZone.valueOf(data.getString("climateType"))))
                    animal.getClimateZone().add(ClimateZone.valueOf(data.getString("climateType")));
                if(!animal.getLivingZone().contains(Class.forName(data.getString("zoneType"))))
                    animal.getLivingZone().add((Class<? extends AnimalHouse>) Class.forName(data.getString("zoneType")));
                if(!animal.getFoodType().contains(Class.forName(data.getString("foodType"))))
                    animal.getFoodType().add((Class<? extends Food>) Class.forName(data.getString("foodType")));
                animal.setDeleted(data.getBoolean("isDeleted"));
                animal.setId(data.getLong("id"));
            }

        } catch (ClassNotFoundException e) {
            throw new ClassCastException("DTO have problem with converting class");

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | SQLException e) {
            System.err.println("->" + e);
        }
        return animal;
    }


}
