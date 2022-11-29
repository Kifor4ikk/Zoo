package zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.repository.animalhouse.AnimalHouseMapper;
import com.epam.rd.tasks.zoo.repository.animalhouse.AnimalHouseRepositoryImpl;
import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@Test
public class AnimalHouseRepositoryTest {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSetMock;
    private AnimalHouseRepositoryImpl animalHouseRepository;
    private Field animalHouse;

    @BeforeTest
    public void beforeTest() throws SQLException, ClassNotFoundException {
        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
        animalHouseRepository = new AnimalHouseRepositoryImpl(connection, new AnimalHouseMapper());

        animalHouse = new Field(2L, "Fields222222",1945, List.of(Crab.class, Bullfinch.class), ClimateZone.SUBANTARCTIC);
    }

    @Test
    public void connectionTest(){
        Assert.assertNotNull(animalHouseRepository);
    }

    @Test
    public void createTest() throws SQLException {

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        Mockito.when(resultSetMock.getString("id")).thenReturn("1");
        Mockito.when(resultSetMock.getLong("id")).thenReturn(1L);
        Mockito.when(resultSetMock.next()).thenReturn(true);

        Mockito.when(statement.executeQuery("INSERT INTO animalHouse (name,area,id_zonetype,id_climatetype,isDeleted) VALUES ('"
                + animalHouse.getName() + "', '"
                + animalHouse.getArea() + "', (SELECT zt.Id FROM zoneType zt WHERE zt.zonetype = '"
                + animalHouse.getClass().getName() + "'), (SELECT ct.Id FROM climateType ct WHERE ct.climateType = '"
                + animalHouse.getClimateZone().name() + "'), '"
                + Date.valueOf(LocalDate.now())
                + animalHouse.isDeleted() + "') RETURNING id;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("SELECT animalType.id FROM animalType WHERE animalType = '"
                + Crab.class.getName() + "'")).thenReturn(resultSetMock);
        Mockito.when(statement.executeQuery("SELECT animalType.id FROM animalType WHERE animalType = '" + Bullfinch.class.getName() + "'"))
                        .thenReturn(resultSetMock);
        Mockito.when(statement.executeQuery("INSERT INTO animalTypeInHouse (ID_HOUSE,ID_ANIMALTYPE) VALUES ("
                        + animalHouse.getId() + ", " + resultSetMock.getLong("id") + ")")).thenReturn(resultSetMock);

        animalHouseRepository.create(animalHouse);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO animalHouse (name,area,id_zonetype,id_climatetype,isDeleted) VALUES ('"
                + animalHouse.getName() + "', '"
                + animalHouse.getArea() + "', (SELECT zt.Id FROM zoneType zt WHERE zt.zonetype = '"
                + animalHouse.getClass().getName() + "'), (SELECT ct.Id FROM climateType ct WHERE ct.climateType = '"
                + animalHouse.getClimateZone().name() + "'), '"
                + animalHouse.isDeleted() + "') RETURNING id;");
        Mockito.verify(statement, Mockito.times(1))
                .executeQuery("SELECT animalType.id FROM animalType WHERE animalType = '" + Crab.class.getName() + "'");
        Mockito.verify(statement, Mockito.times(1))
                .executeQuery("SELECT animalType.id FROM animalType WHERE animalType = '" + Bullfinch.class.getName() + "'");
        Mockito.verify(statement, Mockito.times(2))
                .execute("INSERT INTO animalTypeInHouse (ID_HOUSE,ID_ANIMALTYPE) VALUES ("
                        + animalHouse.getId() + ", " + resultSetMock.getLong("id") + ")");
        
    }

    @Test
    public void getTest() throws SQLException {

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        Mockito.when(resultSetMock.getString("id")).thenReturn("1");
        Mockito.when(resultSetMock.getLong("id")).thenReturn(1L);
        Mockito.when(resultSetMock.next()).thenReturn(true);

    }
}
