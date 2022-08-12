package com.epam.rd.tasks.zoo.animals.crustacean;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;
import java.util.Set;

public abstract class Crustacean extends Animal {

    public Crustacean(){ super(); }

    private String seashell;

    public Crustacean(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String seashell) {
        super(name, describe, age, livingZone, climateZone, foodType);
        this.seashell = seashell;
    }

    public Crustacean(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, boolean isDeleted, String seashell) {
        super(name, describe, age, livingZone, climateZone, foodType, isDeleted);
        this.seashell = seashell;
    }

    public String getSeashell() {
        return seashell;
    }

    public void setSeashell(String seashell) {
        this.seashell = seashell;
    }

    @Override
    public String toString() {
        return "Crustacean{" + super.toString() +
                " seashell='" + seashell + '\'' +
                '}';
    }
}
