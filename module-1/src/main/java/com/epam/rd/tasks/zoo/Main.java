package com.epam.rd.tasks.zoo;

import com.epam.rd.tasks.zoo.entity.Zoo;
import com.epam.rd.tasks.zoo.entity.animals.*;
import com.epam.rd.tasks.zoo.service.ZooService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        ZooService zooService = new ZooService();

        zooService.create("ZooPark AFRICA IS CALLING","Oceania","London","Of winners","12A",
                "Best Zoo at all Oceania! Big brother recommended!","Phone number: +88005553535\nVK: https://vk.com/kifor4ik");

        zooService.sellTicket(0, BigDecimal.valueOf(100), false,2);
        zooService.sellTicket(0, BigDecimal.valueOf(100), true,2);

        zooService.addAnimal(0, new Mammal("Tiger",12, "Normal tiger"));
        zooService.addAnimal(0, new Bird("Bird",1,"Bird"));

        zooService.addAnimalHouse(0,"Tiger cage", 16);
        zooService.addAnimalHouse(0,"Rabit cage", 4);
        zooService.addAnimalHouse(0,"Duck cage", 7);
        zooService.addAnimalHouse(0,"Parrot cage", 6);
        zooService.addAnimalHouse(0,"Fish aquarium", 3);

        zooService.addAnimalToHouse(0,0,0);

        zooService.addFood(0,"Meet",6);
        zooService.addFood(0,"Meet",1);
        zooService.addFood(0,"Weet",120);
        zooService.addFood(0,"Weet",100);

        System.out.println(Arrays.toString(zooService.getZooList().toArray()));
        System.out.println("----------------------------------");

        System.out.println("Birds = " + zooService.getZooById(0).getAnimals().stream()
                .filter(animal -> animal instanceof Bird)
                .collect(Collectors.toList()).size());


    }
}
