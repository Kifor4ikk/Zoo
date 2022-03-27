package animalTest.fish.carp;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.fish.carp.Bream;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class BreamTest {
    Bream bream = new Bream("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(bream.getName(), "AnimalName");
        bream.setName("TempNameForAnimal");
        Assert.assertNotEquals(bream.getName(), "AnimalName");
        Assert.assertEquals(bream.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(bream.getDescribe(), "TestDescribe12");
        bream.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(bream.getDescribe(), "TestDescribe12");
        Assert.assertEquals(bream.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(bream.getAge(), 1);
        bream.setAge(-1);
        bream.setAge(2);
        Assert.assertEquals(bream.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(bream.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(bream.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(bream.getFoodType(), Bugs.class);
        bream.setFoodType(Meat.class);
        Assert.assertNotEquals(bream.getFoodType(), Bugs.class);
        Assert.assertEquals(bream.getFoodType(), Meat.class);
    }
}
