package com.epam.rd.tasks.zoo.animal.mammal.predator;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Objects;
import java.util.Set;

public class Wolf extends Predator {

    private String behaviour;

    public Wolf() {
        super();
    }

    public Wolf(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String tail, String behaviour) {
        super(name, describe, age, livingZone, climateZone, foodType, tail);
        this.behaviour = behaviour;
    }

    public Wolf(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String tail) {
        super(name, describe, age, livingZone, climateZone, foodType, tail);
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    @Override
    public String toString() {
        return "Wolf{" + super.toString() +
                " behaviour='" + behaviour + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Wolf wolf = (Wolf) o;
        return Objects.equals(behaviour, wolf.behaviour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), behaviour);
    }
}
