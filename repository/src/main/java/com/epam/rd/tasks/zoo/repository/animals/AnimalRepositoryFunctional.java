package com.epam.rd.tasks.zoo.repository.animals;

import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface AnimalRepositoryFunctional <T extends Animal, Id> {

    public Long create(@Param("animal") T animal, @Param("animalHouse") AnimalHouse animalHouse) throws SQLException, ClassNotFoundException;

}
