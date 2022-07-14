package com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancers.crab;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class CrabRepository extends CrustaceanRepositoryImpl {

    public CrabRepository(Connection connection) {
        super(connection);
    }

    public void create(Crustacean crustacean, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        super.create(crustacean,animalHouse, Crab.class);
    }
}
