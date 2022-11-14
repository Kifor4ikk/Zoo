package com.epam.rd.tasks.zoo.animals.arachnid.spider;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.arachnid.Arachnid;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class Spider extends Arachnid {
    public Spider(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, boolean isDeleted, int legCount) {
        super(name, describe, age, livingZone, climateZone, foodType, isDeleted, legCount);
    }
}
