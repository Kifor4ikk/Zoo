package animalTest.crustacean.highercancers;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Shrimp;
import com.epam.rd.tasks.zoo.animals.fish.carp.Bream;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class ShrimpTest {
    Shrimp shrimp = new Shrimp("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(shrimp.getName(), "AnimalName");
        shrimp.setName("TempNameForAnimal");
        Assert.assertNotEquals(shrimp.getName(), "AnimalName");
        Assert.assertEquals(shrimp.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(shrimp.getDescribe(), "TestDescribe12");
        shrimp.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(shrimp.getDescribe(), "TestDescribe12");
        Assert.assertEquals(shrimp.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(shrimp.getAge(), 1);
        shrimp.setAge(-1);
        shrimp.setAge(2);
        Assert.assertEquals(shrimp.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(shrimp.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(shrimp.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(shrimp.getFoodType(), Bugs.class);
        shrimp.setFoodType(Meat.class);
        Assert.assertNotEquals(shrimp.getFoodType(), Bugs.class);
        Assert.assertEquals(shrimp.getFoodType(), Meat.class);
    }
}
