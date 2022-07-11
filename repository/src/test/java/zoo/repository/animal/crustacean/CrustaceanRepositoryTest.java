package zoo.repository.animal.crustacean;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Aquarium;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Lion;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Chameleon;
import com.epam.rd.tasks.zoo.exception.AlreadyExistException;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import com.epam.rd.tasks.zoo.food.Wheat;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancers.crab.CrabRepository;
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
import java.sql.SQLOutput;
import java.util.AbstractQueue;
import java.util.List;

@Test
public class CrustaceanRepositoryTest {

    Connection connection = Database.connectWithDataBase();
    CrustaceanRepositoryImpl crustaceanRepository;
    CrabRepository crabRepository;

    public CrustaceanRepositoryTest() throws SQLException, ClassNotFoundException {
    }

    @BeforeTest
    public void beforeTest() throws SQLException {

        crustaceanRepository = new CrustaceanRepositoryImpl(connection);
        crabRepository = new CrabRepository(connection);
    }

    @Test
    public void connectionTest(){
        Assert.assertNotNull(crustaceanRepository);
    }

    @Test
    public void test() throws SQLException, ClassNotFoundException {
        System.out.println(crabRepository.getById(1L));
    }
}
