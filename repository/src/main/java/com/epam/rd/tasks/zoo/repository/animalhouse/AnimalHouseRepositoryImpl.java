package com.epam.rd.tasks.zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.Zoo;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.dto.animal.AnimalDto;
import com.epam.rd.tasks.zoo.dto.animal.crustacean.CrustaceanDto;
import com.epam.rd.tasks.zoo.dto.animalhouse.AnimalHouseDto;
import com.epam.rd.tasks.zoo.exception.*;
import com.epam.rd.tasks.zoo.repository.database.RepositoryConnection;
import org.springframework.validation.annotation.Validated;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalHouseRepositoryImpl extends RepositoryConnection implements AnimalHouseRepository<AnimalHouse, Long> {

    public AnimalHouseRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Boolean create(AnimalHouse animalHouse) throws SQLException {
        AnimalHouseDto dto = AnimalHouseDto.toDto(animalHouse);
        if (!animalHouse.getAnimals().isEmpty())
            throw new AnimalHouseException("Can't create new house with living animals inside! New house should be empty!");
        ResultSet idGetter = state().executeQuery("INSERT INTO animalHouse (name,area,id_zonetype,id_climatetype,isDeleted) VALUES ('"
                + dto.getName() + "', '"
                + dto.getArea() + "', (SELECT zt.Id FROM zoneType zt WHERE zt.zonetype = '"
                + dto.getTypeOfHouse() + "'), (SELECT ct.Id FROM climateType ct WHERE ct.climateType = '"
                + dto.getClimateZone() + "'), '"
                + dto.isDeleted() + "') RETURNING id;");
        if(idGetter.next()) dto.setId(idGetter.getLong("id"));
        //Add animals
        for (Class<? extends Animal> animalType : animalHouse.getTypeOfAnimal()) {
            try (ResultSet rs = state().executeQuery("SELECT animalType.id FROM animalType WHERE animalType = '" + animalType.getName() + "'")) {
                if(rs.next()) state().execute("INSERT INTO animalTypeInHouse (ID_HOUSE,ID_ANIMALTYPE) " +
                        "VALUES (" + dto.getId() +", " + rs.getLong("id") + ")");
            }
        }
        return true;
    }
    @Override
    public Boolean addAnimalToHouse(Long IdAnimal, Long IdAnimalHouse) throws SQLException, ClassNotFoundException {
        return true;
    }

    @Override
    public AnimalHouse getById(Long id) throws SQLException {
        //Here we should to add animalTypes which can live in this house
        // add ANIMALS which ALREADY LIVING HERE
        // and general info
        AnimalHouseDto animalHouseDto = new AnimalHouseDto();
        //Collect main info
        try(ResultSet mainParamsResultSet = state().executeQuery("select \n" +
                "animalHouse.id, animalHouse.name, animalHouse.area,animalHouse.isDeleted,\n" +
                "zoneType.zoneType, climateType.climateType\n" +
                "from animalHouse INNER JOIN zoneType ON zoneType.id = " +
                "animalHouse.id_zoneType INNER JOIN climateType ON climateType.id = animalHouse.id_climateType WHERE animalHouse.id = " + id)){
            if(mainParamsResultSet.next()) AnimalHouseFromRawToDto.fromRawToDtoGeneralInfo(animalHouseDto,mainParamsResultSet);
            else throw new NotFoundException("With current id was not found");
        //Collect info about animal which can live here
        try(ResultSet animalTypeResultSet = state().executeQuery("SELECT animalType.animaltype FROM animalTypeInHouse INNER" +
                " JOIN animaltype ON animaltype.id = animalTypeInHouse.id_animaltype WHERE animalTypeInHouse.ID_HOUSE = " + id)) {
            while(animalTypeResultSet.next()) AnimalHouseFromRawToDto.addAnimalTypesFromRaw(animalHouseDto,animalTypeResultSet);
        }

        //@TODO Add adding animal based on animal repo
        try (ResultSet animalLivingHereResultSet = state().executeQuery("select animal.id, animal.name, animal.describe, animal.age, animaltype.animaltype\n" +
                "from animalinhouse\n" +
                "INNER JOIN animal ON animal.id = animalinhouse.animal_id\n" +
                "INNER JOIN animalType ON animalType.id = animal.id_animaltype WHERE animalinhouse.animalhouse_id = " + id)){
            while (animalLivingHereResultSet.next()) ;
        }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return AnimalHouseDto.fromDto(animalHouseDto);
    }

    @Override
    public void update(AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        AnimalHouseDto newDto = AnimalHouseDto.toDto(getById(animalHouse.getId()));
        AnimalHouseDto data = AnimalHouseDto.toDto(animalHouse);

        if (data.getName() != null) newDto.setName(data.getName());
        if (data.getArea() != null) newDto.setArea(data.getArea());
        if (data.getTypeOfHouse() != null && !newDto.getAnimals().isEmpty())
            throw new BadZoneException("You cant change type of house while animals living in!");
        if (data.getClimateZone() != null && !newDto.getAnimals().isEmpty())
            throw new BadClimateException("You cant change climate of house while animals living in!");
//        if (data.getTypeOfAnimal() != null && !newDto.getAnimals().isEmpty())
//            throw new BadAnimalTypeException("You cant change animal type while animals living in!");

        state().executeQuery("UPDATE animalHouse SET" +
                        " name = '" + newDto.getName() +
                        "', area = '" + newDto.getArea() +
                        "', typeOfHouse = '" + newDto.getTypeOfHouse() +
                        "', climateZone = '" + newDto.getClimateZone()
//                "', typeOfAnimal = '" + newDto.getTypeOfAnimal() + "'"
        );
    }

    @Override
    public void setDeleteStatus(Long id, boolean status) throws SQLException {
        state().executeQuery("UPDATE animalHouse SET isDeleted = " + status + " WHERE id = " + id);
    }

    @Override
    public void hardDelete(Long id) throws SQLException {
        state().execute("DELETE FROM animalHouse WHERE id = " + id);
    }
}
