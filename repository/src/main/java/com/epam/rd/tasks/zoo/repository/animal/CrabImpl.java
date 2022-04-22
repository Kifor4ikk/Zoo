package com.epam.rd.tasks.zoo.repository.animal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.dto.animal.AnimalDto;
import com.epam.rd.tasks.zoo.dto.animal.CrabDto;
import com.epam.rd.tasks.zoo.exception.NotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.List.of;

public class CrabImpl extends AnimalRepoImpl implements CrabRepository {

    public CrabImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(AnimalDto animal, AnimalHouse animalHouse) throws SQLException {
        state().executeQuery(
                "INSERT INTO crab (name, describe,age,zoneType,climateZone,foodtype,isDeleted) VALUES ('" +
                        animal.getName() + "','" +
                        animal.getDescribe() + "','" +
                        animal.getDescribe() + "','" +
                        animal.getAge() + "','" +
                        animal.getLivingZone() + "','" +
                        animal.getClimateZone() + "','" +
                        animal.getFoodType() + "','" +
                        animal.isDeleted() +
                        "')");
    }

    @Override
    public Crab getById(Long id) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AnimalDto animalDto = new AnimalDto();
        try (ResultSet resultSet = state().executeQuery("SELECT * FROM crab WHERE isDeleted = false AND id = " + id);) {
            if (resultSet.next()) {
                animalDto.setId(id);
                animalDto.setName(resultSet.getString("name"));
                animalDto.setDescribe(resultSet.getString("describe"));
                animalDto.setAge(resultSet.getInt("age"));
                animalDto.setLivingZone(resultSet.getString("zoneType"));
                animalDto.setClimateZone(List.of(resultSet.getString("climateZone")
                        .substring(1,resultSet.getString("climateZone").length()-1).split(", ")));
                animalDto.setFoodType(resultSet.getString("foodType"));
                animalDto.setDeleted(resultSet.getBoolean("isDeleted"));
            }
            if (resultSet.getLong("id") == 0)
                throw new NotFoundException("Crab was not found");
        }
        return CrabDto.fromDTO(animalDto);
    }

    @Override
    public void updateCrab(Long id, AnimalDto animal) throws SQLException {
        state().executeQuery("UPDATE crab SET " +
                "name = '" + animal.getName() + "' " +
                ", describe = '" + animal.getDescribe() + "' " +
                ", age = '" + animal.getAge() + "'" +
                ", zonetype = '" + animal.getLivingZone() + "' " +
                ", foodtype = '" + animal.getFoodType() +"' " +
                ", climatezone = '" +  animal.getClimateZone() + "' " +
                "WHERE id = " + id);
    }

    @Override
    public void deleteCrab(Long id, boolean isDeleted) throws SQLException {
        state().executeQuery("UPDATE crab SET isDeleted = " + isDeleted + " WHERE Id = '" + id + "'");
    }

    @Override
    public void deleteHard(Long id) throws SQLException{
        state().executeQuery("DELETE FROM crab WHERE Id = '" + id + "'");
    }
}
