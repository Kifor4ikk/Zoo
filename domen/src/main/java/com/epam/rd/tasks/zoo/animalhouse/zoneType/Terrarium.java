package com.epam.rd.tasks.zoo.animalhouse.zoneType;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animal.Animal;

import java.util.List;

public class Terrarium extends AnimalHouse {
    public Terrarium(Long id, String name, Integer area, List<Class<? extends Animal>> typeOfAnimal, ClimateZone climateZone) {
        super(id, name, area, typeOfAnimal, climateZone);
    }

    public Terrarium() {
        super();
    }
}
