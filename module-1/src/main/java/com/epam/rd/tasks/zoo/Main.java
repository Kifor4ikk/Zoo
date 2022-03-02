package com.epam.rd.tasks.zoo;

import com.epam.rd.tasks.zoo.entity.animals.Tiger;
import com.epam.rd.tasks.zoo.service.ZooService;
import com.epam.rd.tasks.zoo.entity.animals.Rabbit;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ZooService zooService = new ZooService();

        zooService.create("ZooPark AFRICA IS CALLING","Oceania","London","Of winners","12A",
                "Best Zoo at all Oceania! Big brother recommended!","Phone number: +88005553535\nVK: https://vk.com/kifor4ik");

        zooService.sellTicket(0, BigDecimal.valueOf(100), false,2);
        zooService.sellTicket(0, BigDecimal.valueOf(100), true,2);

        zooService.addAnimal(0,new Tiger("Biba",12, "Klassnii tigr net slov"));
        zooService.addAnimal(0,new Tiger("Boba",8, "Klassnii tigr net slov x2"));

        zooService.addAnimal(0,new Rabbit("Koka",2, "Klassnii krol net slov x2"));
        zooService.addAnimal(0,new Rabbit("Kola",3, "Klassnii krol net slov x2"));



        zooService.addFood(0,"Meet",6);
        zooService.addFood(0,"Meet",1);
        zooService.addFood(0,"Weet",120);
        zooService.addFood(0,"Weet",100);

        zooService.addAnimalHouse(0,"Tiger cage", 16);
        zooService.addAnimalHouse(0,"Rabit cage", 4);
//
        zooService.addAnimalToHouse(0,0,1);
        zooService.addAnimalToHouse(0,0,0);
//
        zooService.addAnimalToHouse(0,1,2);
        zooService.addAnimalToHouse(0,1,3);

        System.out.println(Arrays.toString(zooService.getZooList().toArray()));
        System.out.println("----------------------------------");
        zooService.getAnimalById(0,0).sound();
        zooService.getAnimalById(0,1).sound();
        zooService.getAnimalById(0,2).sound();
        zooService.getAnimalById(0,3).sound();

    }
}
