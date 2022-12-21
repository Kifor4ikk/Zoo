package com.epam.rd.tasks.zoo.repository.database;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {


    public DataSource createDataSource(String jdbcUrl, String username, String password){
       return new DriverManagerDataSource(jdbcUrl,username,password);
    }

    public Connection connectWithDataBase(DataSource dataSource) throws SQLException, ClassNotFoundException {
        return dataSource.getConnection();
    }
}
