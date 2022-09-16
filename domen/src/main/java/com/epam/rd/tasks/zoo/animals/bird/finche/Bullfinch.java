package com.epam.rd.tasks.zoo.animals.bird.finche;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;
import lombok.Builder;

import java.util.List;
import java.util.Set;

public class Bullfinch extends Finche{

    private String color;

    public Bullfinch() {
        super();
    }

    public Bullfinch(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String wings, String color) {
        super(name, describe, age, livingZone, climateZone, foodType, wings);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
