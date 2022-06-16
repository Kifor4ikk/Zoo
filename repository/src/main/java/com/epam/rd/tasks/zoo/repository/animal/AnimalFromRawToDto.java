package com.epam.rd.tasks.zoo.repository.animal;

import com.epam.rd.tasks.zoo.dto.animal.AnimalDto;

import java.sql.ResultSet;

public class AnimalFromRawToDto {

    public static AnimalDto generalInfoFromRaw(AnimalDto animalDto, ResultSet rs){

        return animalDto;
    }
}
