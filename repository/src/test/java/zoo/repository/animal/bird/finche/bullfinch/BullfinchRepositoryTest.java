package zoo.repository.animal.bird.finche.bullfinch;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.food.Meat;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;
import com.epam.rd.tasks.zoo.repository.animal.bird.finche.bullfinch.BullfinchMapper;
import com.epam.rd.tasks.zoo.repository.animal.bird.finche.bullfinch.BullfinchRepository;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;

public class BullfinchRepositoryTest {



    private Connection connection;
    private Statement statement;
    private ResultSet resultSetMock;

    private Field animalHouse;
    private Bullfinch bullfinch;
    private Bullfinch bullfinch2;

    private BullfinchRepository bullfinchRepository;

    private AnimalMapper animalMapper;
    private BullfinchMapper bullfinchMapper;

    public BullfinchRepositoryTest() throws SQLException, ClassNotFoundException {}

    @BeforeTest
    public void beforeTest() throws SQLException {

        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
        bullfinchMapper = Mockito.mock(BullfinchMapper.class);
        bullfinchRepository = new BullfinchRepository(connection,bullfinchMapper);

        bullfinch = new Bullfinch("Alex", "KingSlayerCrab", 1, Set.of(Field.class, Terrarium.class),
                Set.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Set.of(Meat.class), "Tail","asd");

        bullfinch2 = new Bullfinch("Alex", "KingSlayerCrab", 1, Set.of(Field.class, Terrarium.class),
                Set.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Set.of(Meat.class), "TailTailTail","Pddd");

        animalHouse = new Field(1L, "Fields222222",1945, List.of(Crab.class, Bullfinch.class), ClimateZone.MODERATE);

        bullfinch.setId(1L);
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
                "WHERE aty.animaltype = '"+ bullfinch.getClass().getName() + "'")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,createDate,isdeleted) VALUES (' " +
                bullfinch.getName() + "','" +
                bullfinch.getDescribe() + "'," +
                bullfinch.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                bullfinch.getClass().getName() + "')," + Date.valueOf(LocalDate.now()) + bullfinch.isDeleted() + ") RETURNING animal.id;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO animalinhouse (animalhouse_id, animal_id)" +
                "VALUES (" + animalHouse.getId() + "," + resultSetMock.getLong("id") + ");")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO bird (ID,wings) VALUES (" +
                1 + ",'" + bullfinch.getWings() + "') RETURNING ID;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("INSERT INTO bullfinch (ID,color) VALUES (" +
                1 + ",'" + bullfinch.getColor() + "') RETURNING ID;")).thenReturn(resultSetMock);

        Mockito.when(bullfinchMapper.getInfoFromRaw(resultSetMock,bullfinch.getClass())).thenReturn(bullfinch);

        bullfinchRepository.create(bullfinch,animalHouse);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("SELECT climatetype.climatetype, zonetype.zonetype, foodtype.foodtype FROM animaltype aty " +
                "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                "WHERE aty.animaltype = '"+ bullfinch.getClass().getName() +"'");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO animal (name,describe,age,id_animaltype,createDate,isdeleted) VALUES ('" +
                bullfinch.getName() + "','" +
                bullfinch.getDescribe() + "'," +
                bullfinch.getAge() + ", (SELECT aType.id FROM animalType aType WHERE aType.animalType = '" +
                bullfinch.getClass().getName() + "'),'" +
                Date.valueOf(LocalDate.now()) + "'," +
                bullfinch.isDeleted() + ") RETURNING animal.id;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO animalinhouse (animalhouse_id, animal_id) " +
                "VALUES (" + animalHouse.getId() + "," + resultSetMock.getLong("id") + ") RETURNING animalhouse_id;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("INSERT INTO bird (ID,wings) VALUES (" +
                1 + ",'" + bullfinch.getWings() + "') RETURNING ID;");

    }

    @Test
    public void getTest() throws SQLException, ClassNotFoundException {

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(resultSetMock.getString("id")).thenReturn("1");
        Mockito.when(resultSetMock.getLong("id")).thenReturn(1L);
        Mockito.when(resultSetMock.getLong("animal_id")).thenReturn(1L);
        Mockito.when(resultSetMock.next()).thenReturn(true);
        Mockito.when(bullfinchMapper.fromRawToAnimal(resultSetMock, bullfinch)).thenReturn(bullfinch);
        Mockito.when(bullfinchMapper.fromRawToBird(resultSetMock, bullfinch)).thenReturn(bullfinch);
        Mockito.when(bullfinchMapper.fromRawToBullfinch(resultSetMock, bullfinch)).thenReturn(bullfinch);

        Mockito.when(statement.executeQuery(
                "select an.id, an.name, an.describe, an.age, aty.animaltype," +
                        " climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted , bird.wings , bullfinch.color from animal" +
                        " an INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                        "INNER JOIN bird ON bird.id = an.id " +
                        "INNER JOIN bullfinch ON bullfinch.id = an.id " +
                        "WHERE an.id = " + 1L + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch'")
        ).thenReturn(resultSetMock);

        bullfinchRepository.findById(1L);

        Mockito.verify(statement,Mockito.times(1)).executeQuery(
                "select an.id, an.name, an.describe, an.age, aty.animaltype," +
                        " climatetype.climatetype, zonetype.zonetype, foodtype.foodtype, an.isdeleted , bird.wings , bullfinch.color from animal" +
                        " an INNER JOIN animaltype aty ON aty.id = an.id_animaltype " +
                        "INNER JOIN climatetypefortypeofanimal ctfta ON ctfta.id_typeofanimal = aty.id " +
                        "INNER JOIN climatetype ON climatetype.id = ctfta.id_climatetype " +
                        "INNER JOIN zonetypefortypeanimal ztfta ON ztfta.id_typeofanimal = aty.id " +
                        "INNER JOIN zonetype ON zonetype.id = ztfta.id_zonetype " +
                        "INNER JOIN foodtypefortypeanimal ftfta ON ftfta.id_typeofanimal = aty.id " +
                        "INNER JOIN foodtype ON foodtype.id = ftfta.id_foodtype " +
                        "INNER JOIN bird ON bird.id = an.id " +
                        "INNER JOIN bullfinch ON bullfinch.id = an.id " +
                        "WHERE an.id = " + 1L + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch'");
    }

    @Test
    public void updateTest() throws SQLException, ClassNotFoundException {

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSetMock);
        bullfinch.setId(1L);

        Mockito.when(statement.executeQuery("UPDATE animal SET" +
                " \"name\" = '" + bullfinch.getName() +"', \"describe\" = '" + bullfinch.getDescribe() +
                "', age = " + bullfinch.getAge() + " WHERE ID = " + bullfinch.getId() + " AND isdeleted = false RETURNING true;")).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("UPDATE bird SET wings = '" + bullfinch.getWings()
                + "' WHERE ID = " + bullfinch.getId())).thenReturn(resultSetMock);

        Mockito.when(statement.executeQuery("UPDATE bullfinch SET color = '" + bullfinch.getColor() + "' WHERE ID = " + bullfinch.getId() + " RETURNING true;"))
                .thenReturn(resultSetMock);

        bullfinchRepository.update(bullfinch);

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE animal SET" +
                " \"name\" = '" + bullfinch.getName() +"', \"describe\" = '" + bullfinch.getDescribe() +
                "', age = " + bullfinch.getAge() + " WHERE ID = " + bullfinch.getId() + " AND isdeleted = false RETURNING true;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE bird SET wings = '" + bullfinch.getWings()
                + "' WHERE ID = " + bullfinch.getId() + " RETURNING true;");

        Mockito.verify(statement, Mockito.times(1)).executeQuery("UPDATE Bullfinch SET color = '"
                + bullfinch.getColor() + "' WHERE ID = " + bullfinch.getId() + " RETURNING true;");
    }

    @Test
    public void softDeleteTest() throws SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        bullfinch.setId(1L);
        bullfinchRepository.setDeleteStatus(bullfinch.getId(), true);
        Mockito.verify(statement,Mockito.times(1)).execute("UPDATE Animal SET isDeleted = '" + true + "' WHERE id = " + bullfinch.getId());
    }
}
