package financeTest.expenses;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Aquarium;
import com.epam.rd.tasks.zoo.animal.fish.carp.Bream;
import com.epam.rd.tasks.zoo.animal.fish.sturgeon.Beluga;
import com.epam.rd.tasks.zoo.animal.fish.sturgeon.Sterlet;
import com.epam.rd.tasks.zoo.finance.expenses.Maintenance;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Test
public class MaintenanceTest {

    Aquarium aquarium = new Aquarium(1L,"Aqualand",4, List.of(Beluga.class, Sterlet.class, Bream.class), ClimateZone.MODERATE);
    Maintenance maintenance = new Maintenance("Changed water in AquaLand", LocalDateTime.now(), BigDecimal.valueOf(4), aquarium);

    public void describeTest(){
        Assert.assertEquals(maintenance.getDescribe(),"Changed water in AquaLand");
        maintenance.setDescribe("Changed water in Aquarium");
        Assert.assertNotEquals(maintenance.getDescribe(),"Changed water in AquaLand");
        Assert.assertEquals(maintenance.getDescribe(),"Changed water in Aquarium");
    }

    public void costTest(){
        Assert.assertEquals(maintenance.getCost(), BigDecimal.valueOf(4));
    }

    public void maintenanceCageTest(){
        Assert.assertEquals(maintenance.getAnimalHouse(), aquarium);
    }
}
