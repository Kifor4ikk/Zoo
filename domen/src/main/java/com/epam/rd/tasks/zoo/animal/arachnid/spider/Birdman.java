package com.epam.rd.tasks.zoo.animal.arachnid.spider;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public class Birdman extends Spider{

    private String color;

    public Birdman(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, boolean isDeleted, int legCount, String color) {
        super(name, describe, age, livingZone, climateZone, foodType, isDeleted, legCount);
        this.color = color;
    }
}
