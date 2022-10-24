package zoo.repository.animal;

import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf;
import com.epam.rd.tasks.zoo.repository.animal.AnimalGeneralMapper;
import com.epam.rd.tasks.zoo.repository.animal.AnimalGeneralRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animal.AnimalIdTypeAndHouseId;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;
import com.epam.rd.tasks.zoo.repository.animal.mammal.predator.wolf.WolfMapper;
import com.epam.rd.tasks.zoo.repository.animal.mammal.predator.wolf.WolfRepository;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;

public class AnimalGeneralRepositoryTest {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSetMock;

    private AnimalGeneralRepositoryImpl animalGeneralRepository;
    private AnimalGeneralMapper animalGeneralMapper;

    public AnimalGeneralRepositoryTest() throws SQLException, ClassNotFoundException {
        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
        animalGeneralMapper = Mockito.mock(AnimalGeneralMapper.class);
        animalGeneralRepository = new AnimalGeneralRepositoryImpl(connection,animalGeneralMapper);
    }

    @Test
    public void getAllIdAndTypeOfAnimalTest() throws SQLException {

        Map<Long,String> map = new HashMap<>();
        map.put(1L,"Jaba");
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("select animal.id, animaltype.animaltype from animal " +
                        "INNER JOIN animaltype ON animaltype.id = animal.id_animaltype"))
                .thenReturn(resultSetMock);

        Mockito.when(animalGeneralMapper.idAndTypeFromRawToMap(resultSetMock)).thenReturn(map);

        animalGeneralRepository.getAllIdAndTypeOfAnimal();

        Mockito.verify(statement, Mockito.times(1)).executeQuery("select animal.id, animaltype.animaltype from animal " +
                "INNER JOIN animaltype ON animaltype.id = animal.id_animaltype");

        Assert.assertEquals(animalGeneralRepository.getAllIdAndTypeOfAnimal(), map);
    }

    @Test
    public void getAllIdAndTypeOfAnimalByNameTest() throws SQLException {
        String name = "Test";
        Map<Long,String> map = new HashMap<>();
        map.put(1L,"Jaba");
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("select animal.id, animaltype.animaltype from animal " +
                        "INNER JOIN animaltype ON animaltype.id = animal.id_animaltype WHERE animal.name LIKE '%" + name + "%'"))
                .thenReturn(resultSetMock);

        Mockito.when(animalGeneralMapper.idAndTypeFromRawToMap(resultSetMock)).thenReturn(map);

        animalGeneralRepository.getAllIdAndTypeOfAnimalByName(name);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("select animal.id, animaltype.animaltype from animal " +
                "INNER JOIN animaltype ON animaltype.id = animal.id_animaltype WHERE animal.name LIKE '%" + name + "%'");

        Assert.assertEquals(animalGeneralRepository.getAllIdAndTypeOfAnimalByName(name), map);
    }

    @Test
    public void getAllIdAndTypeOfAnimalByDateCreationTest() throws SQLException {

        java.sql.Date date = Date.valueOf("2012-12-20");
        Map<Long,String> map = new HashMap<>();
        map.put(1L,"Jaba");
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("select animal.id, animaltype.animaltype from animal " +
                        "INNER JOIN animaltype ON animaltype.id = animal.id_animaltype WHERE animal.createDate = '"+ date + "'"))
                .thenReturn(resultSetMock);

        Mockito.when(animalGeneralMapper.idAndTypeFromRawToMap(resultSetMock)).thenReturn(map);

        animalGeneralRepository.getAllIdAndTypeOfAnimalByDateCreation(date);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("select animal.id, animaltype.animaltype from animal " +
                "INNER JOIN animaltype ON animaltype.id = animal.id_animaltype WHERE animal.createDate = '" + date + "'");

        Assert.assertEquals(animalGeneralRepository.getAllIdAndTypeOfAnimalByDateCreation(date), map);
    }

    @Test
    public void getAllTypesOfAnimalTest() throws SQLException {
        Set<String> list = new HashSet<>();
        list.add("type1"); list.add("type2");
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("select DISTINCT animalType from animaltype"))
                .thenReturn(resultSetMock);

        Mockito.when(animalGeneralMapper.typeFromRawToSet(resultSetMock)).thenReturn(list);

        animalGeneralRepository.getAllTypesOfAnimal();

        Mockito.verify(statement, Mockito.times(1)).executeQuery("select DISTINCT animalType from animaltype");

        Assert.assertEquals(animalGeneralRepository.getAllTypesOfAnimal(),list);

    }

    @Test
    public void getAllIdAndTypeOfAnimalAndHouseIdByAnimalIdTest() throws SQLException {
        AnimalIdTypeAndHouseId animalIdTypeAndHouseId = new AnimalIdTypeAndHouseId(1L,"Jaba",1L);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("select animal.id, animaltype.animaltype, animalhouse.id as animalHouseId from animal" +
                " INNER JOIN animaltype ON animaltype.id = animal.id_animaltype" +
                " INNER JOIN animalinhouse ON animalinhouse.animal_id = animal.id" +
                " INNER JOIN animalhouse ON animalhouse.id = animalinhouse.animalhouse_id" +
                " WHERE animal.id = " + 1L))
                .thenReturn(resultSetMock);

        Mockito.when(animalGeneralMapper.fromRawToAnimalIdTypeAndHouseId(resultSetMock)).thenReturn(animalIdTypeAndHouseId);

        animalGeneralRepository.getAllIdAndTypeOfAnimalAndHouseIdByAnimalId(1L);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("select animal.id, animaltype.animaltype, animalhouse.id as animalHouseId from animal" +
                " INNER JOIN animaltype ON animaltype.id = animal.id_animaltype" +
                " INNER JOIN animalinhouse ON animalinhouse.animal_id = animal.id" +
                " INNER JOIN animalhouse ON animalhouse.id = animalinhouse.animalhouse_id" +
                " WHERE animal.id = " + 1L);

        Assert.assertEquals(animalGeneralRepository.getAllIdAndTypeOfAnimalAndHouseIdByAnimalId(1L),animalIdTypeAndHouseId);
    }

    public void getAllAnimalInHouseByHouseId() throws SQLException {

        Map<Long,String> map = new HashMap<>();
        map.put(1L,"Jaba");

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("select animal.id, animaltype.animaltype from animal" +
                        " INNER JOIN animaltype ON animaltype.id = animal.id_animaltype" +
                        " INNER JOIN animalinhouse ON animalinhouse.animal_id = animal.id" +
                        " INNER JOIN animalhouse ON animalhouse.id = animalinhouse.animalhouse_id" +
                        " WHERE animalhouse.id = " + 1L))
                .thenReturn(resultSetMock);

        Mockito.when(animalGeneralMapper.idAndTypeFromRawToMap(resultSetMock)).thenReturn(map);

        animalGeneralRepository.getAllAnimalInHouseByHouseId(1L);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("select animal.id, animaltype.animaltype from animal" +
                " INNER JOIN animaltype ON animaltype.id = animal.id_animaltype" +
                " INNER JOIN animalinhouse ON animalinhouse.animal_id = animal.id" +
                " INNER JOIN animalhouse ON animalhouse.id = animalinhouse.animalhouse_id" +
                " WHERE animalhouse.id = " + 1L);

        Assert.assertEquals(animalGeneralRepository.getAllAnimalInHouseByHouseId(1L),map);
    }
}
