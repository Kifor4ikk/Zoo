package com.epam.rd.tasks.zoo;

import com.epam.rd.tasks.zoo.service.animal.AnimalService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

@ComponentScan
public class SpringStarter {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext("Zoo/src/main/resources/SpringBean.xml");

        System.out.println("->" + context.getId());

        AnimalService animalService = context.getBean("animalService", AnimalService.class);

        System.out.println(animalService.getAllAnimals());


    }



}
