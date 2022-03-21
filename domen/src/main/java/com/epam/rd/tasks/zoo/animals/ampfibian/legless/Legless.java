package com.epam.rd.tasks.zoo.animals.ampfibian.legless;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.ampfibian.Amphibian;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;

public abstract class Legless extends Amphibian {
    public Legless(String name, String describe, int age, Class<? extends AnimalHouse> livingZone, List<ClimateZone> climateZone, Class<? extends Food> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }
}