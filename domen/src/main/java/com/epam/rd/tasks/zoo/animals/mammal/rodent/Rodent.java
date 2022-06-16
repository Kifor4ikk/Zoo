package com.epam.rd.tasks.zoo.animals.mammal.rodent;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.mammal.Mammal;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;
import java.util.Set;

public abstract class Rodent extends Mammal {
    public Rodent(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }
}
