package com.epam.rd.tasks.zoo.animal.bird.pigeonlike;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.bird.Bird;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class PigeonLike extends Bird {
    public PigeonLike(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String wings) {
        super(name, describe, age, livingZone, climateZone, foodType, wings);
    }
}
