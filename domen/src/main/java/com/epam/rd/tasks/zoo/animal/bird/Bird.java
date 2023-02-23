package com.epam.rd.tasks.zoo.animal.bird;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Objects;
import java.util.Set;

public abstract class Bird extends Animal {

    private String wings;

    public Bird(){
        super();
    }

    public Bird(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String wings) {
        super(name, describe, age, livingZone, climateZone, foodType);
        this.wings = wings;
    }

    public String getWings() {
        return wings;
    }

    public void setWings(String wings) {
        this.wings = wings;
    }

    @Override
    public String toString() {
        return "Bird{" + super.toString() +
                "wings='" + wings + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bird bird = (Bird) o;
        return Objects.equals(wings, bird.wings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wings);
    }
}
