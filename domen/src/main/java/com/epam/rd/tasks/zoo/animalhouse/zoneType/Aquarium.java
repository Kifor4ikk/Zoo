package com.epam.rd.tasks.zoo.animalhouse.zoneType;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;

import java.util.List;

public class Aquarium extends AnimalHouse {
    public Aquarium(Long id, String name, Integer area, List<Class<? extends Animal>> typeOfAnimal, ClimateZone climateZone) {
        super(id, name, area, typeOfAnimal, climateZone);
    }

    public Aquarium() {
        super();
    }
}
