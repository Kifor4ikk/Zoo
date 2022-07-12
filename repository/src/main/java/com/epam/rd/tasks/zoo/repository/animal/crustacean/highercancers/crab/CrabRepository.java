package com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancers.crab;

import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanRepositoryImpl;

import java.sql.Connection;

public class CrabRepository extends CrustaceanRepositoryImpl {

    public CrabRepository(Connection connection) {
        super(connection);
    }

}
