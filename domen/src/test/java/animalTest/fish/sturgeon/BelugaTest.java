package animalTest.fish.sturgeon;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.fish.sturgeon.Beluga;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class BelugaTest {
    Beluga beluga = new Beluga("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(beluga.getName(), "AnimalName");
        beluga.setName("TempNameForAnimal");
        Assert.assertNotEquals(beluga.getName(), "AnimalName");
        Assert.assertEquals(beluga.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(beluga.getDescribe(), "TestDescribe12");
        beluga.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(beluga.getDescribe(), "TestDescribe12");
        Assert.assertEquals(beluga.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(beluga.getAge(), 1);
        beluga.setAge(-1);
        beluga.setAge(2);
        Assert.assertEquals(beluga.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(beluga.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(beluga.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(beluga.getFoodType(), Bugs.class);
        beluga.setFoodType(Meat.class);
        Assert.assertNotEquals(beluga.getFoodType(), Bugs.class);
        Assert.assertEquals(beluga.getFoodType(), Meat.class);
    }
}
