package ru.kifor4ik;

import ru.kifor4ik.entity.Animal;
import ru.kifor4ik.entity.animals.Tiger;
import ru.kifor4ik.service.ZooService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ZooService zooService = new ZooService();

        zooService.create("Booba","Ukrain","Kiev","Benderi","1488A","Norm takoi","+88005553535");
        zooService.sellTicket(0, BigDecimal.valueOf(100), false,2);
        zooService.sellTicket(0, BigDecimal.valueOf(100), true,2);
        zooService.addAnimal(0,new Tiger("Bebs",12, "Klassnii tigr net slov"));
        //System.out.println(Arrays.toString(zooService.getZooList().toArray()));
        zooService.changeAnimal(0,0,"Bebey",1,null);
        System.out.println(Arrays.toString(zooService.getZooList().toArray()));
    }
}
