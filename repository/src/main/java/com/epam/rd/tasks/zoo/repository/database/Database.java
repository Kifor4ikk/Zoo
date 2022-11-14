package com.epam.rd.tasks.zoo.repository.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection connectWithDataBase(String jdbcUrl, String username, String password) throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(jdbcUrl,username,password);
    }
}
