package com.epam.rd.tasks.zoo.repository.animal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AnimalRepoImpl {
    private final Connection connection;

    public AnimalRepoImpl(Connection connection) {
        this.connection = connection;
    }

    public Statement state() throws SQLException {
        return connection.createStatement();
    }
}
