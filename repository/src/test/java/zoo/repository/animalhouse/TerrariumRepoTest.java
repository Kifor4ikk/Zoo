package zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Chameleon;
import com.epam.rd.tasks.zoo.repository.animal.CrabImpl;
import com.epam.rd.tasks.zoo.repository.animalhouse.TerrariumRepoImpl;
import com.epam.rd.tasks.zoo.repository.database.Database;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Test
public class TerrariumRepoTest {
    Connection connection = Database.connectWithDataBase();
    TerrariumRepoImpl terrariumRepo = new TerrariumRepoImpl(connection);
    Terrarium terrarium = new Terrarium(1L, "Terrarium", 4, List.of(Chameleon.class), ClimateZone.TROPICAL);

    public TerrariumRepoTest() throws SQLException, ClassNotFoundException {}

    public void createTest() throws SQLException {
        terrariumRepo.create(terrarium);
    }

    public void getTest() throws SQLException{
        System.out.println(terrariumRepo.getById(1L));
    }
}
