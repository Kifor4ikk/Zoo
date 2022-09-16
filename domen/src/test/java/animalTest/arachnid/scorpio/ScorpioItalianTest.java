package animalTest.arachnid.scorpio;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.arachnid.scorpio.ScorpioItalian;
import com.epam.rd.tasks.zoo.animals.bird.pigeonlike.Vyakhir;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Food;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Test
public class ScorpioItalianTest {
    ScorpioItalian scorpio = new ScorpioItalian("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class),false, 6);

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
        Assert.assertEquals(scorpio.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(scorpio.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(scorpio.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        scorpio.setFoodType(food);
        Assert.assertNotEquals(scorpio.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(scorpio.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
