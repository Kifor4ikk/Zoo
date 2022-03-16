package com.epam.rd.tasks.zoo.animals;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;

public abstract class Animal {

    private Long id;
    private String name;
    private String describe;
    private int age;
    private Class<? extends AnimalHouse> livingZone;
    private ClimateZone climateZone;
    private Class<? extends Food> foodType;

    public Animal(String name, String describe, int age, Class<? extends AnimalHouse> livingZone,
                  ClimateZone climateZone, Class<? extends Food> foodType) {
        this.name = name;
        this.describe = describe;
        this.age = age;
        this.livingZone = livingZone;
        this.climateZone = climateZone;
        this.foodType = foodType;
    }
}
