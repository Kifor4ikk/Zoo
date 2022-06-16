package zoo.repository.animal.crustacean;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Aquarium;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Chameleon;
import com.epam.rd.tasks.zoo.exception.AlreadyExistException;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animalhouse.AnimalHouseRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.database.Database;
import lombok.extern.slf4j.Slf4j;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Test
@Slf4j
public class CrustaceanRepositoryTest {

    @Mock
    Connection connection;
    CrustaceanRepositoryImpl crustaceanRepository;

    @BeforeTest
    public void beforeTest() throws SQLException {
        connection = Mockito.mock(Connection.class);
        crustaceanRepository = new CrustaceanRepositoryImpl(connection);
    }

    @Test
    public void connectionTest(){
        Assert.assertNotNull(crustaceanRepository);
    }
}
