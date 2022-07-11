package com.epam.rd.tasks.zoo.repository.animal.crustacean;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.exception.AlreadyExistException;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animal.AnimalRepositoryImpl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrustaceanRepositoryImpl extends AnimalRepositoryImpl {

    public CrustaceanRepositoryImpl(Connection connection) {
        super(connection);
    }

    //@TODO

    public void create(Crustacean crustacean, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
    }

    @Override
    public Animal getById(Long id) throws SQLException, ClassNotFoundException {
        try (ResultSet crab = state().executeQuery("select * from crustacean where id = " + id)){
            if(crab.next()) {
                return CrustaceanMapper.fromRawToCrustacean(super.getById(crab.getLong("animal_id")), crab);
            }
        }
        throw new NotFoundException("Data for Crustacean with id " + id + " was not found");
    }


    //Have a 3 think how to realise this method
    //now this very SAFETY method
    public void update(Crustacean data) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void setDeleteStatus(Long id, boolean status) throws SQLException {
        state().execute("UPDATE Animal SET isDeleted = '" + status + "' WHERE id = " + id);
    }

    @Override
    public void hardDelete(Long id) throws SQLException {
        state().execute("DELETE FROM Animal WHERE id = " + id);
    }
}
