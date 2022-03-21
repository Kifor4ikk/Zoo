package com.epam.rd.tasks.zoo.animals.arachnid.mite;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.arachnid.Arachnid;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;

public abstract class Mite extends Arachnid {
    public Mite(String name, String describe, int age, Class<? extends AnimalHouse> livingZone, List<ClimateZone> climateZone, Class<? extends Food> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }
}
