package com.epam.rd.tasks.zoo;

import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepositoryImpl;
import com.epam.rd.tasks.zoo.service.animal.bird.finch.BullfinchService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Set;

@ComponentScan
public class SpringStarter {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext( "SpringBeanConfig.xml");
        AnimalTypeRepositoryImpl animalRepository = context.getBean(AnimalTypeRepositoryImpl.class);


        BullfinchService bullfinchService = context.getBean(BullfinchService.class);
        System.out.println(bullfinchService.getById(2L));


        System.out.println(animalRepository.getAnimalsIdsWithTypeOfAnimal(0, 10));


    }
}