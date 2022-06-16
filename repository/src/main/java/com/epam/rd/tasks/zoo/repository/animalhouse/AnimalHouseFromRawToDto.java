package com.epam.rd.tasks.zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.dto.animal.AnimalDto;
import com.epam.rd.tasks.zoo.dto.animalhouse.AnimalHouseDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalHouseFromRawToDto {

    public static AnimalHouseDto fromRawToDtoGeneralInfo(AnimalHouseDto animalHouseDto, ResultSet rs) throws SQLException, ClassNotFoundException {

        animalHouseDto.setId(rs.getLong("id"));
        animalHouseDto.setName(rs.getString("name"));
        animalHouseDto.setArea(rs.getInt("area"));
        animalHouseDto.setTypeOfHouse(rs.getString("zoneType"));
        animalHouseDto.setClimateZone(rs.getString("climateType"));
        animalHouseDto.setDeleted(rs.getBoolean("isDeleted"));
        return animalHouseDto;
    }

    public static AnimalHouseDto addAnimalTypesFromRaw(AnimalHouseDto animalHouseDto, ResultSet rs) throws SQLException, ClassNotFoundException{
        animalHouseDto.getTypeOfAnimals().add((Class<? extends Animal>) Class.forName(rs.getString("animalType")));
        return animalHouseDto;
    }

    public static AnimalHouseDto addAnimalLivingFromRaw(AnimalHouseDto animalHouseDto, ResultSet rs) throws SQLException, ClassNotFoundException{
    //@TODO add adding :D
        //        AnimalDto animalDto = new AnimalDto();
//        animalDto.setId(rs.getLong("id"));
//        animalDto.setName(rs.getString("name"));
//        animalDto.setDescribe(rs.getString("describe"));
//        animalDto.setAge(rs.getInt("age"));
//        animalDto.setLivingZone(rs.getString("zoneType"));
//        animalDto.setClimateZone(List.of(rs.getString("climateZone")
//                .substring(1, rs.getString("climateZone").length() - 1).split(", ")));
//        animalDto.setFoodType(rs.getString("foodType"));
//        animalDto.setDeleted(rs.getBoolean("isDeleted"));
//        animalDto.setTypeOfAnimal(rs.getString("typeofanimal"));

        return animalHouseDto;
    }


}
