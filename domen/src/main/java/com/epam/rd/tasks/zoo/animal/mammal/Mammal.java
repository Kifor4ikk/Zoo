package com.epam.rd.tasks.zoo.animal.mammal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Objects;
import java.util.Set;

public abstract class Mammal extends Animal {

    private String tail;
    public Mammal(){
        super();
    }
    public Mammal(String tail) {
        super();
        this.tail = tail;
    }

    public Mammal(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String tail) {
        super(name, describe, age, livingZone, climateZone, foodType);
        this.tail = tail;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "Mammal{" + super.toString() +
                " tail='" + tail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mammal mammal = (Mammal) o;
        return Objects.equals(tail, mammal.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tail);
    }
}
