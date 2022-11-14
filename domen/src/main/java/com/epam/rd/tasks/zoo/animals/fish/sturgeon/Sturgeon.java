package com.epam.rd.tasks.zoo.animals.fish.sturgeon;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.fish.Fish;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class Sturgeon extends Fish {
    public Sturgeon(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }
}
