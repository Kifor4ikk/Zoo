package animalTest.reptile.scaled;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Chameleon;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class ChameleonTest {

    Chameleon chameleon = new Chameleon("Cham","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(chameleon.getName(), "Cham");
        chameleon.setName("TempNameForAnimal");
        Assert.assertNotEquals(chameleon.getName(), "Cham");
        Assert.assertEquals(chameleon.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(chameleon.getDescribe(), "TestDescribe12");
        chameleon.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(chameleon.getDescribe(), "TestDescribe12");
        Assert.assertEquals(chameleon.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(chameleon.getAge(), 1);
        chameleon.setAge(-1);
        chameleon.setAge(2);
        Assert.assertEquals(chameleon.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(chameleon.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(chameleon.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(chameleon.getFoodType(), Bugs.class);
        chameleon.setFoodType(Meat.class);
        Assert.assertNotEquals(chameleon.getFoodType(), Bugs.class);
        Assert.assertEquals(chameleon.getFoodType(), Meat.class);
    }
}
