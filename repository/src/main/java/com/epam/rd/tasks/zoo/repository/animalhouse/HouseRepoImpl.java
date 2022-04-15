package com.epam.rd.tasks.zoo.repository.animalhouse;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class HouseRepoImpl {
    private final Connection connection;

    public HouseRepoImpl(Connection connection) {
        this.connection = connection;
    }

    public Statement state() throws SQLException {
        return connection.createStatement();
    }

}
