package com.epam.rd.tasks.zoo.animals.bird;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;

public abstract class Bird extends Animal {
    public Bird(String name, String describe, int age, Class<? extends AnimalHouse> livingZone, List<ClimateZone> climateZone, Class<? extends Food> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }

    public Bird(String name, String describe, int age, Class<? extends AnimalHouse> livingZone, List<ClimateZone> climateZone, Class<? extends Food> foodType, boolean isDeleted) {
        super(name, describe, age, livingZone, climateZone, foodType, isDeleted);
    }
}
