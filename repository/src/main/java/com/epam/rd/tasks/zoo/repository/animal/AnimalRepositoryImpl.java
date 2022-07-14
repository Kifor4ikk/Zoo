package com.epam.rd.tasks.zoo.repository.animal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.exception.BadClimateException;
import com.epam.rd.tasks.zoo.exception.BadZoneException;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.database.RepositoryConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AnimalRepositoryImpl extends RepositoryConnection implements AnimalRepository<Animal, Long> {

    public AnimalRepositoryImpl(Connection connection) {
        super(connection);
    }


    @Override
    public Long create(Animal animal, AnimalHouse animalHouse, Class<? extends Animal> typeOfAnimal) throws SQLException, ClassNotFoundException {

        Animal infoAboutTypeObject = getInfoAboutType(typeOfAnimal);

        if(!animalHouse.getTypeOfAnimal().contains(typeOfAnimal))
            throw new BadAnimalTypeException(animal.getClass() + " cant live in " + animalHouse.getName());
        if(!infoAboutTypeObject.getClimateZone().contains(animalHouse.getClimateZone()))
            throw new BadClimateException(animal.getClass() + " cant live in " + animalHouse.getClimateZone() + " zone");
        if(!infoAboutTypeObject.getLivingZone().contains(animalHouse.getClass()))
            throw new BadZoneException(animal.getClass() + " cant live in " + animalHouse.getClass() + " zone");

        try(ResultSet animalCreateResultSet = state().executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,isdeleted) VALUES (' " +
                animal.getName() + "','" +
                animal.getDescribe() + "'," +
                animal.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                typeOfAnimal.getName() + "')," + animal.isDeleted() + ") RETURNING animal.id;")
        ){
            if(animalCreateResultSet.next()) {
                try (ResultSet animalAddToHouseResultSet = state().executeQuery("INSERT INTO animalinhouse (animalhouse_id, animal_id)" +
                        "VALUES (" + animalHouse.getId() + "," + animalCreateResultSet.getLong("id") + ");")) {
                    return animalCreateResultSet.getLong("id");
                }
            }
        }
        throw new NotFoundException("House was not found, but animal was created! You should add animal in house!");
    }

    @Override
    public Animal getById(Long id) throws SQLException, ClassNotFoundException {
        Animal animal;
        try (ResultSet animalGetResultSet = state().executeQuery(
                "select an.id, an.name, an.describe, an.age, aty.animaltype, climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted " +
                "from animal an " +
                "INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                "WHERE an.id = " + id + "AND an.isDeleted = false")){
        animal = AnimalMapper.fromRawDataToAnimal(animalGetResultSet);

        }
        return animal;
    }

    @Override
    public void update(Animal animalHouse) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void setDeleteStatus(Long id, boolean status) throws SQLException {
        state().executeQuery("UPDATE animal SET isdeleted = '"+ status +"' WHERE ID = " + id);
    }

    @Override
    public void hardDelete(Long aLong) throws SQLException {

    }

    public Animal getInfoAboutType(Class<? extends Animal> typeOfAnimal){
        try(ResultSet infoAboutType = state().executeQuery("SELECT climatetype.climatetype, zonetype.zonetype, foodtype.foodtype FROM animaltype aty " +
                "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                "WHERE aty.animaltype = '"+ typeOfAnimal.getName() +"'")){

            return AnimalMapper.getInfoFromRaw(infoAboutType, typeOfAnimal);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new BadAnimalTypeException("Something go wrong");
    }
}
