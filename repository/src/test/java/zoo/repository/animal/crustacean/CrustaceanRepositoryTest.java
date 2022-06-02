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
import com.epam.rd.tasks.zoo.repository.database.Database;
import lombok.extern.slf4j.Slf4j;

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
    //Connection with data base
    Connection connection = Database.connectWithDataBase();
    CrustaceanRepositoryImpl crustaceanRepository = new CrustaceanRepositoryImpl(connection);

    //Test Data
    Terrarium terrarium = new Terrarium(1L, "Terrarium", 4, List.of(Crab.class), ClimateZone.TROPICAL);
    Crab crab = new Crab("AnimalName222", "222TestDescribe12", 1, Terrarium.class,
            List.of(ClimateZone.TROPICAL), Bugs.class, "Red sun shine in the sky");

    Crab crab2 = new Crab("Test", "Test", 12, Aquarium.class,
            List.of(ClimateZone.TROPICAL), Meat.class, "Green valleys bellow");

    public CrustaceanRepositoryTest() throws SQLException, ClassNotFoundException {
    }

    @BeforeTest
    public void setParams() {
        crab.setId(1L);
        crab2.setId(2L);
    }


    public void create() throws SQLException, ClassNotFoundException {
        crustaceanRepository.create(crab, terrarium);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        System.out.println(crustaceanRepository.getById(1L));
        System.out.println(crustaceanRepository.getByAllParamsFromAnimal(crab));
        Assert.assertEquals(crab, crustaceanRepository.getById(1L));
        Assert.assertEquals(crab, crustaceanRepository.getByAllParamsFromAnimal(crab));
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        crustaceanRepository.update(crab2);
        Assert.assertEquals(crab2, crustaceanRepository.getById(2L));
        Assert.assertEquals(crab2, crustaceanRepository.getByAllParamsFromAnimal(crab2));
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void softDelete() throws SQLException, ClassNotFoundException {
        crustaceanRepository.setDeleteStatus(1L, true);
        crustaceanRepository.getById(1L);
    }

    @Test
    public void softRecover() throws SQLException, ClassNotFoundException {
        crustaceanRepository.setDeleteStatus(1L, false);
        Assert.assertFalse(crustaceanRepository.getById(1L).isDeleted());
    }

    @AfterTest
    public void closeConnection() throws SQLException {
        crustaceanRepository.hardDelete(1L);
        connection.close();
    }
}
