package animalTest.crustacean.highercancers;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animals.fish.carp.Bream;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class CrabTest {
    Crab crab = new Crab("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class,"Seashell medium");

    public void nameTest(){
        Assert.assertEquals(crab.getName(), "AnimalName");
        crab.setName("TempNameForAnimal");
        Assert.assertNotEquals(crab.getName(), "AnimalName");
        Assert.assertEquals(crab.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(crab.getDescribe(), "TestDescribe12");
        crab.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(crab.getDescribe(), "TestDescribe12");
        Assert.assertEquals(crab.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(crab.getAge(), 1);
        crab.setAge(-1);
        crab.setAge(2);
        Assert.assertEquals(crab.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(crab.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(crab.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(crab.getFoodType(), Bugs.class);
        crab.setFoodType(Meat.class);
        Assert.assertNotEquals(crab.getFoodType(), Bugs.class);
        Assert.assertEquals(crab.getFoodType(), Meat.class);
    }
}
