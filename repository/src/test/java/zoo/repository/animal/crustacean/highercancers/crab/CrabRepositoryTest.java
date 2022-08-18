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
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancers.crab.CrabMapper;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancers.crab.CrabRepository;
import com.epam.rd.tasks.zoo.repository.database.Database;
import com.epam.rd.tasks.zoo.repository.database.RepositoryConnection;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.internal.util.StringUtil;
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
    private Crab crab2;

    private CrabRepository crabRepository;

    private AnimalMapper animalMapper;
    private CrabMapper crabMapper;

    public CrabRepositoryTest() throws SQLException, ClassNotFoundException {}

    @BeforeTest
    public void beforeTest() throws SQLException {

        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
        crabMapper = Mockito.mock(CrabMapper.class);
        crabRepository = new CrabRepository(connection,crabMapper);

        crab = new Crab("Alex", "KingSlayerCrab", 1, Set.of(Field.class,Terrarium.class),
                Set.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Set.of(Meat.class), "Blue shell");

        crab2 = new Crab("Alex222", "KingSlayerCrab222", 10, Set.of(Field.class,Terrarium.class),
                Set.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Set.of(Meat.class), "Green shell");

        animalHouse = new Field(3L, "Fields222222",1945, List.of(Crab.class, Bullfinch.class), ClimateZone.SUBANTARCTIC);
    }

    @Test
    public void test() throws SQLException, ClassNotFoundException {
        Connection connection = Database.connectWithDataBase();
        CrabRepository crabRepository = new CrabRepository(connection, crabMapper);
        System.out.println(crabRepository.getById(1L));
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

        Mockito.when(crabMapper.getInfoFromRaw(resultSetMock,crab.getClass())).thenReturn((Animal) crab);

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

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(resultSetMock.getString("id")).thenReturn("1");
        Mockito.when(resultSetMock.getLong("id")).thenReturn(1L);
        Mockito.when(resultSetMock.getLong("animal_id")).thenReturn(1L);
        Mockito.when(resultSetMock.next()).thenReturn(true);
        Mockito.when(crabMapper.fromRawToAnimal(resultSetMock, crab)).thenReturn(crab);
        Mockito.when(crabMapper.fromRawToCrustacean(resultSetMock, crab)).thenReturn(crab);

        Mockito.when(statement.executeQuery(
                "select an.name, an.describe, an.age, aty.animaltype, climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted, cr.seashell " +
                        "from animal an " +
                        "INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                        "INNER JOIN crustacean cr ON cr.animal_id = an.id " +
                        "WHERE cr.id = " + 1L + " AND an.isDeleted = false")).thenReturn(resultSetMock);

        crabRepository.getById(1L);

        Mockito.verify(statement,Mockito.times(1)).executeQuery(
                "select an.name, an.describe, an.age, aty.animaltype, climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted , cr.seashell " +
                        "from animal an " +
                        "INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                        "INNER JOIN crustacean cr ON cr.animal_id = an.id " +
                        "WHERE cr.id = "+ 1L + " AND an.isDeleted = false ");
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        Mockito.mockStatic(AnimalMapper.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        crab.setId(1L);

        Mockito.when(statement.executeQuery("UPDATE animal SET" +
                " \"name\" = '" + crab.getName() +"', \"describe\" = '" + crab.getDescribe() +
                "', age = " + crab.getAge() + " WHERE ID = " + crab.getId() + " AND isdeleted = false RETURNING true;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("UPDATE crustacean SET seashell = '" + crab.getSeashell()
                + "' WHERE animal_id = " + crab.getId())).thenReturn(resultSetMock);

        crabRepository.update(crab);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE animal SET" +
                " \"name\" = '" + crab.getName() +"', \"describe\" = '" + crab.getDescribe() +
                "', age = " + crab.getAge() + " WHERE ID = " + crab.getId() + " AND isdeleted = false RETURNING true;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE crustacean SET seashell = '" + crab.getSeashell()
                + "' WHERE animal_Id = " + crab.getId() + " RETURNING true;");
    }

    @Test
    public void softDeleteTest() throws SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        crab.setId(1L);
        crabRepository.setDeleteStatus(crab.getId(), true);
        Mockito.verify(statement,Mockito.times(1)).execute("UPDATE Animal SET isDeleted = '" + true + "' WHERE id = " + crab.getId());
    }
}
