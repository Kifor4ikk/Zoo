package animalTest.arachnid.scorpio;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.arachnid.scorpio.ScorpioItalian;
import com.epam.rd.tasks.zoo.animals.bird.pigeonlike.Vyakhir;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class ScorpioItalianTest {
    ScorpioItalian scorpio = new ScorpioItalian("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(scorpio.getName(), "AnimalName");
        scorpio.setName("TempNameForAnimal");
        Assert.assertNotEquals(scorpio.getName(), "AnimalName");
        Assert.assertEquals(scorpio.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(scorpio.getDescribe(), "TestDescribe12");
        scorpio.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(scorpio.getDescribe(), "TestDescribe12");
        Assert.assertEquals(scorpio.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(scorpio.getAge(), 1);
        scorpio.setAge(-1);
        scorpio.setAge(2);
        Assert.assertEquals(scorpio.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(scorpio.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(scorpio.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(scorpio.getFoodType(), Bugs.class);
        scorpio.setFoodType(Meat.class);
        Assert.assertNotEquals(scorpio.getFoodType(), Bugs.class);
        Assert.assertEquals(scorpio.getFoodType(), Meat.class);
    }
}
