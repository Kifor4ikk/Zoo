package com.epam.rd.tasks.zoo.dto.animal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Food;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CrabDto {


    public static Crab fromDTO(AnimalDto animalDto) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Crab crab = new  Crab(
                animalDto.getName(),
                animalDto.getDescribe(),
                animalDto.getAge(),
                (Class<? extends AnimalHouse>) Class.forName(animalDto.getLivingZone()),
                animalDto.getClimateZone().stream().map(ClimateZone::valueOf).collect(Collectors.toList()),
                (Class<? extends Food>)Class.forName(animalDto.getFoodType()),
                animalDto.isDeleted());
        crab.setId(animalDto.getId());
        return crab;
    }
}
