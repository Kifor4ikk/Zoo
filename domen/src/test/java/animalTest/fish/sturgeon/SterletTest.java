package animalTest.fish.sturgeon;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.fish.sturgeon.Sterlet;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class SterletTest {
    Sterlet sterlet = new Sterlet("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(sterlet.getName(), "AnimalName");
        sterlet.setName("TempNameForAnimal");
        Assert.assertNotEquals(sterlet.getName(), "AnimalName");
        Assert.assertEquals(sterlet.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(sterlet.getDescribe(), "TestDescribe12");
        sterlet.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(sterlet.getDescribe(), "TestDescribe12");
        Assert.assertEquals(sterlet.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(sterlet.getAge(), 1);
        sterlet.setAge(-1);
        sterlet.setAge(2);
        Assert.assertEquals(sterlet.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(sterlet.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(sterlet.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(sterlet.getFoodType(), Bugs.class);
        sterlet.setFoodType(Meat.class);
        Assert.assertNotEquals(sterlet.getFoodType(), Bugs.class);
        Assert.assertEquals(sterlet.getFoodType(), Meat.class);
    }
}
