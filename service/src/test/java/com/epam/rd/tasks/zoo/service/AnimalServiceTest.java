package com.epam.rd.tasks.zoo.service;

import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animal.mammal.rodent.squirrel.SquirrelRepository;
import com.epam.rd.tasks.zoo.repository.database.Database;
import com.epam.rd.tasks.zoo.service.animal.AnimalService;
import com.epam.rd.tasks.zoo.service.animal.AnimalServiceMapper;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.ArgumentMatchers.anyString;

public class AnimalServiceTest {

    @Test
    public void AnimalServiceMapperTest() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        AnimalServiceMapper animalServiceMapper = new AnimalServiceMapper(connection);

        try {
            animalServiceMapper.chooseRepository("csm.epam.rd.tasks.zoo.animals.mammal.rodent.Squirrel");
            throw new ClassCastException("Method before should throw exception!");
        } catch (Exception e){
            Assert.assertEquals(e.getClass(),NotFoundException.class);
        }

        Assert.assertEquals(animalServiceMapper.chooseRepository("com.epam.rd.tasks.zoo.animal.mammal.rodent.Squirrel").getClass(), SquirrelRepository.class);
    }

    @Test
    public void test() throws SQLException, ClassNotFoundException {

    }
}
