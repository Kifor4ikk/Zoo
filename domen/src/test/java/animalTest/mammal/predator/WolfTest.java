package animalTest.mammal.predator;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class WolfTest {
    Wolf wolf = new Wolf("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(wolf.getName(), "AnimalName");
        wolf.setName("TempNameForAnimal");
        Assert.assertNotEquals(wolf.getName(), "AnimalName");
        Assert.assertEquals(wolf.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(wolf.getDescribe(), "TestDescribe12");
        wolf.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(wolf.getDescribe(), "TestDescribe12");
        Assert.assertEquals(wolf.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(wolf.getAge(), 1);
        wolf.setAge(-1);
        wolf.setAge(2);
        Assert.assertEquals(wolf.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(wolf.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(wolf.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(wolf.getFoodType(), Bugs.class);
        wolf.setFoodType(Meat.class);
        Assert.assertNotEquals(wolf.getFoodType(), Bugs.class);
        Assert.assertEquals(wolf.getFoodType(), Meat.class);
    }
}
