package animalTest.mammal.rodent;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.mammal.rodent.Squirrel;
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
public class SquirrelTest {

    Squirrel squirrel = new Squirrel("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class), "Tail");

    public void nameTest(){
        Assert.assertEquals(squirrel.getName(), "AnimalName");
        squirrel.setName("TempNameForAnimal");
        Assert.assertNotEquals(squirrel.getName(), "AnimalName");
        Assert.assertEquals(squirrel.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(squirrel.getDescribe(), "TestDescribe12");
        squirrel.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(squirrel.getDescribe(), "TestDescribe12");
        Assert.assertEquals(squirrel.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(squirrel.getAge(), 1);
        squirrel.setAge(-1);
        squirrel.setAge(2);
        Assert.assertEquals(squirrel.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(squirrel.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(squirrel.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(squirrel.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        squirrel.setFoodType(food);
        Assert.assertNotEquals(squirrel.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(squirrel.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
