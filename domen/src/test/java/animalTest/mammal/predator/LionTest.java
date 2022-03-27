package animalTest.mammal.predator;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Lion;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class LionTest {
    Lion lion = new Lion("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(lion.getName(), "AnimalName");
        lion.setName("TempNameForAnimal");
        Assert.assertNotEquals(lion.getName(), "AnimalName");
        Assert.assertEquals(lion.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(lion.getDescribe(), "TestDescribe12");
        lion.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(lion.getDescribe(), "TestDescribe12");
        Assert.assertEquals(lion.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(lion.getAge(), 1);
        lion.setAge(-1);
        lion.setAge(2);
        Assert.assertEquals(lion.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(lion.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(lion.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(lion.getFoodType(), Bugs.class);
        lion.setFoodType(Meat.class);
        Assert.assertNotEquals(lion.getFoodType(), Bugs.class);
        Assert.assertEquals(lion.getFoodType(), Meat.class);
    }
}
