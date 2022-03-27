package animalTest.insect.coleoptera;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.insect.coleoptera.StagBeetle;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class StagBeetleTest {
    StagBeetle stagbeetle = new StagBeetle("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(stagbeetle.getName(), "AnimalName");
        stagbeetle.setName("TempNameForAnimal");
        Assert.assertNotEquals(stagbeetle.getName(), "AnimalName");
        Assert.assertEquals(stagbeetle.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(stagbeetle.getDescribe(), "TestDescribe12");
        stagbeetle.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(stagbeetle.getDescribe(), "TestDescribe12");
        Assert.assertEquals(stagbeetle.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(stagbeetle.getAge(), 1);
        stagbeetle.setAge(-1);
        stagbeetle.setAge(2);
        Assert.assertEquals(stagbeetle.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(stagbeetle.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(stagbeetle.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(stagbeetle.getFoodType(), Bugs.class);
        stagbeetle.setFoodType(Meat.class);
        Assert.assertNotEquals(stagbeetle.getFoodType(), Bugs.class);
        Assert.assertEquals(stagbeetle.getFoodType(), Meat.class);
    }
}
