package com.epam.rd.tasks.zoo.animals.bird.penguin;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;
import java.util.Set;

public class EmperorPenguin extends Penguin{
    public EmperorPenguin(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String wings) {
        super(name, describe, age, livingZone, climateZone, foodType, wings);
    }
}
