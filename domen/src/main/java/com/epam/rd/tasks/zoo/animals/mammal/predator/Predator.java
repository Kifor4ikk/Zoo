package com.epam.rd.tasks.zoo.animals.mammal.predator;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.mammal.Mammal;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;
import java.util.Set;

public abstract class Predator extends Mammal {
    public Predator(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }

    public Predator() {
        super();
    }
}
