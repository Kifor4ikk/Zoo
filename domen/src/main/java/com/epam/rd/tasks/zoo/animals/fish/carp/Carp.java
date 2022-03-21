package com.epam.rd.tasks.zoo.animals.fish.carp;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.fish.Fish;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;

public abstract class Carp extends Fish {
    public Carp(String name, String describe, int age, Class<? extends AnimalHouse> livingZone, List<ClimateZone> climateZone, Class<? extends Food> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }
}
