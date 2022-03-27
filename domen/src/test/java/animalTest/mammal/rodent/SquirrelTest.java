package animalTest.mammal.rodent;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.mammal.rodent.Squirrel;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Chameleon;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class SquirrelTest {

    Squirrel squirrel = new Squirrel("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

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
        Assert.assertEquals(squirrel.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(squirrel.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(squirrel.getFoodType(), Bugs.class);
        squirrel.setFoodType(Meat.class);
        Assert.assertNotEquals(squirrel.getFoodType(), Bugs.class);
        Assert.assertEquals(squirrel.getFoodType(), Meat.class);
    }
}
