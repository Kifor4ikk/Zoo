package com.epam.rd.tasks.zoo.dto.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalHouseDto {

    private Long id;
    private String name;
    private Integer area;
    private List<String> typeOfAnimal;
    //@TODO добавить сувязь
    //private List<Animal> animals = new ArrayList<>();
    private String climateZone;
    private boolean isDeleted = false;

    public static AnimalHouseDto toDto(AnimalHouse animalHouse){
        return AnimalHouseDto.builder()
                .id(animalHouse.getId())
                .name(animalHouse.getName())
                .area(animalHouse.getArea())
                .typeOfAnimal(animalHouse.getTypeOfAnimal().stream().map(Class::getName).collect(Collectors.toList()))
                .climateZone(animalHouse.getClimateZone().name())
                .build();
    }

}
