package com.epam.rd.tasks.zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.exception.ClassCastException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class AnimalHouseMapper {

    public <T extends AnimalHouse> T fromRowGeneralInfo(ResultSet rs) throws SQLException, ClassNotFoundException {

        T animalHouse = null;
        try {
            Constructor<?> constructor = Class.forName(rs.getString("zoneType")).getConstructor();
            animalHouse = (T) constructor.newInstance();
            animalHouse.setId(rs.getLong("id"));
            animalHouse.setName(rs.getString("name"));
            animalHouse.setArea(rs.getInt("area"));
            animalHouse.setClimateZone(ClimateZone.valueOf(rs.getString("climateType")));
        } catch (ClassNotFoundException e) {
            throw new ClassCastException("DTO have problem with converting class");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("->" + Arrays.toString(e.getStackTrace()));
        }
        return animalHouse;
    }

    public <T extends AnimalHouse> T addAnimalTypesFromRaw(T animalHouse, ResultSet rs) throws SQLException, ClassNotFoundException{

        animalHouse.getTypeOfAnimal().add((Class<? extends Animal>) Class.forName(rs.getString("animalType")));

        return animalHouse;
    }

    public <T extends Animal> AnimalHouse addAnimalLivingFromRaw(AnimalHouse animalHouse, ResultSet rs) throws SQLException, ClassNotFoundException{
        T animal = null;
        try {
            Constructor<?> constructor = Class.forName(rs.getString("animalType")).getConstructor();
            animal = (T) constructor.newInstance();
            animal.setId(rs.getLong("id"));
            animal.setName(rs.getString("name"));
            animal.setDescribe(rs.getString("describe"));
            animal.setAge(rs.getInt("age"));
            animal.setDeleted(rs.getBoolean("isDeleted"));
        } catch (ClassNotFoundException e) {
            throw new ClassCastException("DTO have problem with converting class");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("Error -> " + e);
        }
        animalHouse.addAnimal(animal);
        return animalHouse;
    }


}
