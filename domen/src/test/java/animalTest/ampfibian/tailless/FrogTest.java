package animalTest.ampfibian.tailless;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.ampfibian.tailless.Frog;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class FrogTest {
    Frog frog = new Frog("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(frog.getName(), "AnimalName");
        frog.setName("TempNameForAnimal");
        Assert.assertNotEquals(frog.getName(), "AnimalName");
        Assert.assertEquals(frog.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(frog.getDescribe(), "TestDescribe12");
        frog.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(frog.getDescribe(), "TestDescribe12");
        Assert.assertEquals(frog.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(frog.getAge(), 1);
        frog.setAge(-1);
        frog.setAge(2);
        Assert.assertEquals(frog.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(frog.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(frog.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(frog.getFoodType(), Bugs.class);
        frog.setFoodType(Meat.class);
        Assert.assertNotEquals(frog.getFoodType(), Bugs.class);
        Assert.assertEquals(frog.getFoodType(), Meat.class);
    }
}
