package com.epam.rd.tasks.zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.exception.*;
import com.epam.rd.tasks.zoo.repository.database.RepositoryConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalHouseRepositoryImpl extends RepositoryConnection implements AnimalHouseRepository<AnimalHouse, Long> {

    private AnimalHouseMapper animalHouseMapper;
    public AnimalHouseRepositoryImpl(Connection connection, AnimalHouseMapper animalHouseMapper) {
        super(connection);
        this.animalHouseMapper = animalHouseMapper;
    }

    @Override
    public Boolean create(AnimalHouse animalHouse) throws SQLException {

        if (!animalHouse.getAnimals().isEmpty())
            throw new AnimalHouseException("Can't create new house with living animals inside! New house should be empty!");
        ResultSet idGetter = state().executeQuery("INSERT INTO animalHouse (name,area,id_zonetype,id_climatetype,isDeleted) VALUES ('"
                + animalHouse.getName() + "', '"
                + animalHouse.getArea() + "', (SELECT zt.Id FROM zoneType zt WHERE zt.zonetype = '"
                + animalHouse.getClass().getName() + "'), (SELECT ct.Id FROM climateType ct WHERE ct.climateType = '"
                + animalHouse.getClimateZone().name() + "'), '"
                + animalHouse.isDeleted() + "') RETURNING id;");
        if(idGetter.next()) animalHouse.setId(idGetter.getLong("id"));
        //Add animals
        for (Class<? extends Animal> animalType : animalHouse.getTypeOfAnimal()) {
            try (ResultSet rs = state().executeQuery("SELECT animalType.id FROM animalType WHERE animalType = '" + animalType.getName() + "'")) {
                if(rs.next()) state().execute("INSERT INTO animalTypeInHouse (ID_HOUSE,ID_ANIMALTYPE) " +
                        "VALUES (" + animalHouse.getId() +", " + rs.getLong("id") + ")");
            }
        }
        return true;
    }

    @Override
    public Boolean addAnimalToHouse(Animal animal, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        if(!animal.getLivingZone().contains(animalHouse.getClass()))
            throw new BadZoneException("Bad livingZone for animal " + animal.getName() + "[id=" + animal.getId() + "] in house " +
                    animalHouse.getName() + " [id=" + animalHouse.getId() + "]");
        if(!animal.getClimateZone().contains(animalHouse.getClimateZone()))
            throw new BadClimateException("Bad climate for animal " + animal.getName() + "[id=" + animal.getId() + "] in house " +
                    animalHouse.getName() + " [id=" + animalHouse.getId() + "]");
        if(!animalHouse.getTypeOfAnimal().contains(animal.getClass()))
            throw new BadAnimalTypeException("Bad climate for animal " + animal.getName() + "[id=" + animal.getId() + "] in house " +
                    animalHouse.getName() + " [id=" + animalHouse.getId() + "]");

        ResultSet insertResult = state().executeQuery("INSERT INTO animalInHouse (animal_id,animalhouse_id)" +
                " VALUES (" + animal.getId() + ", " + animalHouse.getId() + " ) RETURNING TRUE");
        return insertResult.getBoolean("bool");
    }

    @Override
    public AnimalHouse findById(Long id) throws SQLException {
        //Here we should to add animalTypes which can live in this house
        // add ANIMALS which ALREADY LIVING HERE
        // and general info
        AnimalHouse animalHouse = null;
        //Collect main info
        try(ResultSet mainParamsResultSet = state().executeQuery("select " +
                "animalHouse.id, animalHouse.name, animalHouse.area,animalHouse.isDeleted," +
                "zoneType.zoneType, climateType.climateType " +
                "from animalHouse INNER JOIN zoneType ON zoneType.id = " +
                "animalHouse.id_zoneType INNER JOIN climateType ON climateType.id = animalHouse.id_climateType WHERE animalHouse.id = " + id)){
            if(mainParamsResultSet.next()) animalHouse = animalHouseMapper.fromRowGeneralInfo(mainParamsResultSet);
            else throw new NotFoundException("With current id was not found");
        //Collect info about animal which can live here
        try(ResultSet animalTypeResultSet = state().executeQuery("SELECT animalType.animaltype FROM animalTypeInHouse INNER" +
                " JOIN animaltype ON animaltype.id = animalTypeInHouse.id_animaltype WHERE animalTypeInHouse.ID_HOUSE = " + id)) {
            while(animalTypeResultSet.next()) animalHouseMapper.addAnimalTypesFromRaw(animalHouse,animalTypeResultSet);
        }

        try (ResultSet animalLivingHereResultSet = state().executeQuery("select animal.id, animal.name," +
                " animal.describe, animal.age, animaltype.animaltype, animal.isDeleted\n" +
                "from animalinhouse\n" +
                "INNER JOIN animal ON animal.id = animalinhouse.animal_id\n" +
                "INNER JOIN animalType ON animalType.id = animal.id_animaltype WHERE animalinhouse.animalhouse_id = " + id)){
            while (animalLivingHereResultSet.next()) {
                animalHouseMapper.addAnimalLivingFromRaw(animalHouse, animalLivingHereResultSet);
            }
        }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return animalHouse;
    }

    @Override
    public void update(AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {

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
