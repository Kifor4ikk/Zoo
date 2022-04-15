package com.epam.rd.tasks.zoo.animals.crustacean.highercancers;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;
import lombok.Builder;

import java.util.List;

public class Crab extends HigherСancers{

    public Crab(String name, String describe, int age, Class<? extends AnimalHouse> livingZone, List<ClimateZone> climateZone, Class<? extends Food> foodType) {
        super(name, describe, age, livingZone, climateZone, foodType);
    }

    public Crab(String name, String describe, int age, Class<? extends AnimalHouse> livingZone, List<ClimateZone> climateZone, Class<? extends Food> foodType, boolean isDeleted) {
        super(name, describe, age, livingZone, climateZone, foodType, isDeleted);
    }
}
