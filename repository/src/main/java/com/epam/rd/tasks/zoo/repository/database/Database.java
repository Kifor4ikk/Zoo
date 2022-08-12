package com.epam.rd.tasks.zoo.repository.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String jdbcUrl = "jdbc:postgresql://localhost:1994/zoo";
    private static final String username = "postgres";
    private static final String password = "1";

    public static Connection connectWithDataBase() throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
        System.out.println("Connected!");
        return connection;
    }
}
