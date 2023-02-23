package com.epam.rd.tasks.zoo.animal.bird.finche;

import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Objects;
import java.util.Set;

public class Bullfinch extends Finche {
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


    @Override
    public String toString() {
        return "Bullfinch{" + super.toString() +
                "color='" + color + '\'' +
                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bullfinch bullfinch = (Bullfinch) o;
        return Objects.equals(color, bullfinch.color);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
