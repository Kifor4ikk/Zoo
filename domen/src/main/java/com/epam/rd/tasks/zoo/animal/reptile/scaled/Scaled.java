package com.epam.rd.tasks.zoo.animal.reptile.scaled;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.reptile.Reptile;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class Scaled extends Reptile {
    public Scaled(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }
}
