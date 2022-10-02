package com.epam.rd.tasks.zoo.service;

import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.repository.database.Database;
import com.epam.rd.tasks.zoo.service.animal.AnimalService;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class AnimalServiceTest {

    private AnimalService animalServiceMapper = new AnimalService(Database.connectWithDataBase());

    public AnimalServiceTest() throws SQLException, ClassNotFoundException {}

    @Test
    public void test() throws SQLException, ClassNotFoundException {
        AnimalService animalServiceMapper = new AnimalService(Database.connectWithDataBase());

        System.out.println(animalServiceMapper.getAllAnimals());
        System.out.println(animalServiceMapper.getAllTypes());
        System.out.println(animalServiceMapper.getCountOfAllTypes());
    }
}
