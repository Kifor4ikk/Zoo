package com.epam.rd.tasks.zoo.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public abstract class AnimalHouse {
    private Long id;
    private String name;
    private Integer area;
    private Class <? extends Animal> typeOfAnimal;
    private final List<Animal> animalList = new ArrayList<>();
    private ClimateZone climateZone;

    public AnimalHouse(Long id, String name, Integer area,
                       Class<? extends Animal> typeOfAnimal, ClimateZone climateZone) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.typeOfAnimal = typeOfAnimal;
        this.climateZone = climateZone;
    }
}
