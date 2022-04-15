package zoo.repository.animal;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Chameleon;
import com.epam.rd.tasks.zoo.dto.animal.AnimalDto;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.repository.animal.CrabImpl;
import com.epam.rd.tasks.zoo.repository.database.Database;
import org.postgresql.util.PSQLException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Test
public class CrabRepoTest {

    Connection connection = Database.connectWithDataBase();
    CrabImpl crabImpl = new CrabImpl(connection);
    Terrarium terrarium = new Terrarium(1L, "Terrarium", 4, List.of(Chameleon.class), ClimateZone.TROPICAL);

    public CrabRepoTest() throws SQLException, ClassNotFoundException {}

    @Test(expectedExceptions = PSQLException.class)
    public void createGetTest() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Crab crab = new Crab("AnimalName","TestDescribe12",1, Terrarium.class,
                List.of(ClimateZone.TROPICAL, ClimateZone.SUBARCTIC,ClimateZone.ANTARCTIC), Bugs.class);
        crabImpl.create(AnimalDto.toDTO(crab),terrarium);
        Crab crab2 = crabImpl.getById(1L);
        Assert.assertEquals(crab2,crab);
    }

    public void getTest() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

    }

}
