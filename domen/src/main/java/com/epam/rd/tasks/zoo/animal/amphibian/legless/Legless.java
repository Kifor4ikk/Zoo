package com.epam.rd.tasks.zoo.animal.amphibian.legless;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.amphibian.Amphibian;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class Legless extends Amphibian {
    public Legless(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }
}
