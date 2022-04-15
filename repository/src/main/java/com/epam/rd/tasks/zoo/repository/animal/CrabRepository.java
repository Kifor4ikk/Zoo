package com.epam.rd.tasks.zoo.repository.animal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.dto.animal.AnimalDto;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface CrabRepository {

    public void create(AnimalDto animalDto, AnimalHouse house) throws SQLException;
    public Crab getById(Long id) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    public void updateCrab(Long id);
    public void deleteCrab(Long id) throws SQLException;
}
