package com.epam.rd.tasks.zoo.animals.amphibian.tailed;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.amphibian.Amphibian;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;
import java.util.Set;

public abstract class Tailed extends Amphibian {
    public Tailed(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }
}
