package com.epam.rd.tasks.zoo.animals.arachnid.tentacle;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.arachnid.Arachnid;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;
import java.util.Set;

public abstract class Tentacle extends Arachnid {
    public Tentacle(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, boolean isDeleted, int legCount) {
        super(name, describe, age, livingZone, climateZone, foodType, isDeleted, legCount);
    }
}
