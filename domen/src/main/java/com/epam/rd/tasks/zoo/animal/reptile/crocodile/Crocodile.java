package com.epam.rd.tasks.zoo.animal.reptile.crocodile;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.reptile.Reptile;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class Crocodile extends Reptile {
    public Crocodile(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }
}
