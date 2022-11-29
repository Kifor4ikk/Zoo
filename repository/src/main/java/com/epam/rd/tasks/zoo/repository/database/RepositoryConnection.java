package com.epam.rd.tasks.zoo.repository.database;

import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryConnection {

    protected final Connection connection;

    public RepositoryConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement state() throws SQLException {
        return connection.createStatement();
    }
}
