package com.epam.rd.tasks.zoo;

import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepositoryImpl;
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
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");

        Bullfinch bullfinch = new Bullfinch("You are my test now", LocalTime.now().toString(), 1, Set.of(Terrarium.class),
                Set.of(ClimateZone.SUBTROPICAL), Set.of(Bugs.class), "You are my wings now", "Yes indeed");

        BullfinchService bullfinchService = context.getBean(BullfinchService.class);

        bullfinchService.create(bullfinch, new Field());
        System.out.println(bullfinchService.getById(2L));
    }
}
