package com.epam.rd.tasks.zoo.animals.bird.finche;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;
import lombok.Builder;

import java.util.List;

public class Bullfinch extends Finche{
    public Bullfinch(String name, String describe, int age, Class<? extends AnimalHouse> livingZone, List<ClimateZone> climateZone, Class<? extends Food> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }

    public Bullfinch(String name, String describe, int age, Class<? extends AnimalHouse> livingZone, List<ClimateZone> climateZone, Class<? extends Food> foodType, boolean isDeleted) {
        super(name, describe, age, livingZone, climateZone, foodType, isDeleted);
    }
}
