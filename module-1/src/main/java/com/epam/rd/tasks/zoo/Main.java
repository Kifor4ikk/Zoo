package com.epam.rd.tasks.zoo;

import com.epam.rd.tasks.zoo.entity.animals.Tiger;
import com.epam.rd.tasks.zoo.service.ZooService;
import com.epam.rd.tasks.zoo.entity.animals.Rabbit;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ZooService zooService = new ZooService();

        zooService.create("Booba","Ukrain","Kiev","Benderi","1488A",
                "Norm takoi","Phone number: +88005553535\nVK: https://vk.com/kifor4ik");

        zooService.sellTicket(0, BigDecimal.valueOf(100), false,2);
        zooService.sellTicket(0, BigDecimal.valueOf(100), true,2);

        zooService.addAnimal(0,new Tiger("Bebs",12, "Klassnii tigr net slov"));
        zooService.addAnimal(0,new Rabbit("Bibs",4, "Klassnii krol net slov"));

        zooService.changeAnimal(0,0,"Bebey",1,null);

        System.out.println(Arrays.toString(zooService.getZooList().toArray()));
        System.out.println("----------------------------------");
        zooService.getAnimalById(0,0).sound();
        zooService.getAnimalById(0,1).sound();
    }
}
