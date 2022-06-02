package com.epam.rd.tasks.zoo.animals.crustacean.highercancers;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;

public class Crab extends HigherCancers {

    public Crab(){
        super();
    }
    public Crab(String name, String describe, int age, Class<? extends AnimalHouse> livingZone, List<ClimateZone> climateZone, Class<? extends Food> foodType, String seashell) {
        super(name, describe, age, livingZone, climateZone, foodType, seashell);
    }

    public Crab(String name, String describe, int age, Class<? extends AnimalHouse> livingZone, List<ClimateZone> climateZone, Class<? extends Food> foodType, String seashell, boolean isDeleted) {
        super(name, describe, age, livingZone, climateZone, foodType, seashell, isDeleted);
    }
}
