package com.epam.rd.tasks.zoo.repository.database;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
        Connection connection = dataSource.getConnection();
        System.out.println("Get connection - successful");
        return connection;
    }

    public SqlSession getSession(SqlSessionFactory sqlSessionFactory){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("Open SqlSession - successful");
        return sqlSession;
    }
}