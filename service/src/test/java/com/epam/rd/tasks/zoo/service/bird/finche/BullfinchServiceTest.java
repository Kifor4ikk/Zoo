package com.epam.rd.tasks.zoo.service.bird.finche;

import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.repository.animalhouse.AnimalHouseRepositoryImpl;
import com.epam.rd.tasks.zoo.service.animal.bird.finche.BullfinchService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class BullfinchServiceTest {

    private BullfinchService bullfinchService;
    private Connection connection;
    private AnimalHouseRepositoryImpl animalHouseRepository;
    private ApplicationContext context;

    public BullfinchServiceTest() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBean.xml", "TestSpringBean.xml");

        connection = context.getBean("connection", Connection.class);
        bullfinchService = context.getBean("bullfinchService",BullfinchService.class);
        animalHouseRepository = context.getBean("animalHouseRepository", AnimalHouseRepositoryImpl.class);
    }

    @BeforeTest
    public void beforeTest() throws SQLException, ClassNotFoundException, IOException {
        //Before test create
        connection.createStatement().execute("DROP SCHEMA public CASCADE; CREATE SCHEMA public;");

        BufferedReader reader = new BufferedReader(new FileReader("D:\\java\\Zoo\\SQL_Script.txt"));
        String temp = reader.readLine();
        StringBuilder createScript = new StringBuilder();
        while(temp != null){
            createScript.append(temp);
            createScript.append(System.lineSeparator());
            temp = reader.readLine();
        }
        connection.createStatement().execute(String.valueOf(createScript));

        animalHouseRepository.create(new Terrarium(1L, "Fields222222",1945, List.of(Crab.class, Bullfinch.class), ClimateZone.SUBTROPICAL));
    }

    @AfterTest
    public void afterTest() throws SQLException {
        connection.createStatement().execute("DROP SCHEMA public CASCADE; CREATE SCHEMA public;");
    }

    @Test
    public void integrationTest() throws SQLException, ClassNotFoundException {
        //Data before
        Bullfinch bullfinch = new Bullfinch("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
                Set.of(ClimateZone.SUBTROPICAL), Set.of(Bugs.class),"Test", "red");
        bullfinch.setId(1L);

        //Create test
        bullfinchService.create(new Bullfinch("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
                Set.of(), Set.of(Bugs.class),"Test", "red"), 1L);

        //Get test
        Assert.assertEquals(bullfinch.toString(),bullfinchService.getById(1L).toString());

        //Update test
        bullfinch.setName("Valentin");
        bullfinchService.update(bullfinch, bullfinch.getId());

        Assert.assertEquals(bullfinch.toString(),bullfinchService.getById(1L).toString());
        //softDelete test
        bullfinchService.setDeleteStatus(1L, true);
        try {
            bullfinchService.getById(1L);
        } catch (BadAnimalTypeException e){
            Assert.assertEquals(BadAnimalTypeException.class, e.getClass());
        }
        bullfinchService.setDeleteStatus(1L, false);
        Assert.assertEquals(bullfinch.toString(),bullfinchService.getById(1L).toString());

        //Hard delete
        bullfinchService.delete(1L);
        try {
            bullfinchService.getById(1L);
        } catch (BadAnimalTypeException e){
            Assert.assertEquals(BadAnimalTypeException.class, e.getClass());
        }
    }

}
