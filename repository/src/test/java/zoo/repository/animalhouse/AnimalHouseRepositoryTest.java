package zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Lion;
import com.epam.rd.tasks.zoo.repository.animalhouse.AnimalHouseRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.database.Database;
import org.checkerframework.checker.units.qual.A;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Test
public class AnimalHouseRepositoryTest {

    Connection connection;
    AnimalHouseRepositoryImpl animalHouseRepository;

    @BeforeTest
    public void beforeTest() throws SQLException, ClassNotFoundException {
        connection = Database.connectWithDataBase();
        animalHouseRepository = new AnimalHouseRepositoryImpl(connection);
    }

    @Test
    public void connectionTest(){
        Assert.assertNotNull(animalHouseRepository);
    }
    Field field = new Field(1L, "Fields",2, List.of(Lion.class, Bullfinch.class), ClimateZone.MODERATE);
    @Test
    public void createTest() throws SQLException {

        animalHouseRepository.create(field);
    }

    @Test
    public void getTest() throws  SQLException{
        System.out.println(animalHouseRepository.getById(17L));

    }

}
