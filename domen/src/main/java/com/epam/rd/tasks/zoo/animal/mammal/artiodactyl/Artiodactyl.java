package com.epam.rd.tasks.zoo.animal.mammal.artiodactyl;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.mammal.Mammal;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class Artiodactyl extends Mammal {
    public Artiodactyl(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String tail) {
        super(name, describe, age, livingZone, climateZone, foodType, tail);
    }
}
