package zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Lion;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animalhouse.AnimalHouseRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.database.Database;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.channels.CancelledKeyException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Test
public class AnimalHouseRepositoryTest {

    //Connection with data base
    Connection connection = Database.connectWithDataBase();
    AnimalHouseRepositoryImpl animalHouseRepository= new AnimalHouseRepositoryImpl(connection);
    //Testing data
    Field field = new Field(1L, "Fields",2, List.of(Lion.class, Bullfinch.class), ClimateZone.MODERATE);;
    Terrarium terrarium = new Terrarium(1L, "Terrarium", 4, List.of(Crab.class), ClimateZone.TROPICAL);

    public AnimalHouseRepositoryTest() throws SQLException, ClassNotFoundException {}


    public void createTest() throws SQLException, ClassNotFoundException {
        animalHouseRepository.create(terrarium);
    }

    public void getTest() throws SQLException, ClassNotFoundException{
        System.out.println(animalHouseRepository.getById(1L));
//        Assert.assertEquals(animalHouseRepository.getById(1L), field);
//        Assert.assertEquals(1L, animalHouseRepository.getIdByAllParams(field));
    }

    public void addAnimalTest() throws SQLException, ClassNotFoundException{
        animalHouseRepository.addAnimalToHouse(2L,1L);
    }
}
