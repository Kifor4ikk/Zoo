package com.epam.rd.tasks.zoo.service;

import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.repository.database.Database;
import com.epam.rd.tasks.zoo.service.animal.AnimalService;
import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.SQLException;

public class AnimalServiceTest {

    private AnimalService animalService = new AnimalService(Database.connectWithDataBase());

    public AnimalServiceTest() throws SQLException, ClassNotFoundException {}

    @Test
    public void test() throws SQLException, ClassNotFoundException {
//
//        //System.out.println(animalService.getAllAnimals());
//        for(Animal animal : animalService.getAllAnimals())
//            System.out.println(animal.toString());
//
//        for(String type : animalService.getAllTypes())
//            System.out.println(type);
//
//        System.out.println(animalService.getCountOfAllTypes());
//
//        animalService.getAnimalByName("").forEach(o ->System.out.println(o.toString()));
//        animalService.getAnimalByCreationDate(java.sql.Date.valueOf("2022-10-13")).forEach(o ->System.out.println(o.toString()));
//        System.out.println(animalService.getAnimalAndHouseByAnimalId(4L));
//        System.out.println(animalService.getAllAnimalsInHouseByHouseId(1L));
    }
}
