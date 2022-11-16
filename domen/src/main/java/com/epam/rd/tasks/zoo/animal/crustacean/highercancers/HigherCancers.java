package com.epam.rd.tasks.zoo.animal.crustacean.highercancers;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Set;

public abstract class HigherCancers extends Crustacean {
    public HigherCancers(){
        super();
    }
    public HigherCancers(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String seashell) {
        super(name, describe, age, livingZone, climateZone, foodType, seashell);
    }

    public HigherCancers(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String seashell, boolean isDeleted) {
        super(name, describe, age, livingZone, climateZone, foodType, isDeleted, seashell);
    }
}
