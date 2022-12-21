package com.epam.rd.tasks.zoo.repository.animals;

import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface AnimalRepository<T extends Animal, Id> {

    public Long create(@Param("animal") T animal, @Param("animalHouse") AnimalHouse animalHouse) throws SQLException, ClassNotFoundException;

    public T findById(@Param("id") Id id) throws SQLException, ClassNotFoundException;

    public void update(@Param("animal")T animal) throws SQLException, ClassNotFoundException;

    public void setDeleteStatus(@Param("id") Id id,@Param("status") boolean status) throws SQLException;

    public void hardDelete(@Param("id") Id id) throws SQLException;

}
