package animalTest.fish.carp;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.fish.carp.Bream;
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
public class BreamTest {
    Bream bream = new Bream("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class));

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
        Assert.assertEquals(bream.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(bream.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(bream.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        bream.setFoodType(food);
        Assert.assertNotEquals(bream.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(bream.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
