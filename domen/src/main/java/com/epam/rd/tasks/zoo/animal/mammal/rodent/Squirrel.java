package com.epam.rd.tasks.zoo.animal.mammal.rodent;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.Objects;
import java.util.Set;

public class Squirrel extends Rodent{

    private String fur;

    public Squirrel(){
        super();
    }
    public Squirrel(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String tail) {
        super(name, describe, age, livingZone, climateZone, foodType, tail);
    }

    public Squirrel(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, String tail, String fur) {
        super(name, describe, age, livingZone, climateZone, foodType, tail);
        this.fur = fur;
    }

    public String getFur() {
        return fur;
    }

    public void setFur(String fur) {
        this.fur = fur;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Squirrel squirrel = (Squirrel) o;
        return Objects.equals(fur, squirrel.fur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fur);
    }
}
