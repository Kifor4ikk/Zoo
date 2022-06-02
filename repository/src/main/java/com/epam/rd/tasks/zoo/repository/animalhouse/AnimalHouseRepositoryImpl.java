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
    public void create(AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        AnimalHouseDto dto = AnimalHouseDto.toDto(animalHouse);
        try {
            if (!animalHouse.getAnimals().isEmpty())
                throw new AnimalHouseException("Can't create new house with living animals inside! New house should be empty!");
            getIdByAllParams(animalHouse);
            throw new AlreadyExistException("House with current params already exist");
        } catch (NotFoundException e) {
            state().executeQuery("INSERT INTO AnimalHouse (name,area,animaltype,climatezone,typeofhouse,isdeleted) VALUES ('"
                    + dto.getName() + "', '"
                    + dto.getArea() + "', '"
                    + dto.getTypeOfAnimal() + "', '"
                    + dto.getClimateZone() + "', '"
                    + dto.getTypeOfHouse() + "', '"
                    + dto.isDeleted() + "')");
        }
    }

    @Override
    public Long getIdByAllParams(AnimalHouse animalHouse) throws SQLException {
        AnimalHouseDto dto = AnimalHouseDto.toDto(animalHouse);
        try (ResultSet resultSet = state().executeQuery("SELECT * FROM AnimalHouse WHERE isDeleted = false"
                + " AND name = '" + dto.getName()
                + "' AND area = '" + dto.getArea()
                + "' AND animaltype = '" + dto.getTypeOfAnimal()
                + "' AND climatezone = '" + dto.getClimateZone()
                + "' AND typeofhouse = '" + dto.getTypeOfHouse() + "'")
        ) {
            if (resultSet.next()) {
                dto.setId(resultSet.getLong("id"));

                if (resultSet.getLong("id") == 0)
                    throw new NotFoundException("Animal House was not found");
            } else throw new NotFoundException("Animal house was not found");
        }
        return dto.getId();
    }

    @Override
    public void addAnimalToHouse(Long IdAnimal, Long IdAnimalHouse) throws SQLException, ClassNotFoundException {

        Class animalType;
        String climateZone;
        try (ResultSet resultSet = state().executeQuery("SELECT * FROM Animal WHERE isDeleted = false AND id = " + IdAnimal)
        ) {
            if (resultSet.next()) {
                if (resultSet.getLong("id") == 0)
                    throw new NotFoundException("Animal with id" + IdAnimal + " was not found");

                animalType = Class.forName(resultSet.getString("typeofanimal"));
                climateZone = resultSet.getString("climatezone");

            } else throw new NotFoundException("Animal with id" + IdAnimal + " was not found");
        }
        AnimalHouse animalHouse = getById(IdAnimalHouse);
        if (Arrays.stream(climateZone.substring(1, climateZone.length() - 1).split(", "))
                .map(ClimateZone::valueOf).collect(Collectors.toList()).contains(animalHouse.getClimateZone())
                && animalHouse.getTypeOfAnimal().contains(animalType)
        )
            state().execute("INSERT INTO AnimalInHouse (AnimalHouse_id,Animal_Id) VALUES (" + IdAnimalHouse + ", " + IdAnimal + ")");
        else
            throw new BadAnimalTypeException("Animal with current params cant live in this house!");

    }


    @Override
    public AnimalHouse getById(Long id) throws SQLException, ClassNotFoundException {

        AnimalHouseDto dto = new AnimalHouseDto();
        dto.setId(id);
        try (ResultSet resultSet = state().executeQuery("SELECT * FROM AnimalHouse WHERE isDeleted = false AND id = " + id)
        ) {
            if (resultSet.next()) {
                if (resultSet.getLong("id") == 0)
                    throw new NotFoundException("Animal House was not found");

                dto.setName(resultSet.getString("name"));
                dto.setArea(resultSet.getInt("area"));
                dto.setTypeOfAnimal(resultSet.getString("animaltype"));
                dto.setClimateZone(resultSet.getString("climatezone"));
                dto.setTypeOfHouse(resultSet.getString("typeofhouse"));

            } else throw new NotFoundException("Animal house was not found");
        }
        List<Animal> animals = new ArrayList<>();
        try (ResultSet resultSet = state().executeQuery("SELECT * FROM animalInHouse UP INNER JOIN animal ON animal_id = id WHERE animalHouse_Id = " + dto.getId())) {
            AnimalDto animalDto = new AnimalDto();
            while (resultSet.next()) {
                if (resultSet.getLong("id") == 0)
                    throw new NotFoundException("Animal was not found");
                animalDto.setId(resultSet.getLong("id"));
                animalDto.setName(resultSet.getString("name"));
                animalDto.setDescribe(resultSet.getString("describe"));
                animalDto.setAge(resultSet.getInt("age"));
                animalDto.setLivingZone(resultSet.getString("zoneType"));
                animalDto.setClimateZone(List.of(resultSet.getString("climateZone")
                        .substring(1, resultSet.getString("climateZone").length() - 1).split(", ")));
                animalDto.setFoodType(resultSet.getString("foodType"));
                animalDto.setDeleted(resultSet.getBoolean("isDeleted"));
                animalDto.setTypeOfAnimal(resultSet.getString("typeofanimal"));
                animals.add(AnimalDto.fromDto(animalDto));
            }
        }
        dto.setAnimals(animals);
        return AnimalHouseDto.fromDto(dto);
    }

    @Override
    public void update(AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        AnimalHouseDto newDto = AnimalHouseDto.toDto(getById(animalHouse.getId()));
        AnimalHouseDto data = AnimalHouseDto.toDto(animalHouse);

        if(data.getName() != null) newDto.setName(data.getName());
        if(data.getArea() != null) newDto.setArea(data.getArea());
        if(data.getTypeOfHouse() != null && !newDto.getAnimals().isEmpty()) throw new BadZoneException("You cant change type of house while animals living in!");
        if(data.getClimateZone() != null && !newDto.getAnimals().isEmpty()) throw new BadClimateException("You cant change climate of house while animals living in!");
        if(data.getTypeOfAnimal() != null && !newDto.getAnimals().isEmpty()) throw new BadAnimalTypeException("You cant change animal type while animals living in!");

        state().executeQuery("UPDATE animalHouse SET" +
                        " name = '" + newDto.getName() +
                        "', area = '" + newDto.getArea() +
                        "', typeOfHouse = '" + newDto.getTypeOfHouse() +
                        "', climateZone = '" + newDto.getClimateZone() +
                        "', typeOfAnimal = '" + newDto.getTypeOfAnimal()+ "'"
                );
    }

    @Override
    public void setDeleteStatus(Long id, boolean status) throws SQLException {
        state().executeQuery("UPDATE animalHouse SET isDeleted = "+ status +" WHERE id = " + id);
    }

    @Override
    public void hardDelete(Long id) throws SQLException {
        state().execute("DELETE FROM animalHouse WHERE id = " + id);
    }
}
