package zoo.repository.animal.crustacean.highercancers.crab;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.food.Meat;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;
import com.epam.rd.tasks.zoo.repository.animal.AnimalRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanMapper;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancers.crab.CrabRepository;
import com.epam.rd.tasks.zoo.repository.database.Database;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;

public class CrabRepositoryTest {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSetMock;

    private Field animalHouse;
    private Crab crab;

    private CrabRepository crabRepository;

    public CrabRepositoryTest() throws SQLException, ClassNotFoundException {}

    @BeforeTest
    public void beforeTest() throws SQLException {

        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
        crabRepository = new CrabRepository(connection);

        crab = new Crab("Alex", "KingSlayerCrab", 1, Set.of(Field.class,Terrarium.class),
                Set.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Set.of(Meat.class), "Blue shell");
        animalHouse = new Field(1L, "Fields222222",1945, List.of(Crab.class, Bullfinch.class), ClimateZone.MODERATE);
    }

    @Test
    public void connectionTest(){
        Assert.assertNotNull(crabRepository);
    }

    @Test
    public void createTest() throws SQLException, ClassNotFoundException {
        Mockito.mockStatic(AnimalMapper.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        Mockito.when(resultSetMock.getString("id")).thenReturn("1");
        Mockito.when(resultSetMock.getLong("id")).thenReturn(1L);
        Mockito.when(resultSetMock.next()).thenReturn(true);

        Mockito.when(statement.execute("INSERT INTO Crustacean (Animal_Id,seashell) VALUES (" +
                1 + ",'" + crab.getSeashell() + "');")).thenReturn(true);

        Mockito.when(statement.executeQuery("INSERT INTO animalinhouse (animalhouse_id, animal_id)" +
                "VALUES (" + animalHouse.getId() + "," + resultSetMock.getLong("id") + ");")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,isdeleted) VALUES (' " +
                crab.getName() + "','" +
                crab.getDescribe() + "'," +
                crab.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                crab.getClass().getName() + "')," + crab.isDeleted() + ") RETURNING animal.id;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("SELECT climatetype.climatetype, zonetype.zonetype, foodtype.foodtype FROM animaltype aty " +
                "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                "WHERE aty.animaltype = '"+ crab.getClass().getName() +"'")).thenReturn(resultSetMock);

        Mockito.when(AnimalMapper.getInfoFromRaw(resultSetMock,crab.getClass())).thenReturn(crab);

        crabRepository.create(crab,animalHouse,crab.getClass());


        Mockito.verify(statement, Mockito.times(1)).execute("INSERT INTO Crustacean (Animal_Id,seashell) VALUES (" +
                1 + ",'" + crab.getSeashell() + "');");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,isdeleted) VALUES (' " +
                crab.getName() + "','" +
                crab.getDescribe() + "'," +
                crab.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                crab.getClass().getName() + "')," + crab.isDeleted() + ") RETURNING animal.id;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("SELECT climatetype.climatetype, zonetype.zonetype, foodtype.foodtype FROM animaltype aty " +
                "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                "WHERE aty.animaltype = '"+ crab.getClass().getName() +"'");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO animalinhouse (animalhouse_id, animal_id)" +
                "VALUES (" + animalHouse.getId() + "," + resultSetMock.getLong("id") + ");");


    }

    @Test
    public void getTest() throws SQLException, ClassNotFoundException {

        Mockito.mockStatic(AnimalMapper.class);
        Mockito.mockStatic(CrustaceanMapper.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        Mockito.when(resultSetMock.getString("id")).thenReturn("1");
        Mockito.when(resultSetMock.getLong("id")).thenReturn(1L);
        Mockito.when(resultSetMock.getLong("animal_id")).thenReturn(1L);
        Mockito.when(resultSetMock.next()).thenReturn(true);

        Mockito.when(AnimalMapper.fromRawDataToAnimal(resultSetMock)).thenReturn(crab);
        Mockito.when(CrustaceanMapper.fromRawToCrustacean(crab,resultSetMock)).thenReturn(crab);

        Mockito.when(statement.executeQuery("select * from crustacean where id = " + 1L)).thenReturn(resultSetMock);
        Mockito.when(statement.executeQuery(
                "select an.id, an.name, an.describe, an.age, aty.animaltype, climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted " +
                        "from animal an " +
                        "INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                        "WHERE an.id = " + 1L)).thenReturn(resultSetMock);

        crabRepository.getById(1L);

        Mockito.verify(statement,Mockito.times(1)).executeQuery("select * from crustacean where id = " + 1L);
        Mockito.verify(statement,Mockito.times(1)).executeQuery(
                "select an.id, an.name, an.describe, an.age, aty.animaltype, climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted " +
                        "from animal an " +
                        "INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                        "WHERE an.id = " + 1L);
    }
}
