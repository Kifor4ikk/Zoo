package com.epam.rd.tasks.zoo.repository.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class Database {

    private static final String jdbcUrl = "jdbc:postgresql://localhost:1994/zoo";
    private static final String username = "postgres";
    private static final String password = "1";

    @Bean
    @Qualifier("connection")
    public static Connection connectWithDataBase() throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
        System.out.println("Connected!");
        return connection;
    }
}
