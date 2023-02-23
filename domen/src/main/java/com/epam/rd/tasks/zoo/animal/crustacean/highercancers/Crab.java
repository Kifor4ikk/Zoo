package com.epam.rd.tasks.zoo.animal.crustacean.highercancers;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Objects;
import java.util.Set;

public class Crab extends HigherCancers {

    private String taste;

    public Crab(){
        super();
        this.taste = "UNTITLED";
        this.setId(0L);
    }


    public Crab(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String seashell, boolean isDeleted) {
        super(name, describe, age, livingZone, climateZone, foodType, seashell, isDeleted);
    }

    public Crab(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String seashell, boolean isDeleted, String taste) {
        super(name, describe, age, livingZone, climateZone, foodType, seashell, isDeleted);
        this.taste = taste;
    }


    public Crab(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String seashell, String taste) {
        super(name, describe, age, livingZone, climateZone, foodType, seashell);
        this.taste = taste;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    @Override
    public String toString() {
        return "Crab{" +
                super.toString() +
                " taste='" + taste + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Crab crab = (Crab) o;
        return Objects.equals(taste, crab.taste);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taste);
    }
}
