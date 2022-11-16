package com.epam.rd.tasks.zoo.animal.insect.lepidoptera;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.insect.Insect;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class Lepidoptera extends Insect {
    public Lepidoptera(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }
}
