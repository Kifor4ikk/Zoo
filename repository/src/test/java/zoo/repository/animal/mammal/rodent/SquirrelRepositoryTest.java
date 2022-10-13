package zoo.repository.animal.mammal.rodent;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf;
import com.epam.rd.tasks.zoo.animals.mammal.rodent.Squirrel;
import com.epam.rd.tasks.zoo.food.Meat;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;
import com.epam.rd.tasks.zoo.repository.animal.mammal.predator.wolf.WolfMapper;
import com.epam.rd.tasks.zoo.repository.animal.mammal.predator.wolf.WolfRepository;
import com.epam.rd.tasks.zoo.repository.animal.mammal.rodent.squirrel.SquirrelMapper;
import com.epam.rd.tasks.zoo.repository.animal.mammal.rodent.squirrel.SquirrelRepository;
import com.epam.rd.tasks.zoo.repository.database.Database;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;

public class SquirrelRepositoryTest {


    private Connection connection;
    private Statement statement;
    private ResultSet resultSetMock;

    private Field animalHouse;
    private Squirrel squirrel;
    private Squirrel squirrel2;

    private SquirrelRepository squirrelRepository;

    private AnimalMapper animalMapper;
    private SquirrelMapper squirrelMapper;

    public SquirrelRepositoryTest() throws SQLException, ClassNotFoundException {}

    @BeforeTest
    public void beforeTest() throws SQLException {

        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
        squirrelMapper = Mockito.mock(SquirrelMapper.class);
        squirrelRepository = new SquirrelRepository(connection,squirrelMapper);

        squirrel = new Squirrel("Alex", "KingSlayerCrab", 1, Set.of(Field.class, Terrarium.class),
                Set.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Set.of(Meat.class), "Tail","asd");

        squirrel2 = new Squirrel("Alex", "KingSlayerCrab", 1, Set.of(Field.class, Terrarium.class),
                Set.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Set.of(Meat.class), "TailTailTail","Pddd");

        animalHouse = new Field(1L, "Fields222222",1945, List.of(Crab.class, Squirrel.class), ClimateZone.MODERATE);

        squirrel.setId(1L);
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
                "WHERE aty.animaltype = '"+ squirrel.getClass().getName() + "'")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,createDate,isdeleted) VALUES (' " +
                squirrel.getName() + "','" +
                squirrel.getDescribe() + "'," +
                squirrel.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                squirrel.getClass().getName() + "'),'" +
                Date.valueOf(LocalDate.now()) + "'," +
                squirrel.isDeleted() + ") RETURNING animal.id;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO animalinhouse (animalhouse_id, animal_id)" +
                "VALUES (" + animalHouse.getId() + "," + resultSetMock.getLong("id") + ");")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO mammal (ID,tail) VALUES (" +
                1 + ",'" + squirrel.getTail() + "') RETURNING ID;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO squirrel (ID,fur) VALUES (" +
                1 + ",'" + squirrel.getFur() + "') RETURNING ID;")).thenReturn(resultSetMock);

        Mockito.when(squirrelMapper.getInfoFromRaw(resultSetMock,squirrel.getClass())).thenReturn(squirrel);

        squirrelRepository.create(squirrel,animalHouse);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("SELECT climatetype.climatetype, zonetype.zonetype, foodtype.foodtype FROM animaltype aty " +
                "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                "WHERE aty.animaltype = '"+ squirrel.getClass().getName() +"'");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,createDate,isdeleted) VALUES (' " +
                squirrel.getName() + "','" +
                squirrel.getDescribe() + "'," +
                squirrel.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                squirrel.getClass().getName() + "'),'" +
                Date.valueOf(LocalDate.now()) + "'," +
                squirrel.isDeleted() + ") RETURNING animal.id;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO animalinhouse (animalhouse_id, animal_id) " +
                "VALUES (" + animalHouse.getId() + "," + resultSetMock.getLong("id") + ") RETURNING animalhouse_id;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO mammal (ID,tail) VALUES (" +
                1 + ",'" + squirrel.getTail() + "') RETURNING ID;");

    }

    @Test
    public void getTest() throws SQLException, ClassNotFoundException {

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(resultSetMock.getString("id")).thenReturn("1");
        Mockito.when(resultSetMock.getLong("id")).thenReturn(1L);
        Mockito.when(resultSetMock.getLong("animal_id")).thenReturn(1L);
        Mockito.when(resultSetMock.next()).thenReturn(true);
        Mockito.when(squirrelMapper.fromRawToAnimal(resultSetMock, squirrel)).thenReturn(squirrel);
        Mockito.when(squirrelMapper.fromRawToMammal(resultSetMock, squirrel)).thenReturn(squirrel);
        Mockito.when(squirrelMapper.fromRawToSquirrel(resultSetMock, squirrel)).thenReturn(squirrel);

        Mockito.when(statement.executeQuery(
                "select an.id, an.name, an.describe, an.age, aty.animaltype," +
                        " climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted , mammal.tail , squirrel.fur from animal" +
                        " an INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                        "INNER JOIN mammal ON mammal.id = an.id " +
                        "INNER JOIN squirrel ON squirrel.id = an.id " +
                        "WHERE an.id = " + 1L + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animals.mammal.rodent.Squirrel'")
        ).thenReturn(resultSetMock);

        squirrelRepository.getById(1L);

        Mockito.verify(statement,Mockito.times(1)).executeQuery(
                "select an.id, an.name, an.describe, an.age, aty.animaltype," +
                        " climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted , mammal.tail , squirrel.fur from animal" +
                        " an INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                        "INNER JOIN mammal ON mammal.id = an.id " +
                        "INNER JOIN squirrel ON squirrel.id = an.id " +
                        "WHERE an.id = " + 1L + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animals.mammal.rodent.Squirrel'");
    }

    @Test
    public void updateTest() throws SQLException, ClassNotFoundException {

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        squirrel.setId(1L);

        Mockito.when(statement.executeQuery("UPDATE animal SET" +
                " \"name\" = '" + squirrel.getName() +"', \"describe\" = '" + squirrel.getDescribe() +
                "', age = " + squirrel.getAge() + " WHERE ID = " + squirrel.getId() + " AND isdeleted = false RETURNING true;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("UPDATE mammal SET tail = '" + squirrel.getTail()
                + "' WHERE ID = " + squirrel.getId())).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("UPDATE squirrel SET fur = '" + squirrel.getFur() + "' WHERE ID = " + squirrel.getId() + " RETURNING true;"))
                .thenReturn(resultSetMock);

        squirrelRepository.update(squirrel);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE animal SET" +
                " \"name\" = '" + squirrel.getName() +"', \"describe\" = '" + squirrel.getDescribe() +
                "', age = " + squirrel.getAge() + " WHERE ID = " + squirrel.getId() + " AND isdeleted = false RETURNING true;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE mammal SET tail = '" + squirrel.getTail()
                + "' WHERE ID = " + squirrel.getId() + " RETURNING true;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE Squirrel SET fur = '"
                + squirrel.getFur() + "' WHERE ID = " + squirrel.getId() + " RETURNING true;");
    }

    @Test
    public void softDeleteTest() throws SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        squirrel.setId(1L);
        squirrelRepository.setDeleteStatus(squirrel.getId(), true);
        Mockito.verify(statement,Mockito.times(1)).execute("UPDATE Animal SET isDeleted = '" + true + "' WHERE id = " + squirrel.getId());
    }


}
