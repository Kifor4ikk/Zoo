package zoo.repository.animal.mammal.predator;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf;
import com.epam.rd.tasks.zoo.food.Meat;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.crab.CrabMapper;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.crab.CrabRepository;
import com.epam.rd.tasks.zoo.repository.animal.mammal.predator.wolf.WolfMapper;
import com.epam.rd.tasks.zoo.repository.animal.mammal.predator.wolf.WolfRepository;
import com.epam.rd.tasks.zoo.repository.database.Database;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;

public class WolfRepositoryTest {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSetMock;

    private Field animalHouse;
    private Wolf wolf;
    private Wolf wolf2;

    private WolfRepository wolfRepository;

    private AnimalMapper animalMapper;
    private WolfMapper wolfMapper;

    public WolfRepositoryTest() throws SQLException, ClassNotFoundException {}

    @BeforeTest
    public void beforeTest() throws SQLException {

        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
        wolfMapper = Mockito.mock(WolfMapper.class);
        wolfRepository = new WolfRepository(connection,wolfMapper);

        wolf = new Wolf("Alex", "KingSlayerCrab", 1, Set.of(Field.class, Terrarium.class),
                Set.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Set.of(Meat.class), "Tail","Aggressive");

        wolf2 = new Wolf("Alex", "KingSlayerCrab", 1, Set.of(Field.class, Terrarium.class),
                Set.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Set.of(Meat.class), "TailTailTail","Passive");

        animalHouse = new Field(1L, "Fields222222",1945, List.of(Crab.class, Wolf.class), ClimateZone.MODERATE);

        wolf.setId(1L);
    }

    @Test
    public void create() throws SQLException, ClassNotFoundException{

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        Mockito.when(resultSetMock.getString("id")).thenReturn("1");
        Mockito.when(resultSetMock.getLong("id")).thenReturn(1L);
        Mockito.when(resultSetMock.getLong("ID")).thenReturn(1L);
        Mockito.when(resultSetMock.next()).thenReturn(true);

        Mockito.when(statement.executeQuery("SELECT climatetype.climatetype, zonetype.zonetype, foodtype.foodtype FROM animaltype aty " +
                "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                "WHERE aty.animaltype = '"+ wolf.getClass().getName() + "'")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,isdeleted) VALUES (' " +
                wolf.getName() + "','" +
                wolf.getDescribe() + "'," +
                wolf.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                wolf.getClass().getName() + "')," + wolf.isDeleted() + ") RETURNING animal.id;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO animalinhouse (animalhouse_id, animal_id)" +
                "VALUES (" + animalHouse.getId() + "," + resultSetMock.getLong("id") + ");")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO mammal (ID,tail) VALUES (" +
                1 + ",'" + wolf.getTail() + "') RETURNING ID;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO wolf (ID,behaviour) VALUES (" +
                1 + ",'" + wolf.getBehaviour() + "') RETURNING ID;")).thenReturn(resultSetMock);

        Mockito.when(wolfMapper.getInfoFromRaw(resultSetMock,wolf.getClass())).thenReturn(wolf);

        wolfRepository.create(wolf,animalHouse);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("SELECT climatetype.climatetype, zonetype.zonetype, foodtype.foodtype FROM animaltype aty " +
                "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                "WHERE aty.animaltype = '"+ wolf.getClass().getName() +"'");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,isdeleted) VALUES (' " +
                wolf.getName() + "','" +
                wolf.getDescribe() + "'," +
                wolf.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                wolf.getClass().getName() + "')," + wolf.isDeleted() + ") RETURNING animal.id;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO animalinhouse (animalhouse_id, animal_id) " +
                "VALUES (" + animalHouse.getId() + "," + resultSetMock.getLong("id") + ") RETURNING animalhouse_id;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO mammal (ID,tail) VALUES (" +
                1 + ",'" + wolf.getTail() + "') RETURNING ID;");

    }

    @Test
    public void getTest() throws SQLException, ClassNotFoundException {

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(resultSetMock.getString("id")).thenReturn("1");
        Mockito.when(resultSetMock.getLong("id")).thenReturn(1L);
        Mockito.when(resultSetMock.getLong("animal_id")).thenReturn(1L);
        Mockito.when(resultSetMock.next()).thenReturn(true);
        Mockito.when(wolfMapper.fromRawToAnimal(resultSetMock, wolf)).thenReturn(wolf);
        Mockito.when(wolfMapper.fromRawToMammal(resultSetMock, wolf)).thenReturn(wolf);
        Mockito.when(wolfMapper.fromRawToWolf(resultSetMock, wolf)).thenReturn(wolf2);

        Mockito.when(statement.executeQuery(
                "select an.id, an.name, an.describe, an.age, aty.animaltype," +
                        " climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted , mammal.tail , wolf.behaviour from animal" +
                        " an INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                        "INNER JOIN mammal ON mammal.id = an.id " +
                        "INNER JOIN wolf ON wolf.id = an.id " +
                        "WHERE an.id = " + 1L + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf'")
        ).thenReturn(resultSetMock);

        wolfRepository.getById(1L);

        Mockito.verify(statement,Mockito.times(1)).executeQuery(
                "select an.id, an.name, an.describe, an.age, aty.animaltype," +
                        " climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted , mammal.tail , wolf.behaviour from animal" +
                        " an INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                        "INNER JOIN mammal ON mammal.id = an.id " +
                        "INNER JOIN wolf ON wolf.id = an.id " +
                        "WHERE an.id = " + 1L + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf'");
    }

    @Test
    public void updateTest() throws SQLException, ClassNotFoundException {

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        wolf.setId(1L);

        Mockito.when(statement.executeQuery("UPDATE animal SET" +
                " \"name\" = '" + wolf.getName() +"', \"describe\" = '" + wolf.getDescribe() +
                "', age = " + wolf.getAge() + " WHERE ID = " + wolf.getId() + " AND isdeleted = false RETURNING true;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("UPDATE mammal SET tail = '" + wolf.getTail()
                + "' WHERE ID = " + wolf.getId())).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("UPDATE wolf SET behaviour = '" + wolf.getBehaviour() + "' WHERE ID = " + wolf.getId() + " RETURNING true;"))
                .thenReturn(resultSetMock);

        wolfRepository.update(wolf);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE animal SET" +
                " \"name\" = '" + wolf.getName() +"', \"describe\" = '" + wolf.getDescribe() +
                "', age = " + wolf.getAge() + " WHERE ID = " + wolf.getId() + " AND isdeleted = false RETURNING true;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE mammal SET tail = '" + wolf.getTail()
                + "' WHERE ID = " + wolf.getId() + " RETURNING true;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE wolf SET behaviour = '"
                + wolf.getBehaviour() + "' WHERE ID = " + wolf.getId() + " RETURNING true;");
    }

    @Test
    public void softDeleteTest() throws SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        wolf.setId(1L);
        wolfRepository.setDeleteStatus(wolf.getId(), true);
        Mockito.verify(statement,Mockito.times(1)).execute("UPDATE Animal SET isDeleted = '" + true + "' WHERE id = " + wolf.getId());
    }
}
