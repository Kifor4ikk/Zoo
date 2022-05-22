package com.epam.rd.tasks.zoo.repository.animal;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AnimalRepository<T extends Animal, Id> {

    public ResultSet create(T animal, AnimalHouse animalHouse) throws SQLException;
    public T getById(Id id) throws SQLException, ClassNotFoundException;
    public T getByAllParams(T animal) throws SQLException, ClassNotFoundException;
    public void update(T animal) throws SQLException, ClassNotFoundException;
    public void setDeleteStatus(Id id, boolean status) throws SQLException;
    public void hardDelete(Id id) throws SQLException;
}
