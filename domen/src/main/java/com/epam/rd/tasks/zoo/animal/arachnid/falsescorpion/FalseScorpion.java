package com.epam.rd.tasks.zoo.animal.arachnid.falsescorpion;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.arachnid.Arachnid;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class FalseScorpion extends Arachnid {
    public FalseScorpion(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, boolean isDeleted, int legCount) {
        super(name, describe, age, livingZone, climateZone, foodType, isDeleted, legCount);
    }
}
