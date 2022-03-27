package animalTest.mammal.primate;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.mammal.primate.Monkey;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class MonkeyTest {
    Monkey monkey = new Monkey("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(monkey.getName(), "AnimalName");
        monkey.setName("TempNameForAnimal");
        Assert.assertNotEquals(monkey.getName(), "AnimalName");
        Assert.assertEquals(monkey.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(monkey.getDescribe(), "TestDescribe12");
        monkey.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(monkey.getDescribe(), "TestDescribe12");
        Assert.assertEquals(monkey.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(monkey.getAge(), 1);
        monkey.setAge(-1);
        monkey.setAge(2);
        Assert.assertEquals(monkey.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(monkey.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(monkey.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(monkey.getFoodType(), Bugs.class);
        monkey.setFoodType(Meat.class);
        Assert.assertNotEquals(monkey.getFoodType(), Bugs.class);
        Assert.assertEquals(monkey.getFoodType(), Meat.class);
    }
}
