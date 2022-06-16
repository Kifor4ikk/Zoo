package com.epam.rd.tasks.zoo.repository.animal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.dto.animal.AnimalCreateDto;
import com.epam.rd.tasks.zoo.dto.animal.AnimalDto;
import com.epam.rd.tasks.zoo.repository.animalhouse.AnimalHouseRepository;
import com.epam.rd.tasks.zoo.repository.database.RepositoryConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AnimalRepositoryImpl extends RepositoryConnection implements AnimalRepository<Animal, Long> {

    public AnimalRepositoryImpl(Connection connection) {
        super(connection);
    }


    @Override
    public void create(AnimalCreateDto animal, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        try(ResultSet resultSet = state().executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,isdeleted) VALUES (' " +
                animal.getName() + "','" +
                animal.getDescribe() + "'," +
                animal.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                animal.getClass() + "')," + animal.isDeleted() + ") RETURNING animal.id;")
        ){

        }
    }

    @Override
    public Animal getByAllParamsFromAnimal(Animal animal) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Animal getById(Long aLong) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void update(Animal animalHouse) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void setDeleteStatus(Long aLong, boolean status) throws SQLException {

    }

    @Override
    public void hardDelete(Long aLong) throws SQLException {

    }
}
