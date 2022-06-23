package com.epam.rd.tasks.zoo.repository.animal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;

import java.sql.SQLException;

public interface AnimalRepository<T extends Animal, Id> {

    public void create(T animal, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException;
    public T getById(Id id) throws SQLException, ClassNotFoundException;
    public T getByAllParamsFromAnimal(T animal) throws SQLException, ClassNotFoundException;
    public void update(T animal) throws SQLException, ClassNotFoundException;
    public void setDeleteStatus(Id id, boolean status) throws SQLException;
    public void hardDelete(Id id) throws SQLException;
}
