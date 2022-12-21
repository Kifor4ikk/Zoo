package zoo.repository.animal.crustacean.highercancers.shrimp;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Shrimp;
import com.epam.rd.tasks.zoo.food.Meat;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.shrimp.ShrimpMapper;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.shrimp.ShrimpRepository;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;

public class ShrimpRepositoryTest {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSetMock;

    private Terrarium animalHouse;
    private Shrimp shrimp;
    private Shrimp shrimp2;

    private ShrimpRepository shrimpRepository;

    private AnimalMapper animalMapper;
    private ShrimpMapper shrimpMapper;

    @BeforeTest
    public void beforeTest() throws SQLException {

        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
        shrimpMapper = Mockito.mock(ShrimpMapper.class);
        shrimpRepository = new ShrimpRepository(connection,shrimpMapper);

        shrimp = new Shrimp("Alex", "Shrimp", 1, Set.of(Field.class, Terrarium.class),
                Set.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Set.of(Meat.class), "Blue shell",false,"Sweet");

        shrimp2 = new Shrimp("Alex222", "Shrimp222", 10, Set.of(Field.class,Terrarium.class),
                Set.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Set.of(Meat.class), "Green shell",false, "Sweet dreams are made of this");

        animalHouse = new Terrarium(1L, "Fields222222",1945, List.of(Crab.class, Bullfinch.class, Shrimp.class), ClimateZone.TROPICAL);
    }

    @Test
    public void create() throws SQLException, ClassNotFoundException{

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        Mockito.when(resultSetMock.getString("id")).thenReturn("1");
        Mockito.when(resultSetMock.getLong("id")).thenReturn(1L);
        Mockito.when(resultSetMock.next()).thenReturn(true);

        Mockito.when(statement.execute("INSERT INTO Crustacean (ID,seashell) VALUES (" +
                1 + ",'" + shrimp.getSeashell() + "') RETURNING ID;")).thenReturn(true);

        Mockito.when(statement.executeQuery("INSERT INTO animalinhouse (animalhouse_id, animal_id)" +
                "VALUES (" + animalHouse.getId() + "," + resultSetMock.getLong("id") + ");")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,createDate,isdeleted) VALUES (' " +
                shrimp.getName() + "','" +
                shrimp.getDescribe() + "'," +
                shrimp.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                shrimp.getClass().getName() + "'),'" +
                Date.valueOf(LocalDate.now()) + "'," +
                shrimp.isDeleted() + ") RETURNING animal.id;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("SELECT climatetype.climatetype, zonetype.zonetype, foodtype.foodtype FROM animaltype aty " +
                "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                "WHERE aty.animaltype = '"+ shrimp.getClass().getName() +"'")).thenReturn(resultSetMock);

        Mockito.when(shrimpMapper.getInfoFromRaw(resultSetMock,shrimp.getClass())).thenReturn((Animal) shrimp);

        shrimpRepository.create(shrimp,animalHouse);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO Crustacean (ID,seashell) VALUES (" +
                1 + ",'" + shrimp.getSeashell() + "') RETURNING ID;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,createDate,isdeleted) VALUES ('" +
                shrimp.getName() + "','" +
                shrimp.getDescribe() + "'," +
                shrimp.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                shrimp.getClass().getName() + "'),'" +
                Date.valueOf(LocalDate.now()) + "'," +
                shrimp.isDeleted() + ") RETURNING animal.id;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("SELECT climatetype.climatetype, zonetype.zonetype, foodtype.foodtype FROM animaltype aty " +
                "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                "WHERE aty.animaltype = '"+ shrimp.getClass().getName() +"'");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO animalinhouse (animalhouse_id, animal_id) " +
                "VALUES (" + animalHouse.getId() + "," + resultSetMock.getLong("id") + ") RETURNING animalhouse_id;");
    }

    @Test
    public void getTest() throws SQLException, ClassNotFoundException {

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(resultSetMock.getString("id")).thenReturn("1");
        Mockito.when(resultSetMock.getLong("id")).thenReturn(1L);
        Mockito.when(resultSetMock.getLong("animal_id")).thenReturn(1L);
        Mockito.when(resultSetMock.next()).thenReturn(true);
        Mockito.when(shrimpMapper.fromRawToAnimal(resultSetMock, shrimp)).thenReturn(shrimp);
        Mockito.when(shrimpMapper.fromRawToCrustacean(resultSetMock, shrimp)).thenReturn(shrimp);
        Mockito.when(shrimpMapper.fromRawToShrimp(resultSetMock, shrimp)).thenReturn(shrimp2);


        Mockito.when(statement.executeQuery(
                "select an.id, an.name, an.describe, an.age, aty.animaltype," +
                        " climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted , cr.seashell , shrimp.size from animal" +
                        " an INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype INNER JOIN crustacean cr ON cr.id = an.id " +
                        "INNER JOIN shrimp ON shrimp.id = an.id " +
                        "WHERE an.id = " + 1L + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Shrimp'")
        ).thenReturn(resultSetMock);

        shrimpRepository.findById(1L);

        Mockito.verify(statement,Mockito.times(1)).executeQuery(
                "select an.id, an.name, an.describe, an.age, aty.animaltype, climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted , cr.seashell , shrimp.size " +
                        "from animal an " +
                        "INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                        "INNER JOIN crustacean cr ON cr.id = an.id " +
                        "INNER JOIN shrimp ON shrimp.id = an.id " +
                        "WHERE an.id = " + 1L + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Shrimp'");
    }

    @Test
    public void updateTest() throws SQLException, ClassNotFoundException {

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        shrimp.setId(1L);

        Mockito.when(statement.executeQuery("UPDATE animal SET" +
                " \"name\" = '" + shrimp.getName() +"', \"describe\" = '" + shrimp.getDescribe() +
                "', age = " + shrimp.getAge() + " WHERE ID = " + shrimp.getId() + " AND isdeleted = false RETURNING true;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("UPDATE crustacean SET seashell = '" + shrimp.getSeashell()
                + "' WHERE ID = " + shrimp.getId())).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("UPDATE Shrimp SET size = '" + shrimp.getSize() + "' WHERE ID = " + shrimp.getId() + " RETURNING true;"))
                .thenReturn(resultSetMock);

        shrimpRepository.update(shrimp);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE animal SET" +
                " \"name\" = '" + shrimp.getName() +"', \"describe\" = '" + shrimp.getDescribe() +
                "', age = " + shrimp.getAge() + " WHERE ID = " + shrimp.getId() + " AND isdeleted = false RETURNING true;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE crustacean SET seashell = '" + shrimp.getSeashell()
                + "' WHERE ID = " + shrimp.getId() + " RETURNING true;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE Shrimp SET size = '"
                + shrimp.getSize() + "' WHERE ID = " + shrimp.getId() + " RETURNING true;");
    }

    @Test
    public void softDeleteTest() throws SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        shrimp.setId(1L);
        shrimpRepository.setDeleteStatus(shrimp.getId(), true);
        Mockito.verify(statement,Mockito.times(1)).execute("UPDATE Animal SET isDeleted = '" + true + "' WHERE id = " + shrimp.getId());
    }


}
