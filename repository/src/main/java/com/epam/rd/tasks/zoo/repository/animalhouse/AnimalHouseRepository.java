package com.epam.rd.tasks.zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;

import java.sql.SQLException;

public interface AnimalHouseRepository <T extends AnimalHouse, Id>{

    public void create(T animalHouse) throws SQLException, ClassNotFoundException;
    public void addAnimalToHouse(Long IdAnimal, Long IdAnimalHouse) throws SQLException, ClassNotFoundException;
    public T getById(Id id) throws SQLException, ClassNotFoundException;
    public Long getIdByAllParams(T animalHouse) throws SQLException, ClassNotFoundException;
    public void update(T animalHouse) throws SQLException, ClassNotFoundException;
    public void setDeleteStatus(Id id, boolean status) throws SQLException;
    public void hardDelete(Id id) throws SQLException;
}
