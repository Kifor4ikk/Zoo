package com.epam.rd.tasks.zoo.repository.animal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.food.Food;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AnimalMapper {

    public Animal fromRawToAnimal(ResultSet data, Animal animal) throws SQLException, ClassNotFoundException {

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
        animal.setId(data.getLong("ID"));
        return animal;
    }

    //Just info about living zone,climate zone, food. By animal type!
    public <T extends Animal> Animal getInfoFromRaw(ResultSet data, Class<? extends Animal> animaltype) throws SQLException {
        T animal = null;
        try {
            while (data.next()) {
                if (animal == null) {
                    Constructor<?> constructor = animaltype.getConstructor();
                    animal = (T) constructor.newInstance();
                }
                if(!animal.getClimateZone().contains(ClimateZone.valueOf(data.getString("climateType"))))
                    animal.getClimateZone().add(ClimateZone.valueOf(data.getString("climateType")));
                if(!animal.getLivingZone().contains(Class.forName(data.getString("zoneType"))))
                    animal.getLivingZone().add((Class<? extends AnimalHouse>) Class.forName(data.getString("zoneType")));
                if(!animal.getFoodType().contains(Class.forName(data.getString("foodType"))))
                    animal.getFoodType().add((Class<? extends Food>) Class.forName(data.getString("foodType")));
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if(animal == null) throw new BadAnimalTypeException("Type " + animaltype.getName() + " is not exist");
        return animal;
    }
}
