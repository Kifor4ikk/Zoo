package animalTest.arachnid.spider;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animal.arachnid.spider.Birdman;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Food;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Test
public class BirdmanTest {
    Birdman birdman = new Birdman("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
           Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class), false,8 , "Red");

    public void nameTest(){
        Assert.assertEquals(birdman.getName(), "AnimalName");
        birdman.setName("TempNameForAnimal");
        Assert.assertNotEquals(birdman.getName(), "AnimalName");
        Assert.assertEquals(birdman.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(birdman.getDescribe(), "TestDescribe12");
        birdman.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(birdman.getDescribe(), "TestDescribe12");
        Assert.assertEquals(birdman.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(birdman.getAge(), 1);
        birdman.setAge(-1);
        birdman.setAge(2);
        Assert.assertEquals(birdman.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(birdman.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(birdman.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(birdman.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        birdman.setFoodType(food);
        Assert.assertNotEquals(birdman.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(birdman.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
