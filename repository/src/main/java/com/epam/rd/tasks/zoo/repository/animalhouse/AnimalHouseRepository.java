package com.epam.rd.tasks.zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animal.Animal;

import java.sql.SQLException;

public interface AnimalHouseRepository <T extends AnimalHouse, Id>{

    public Boolean create(T animalHouse) throws SQLException, ClassNotFoundException;
    public Boolean addAnimalToHouse(Animal animal, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException;
    public T getById(Id id) throws SQLException, ClassNotFoundException;
    public void update(T animalHouse) throws SQLException, ClassNotFoundException;
    public void setDeleteStatus(Id id, boolean status) throws SQLException;
    public void hardDelete(Id id) throws SQLException;
}
