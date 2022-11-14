package com.epam.rd.tasks.zoo.animals.arachnid;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class Arachnid extends Animal {

    private int legCount;

    public Arachnid(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, boolean isDeleted, int legCount) {
        super(name, describe, age, livingZone, climateZone, foodType, isDeleted);
        this.legCount = legCount;
    }
}
