package com.epam.rd.tasks.zoo.animals.crustacean.seashell;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class Seashell extends Crustacean {
    public Seashell(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String seashell) {
        super(name, describe, age, livingZone, climateZone, foodType, seashell);
    }

    public Seashell(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String seashell, boolean isDeleted) {
        super(name, describe, age, livingZone, climateZone, foodType, isDeleted, seashell);
    }
}
