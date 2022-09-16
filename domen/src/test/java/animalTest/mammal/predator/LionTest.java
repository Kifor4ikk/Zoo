package animalTest.mammal.predator;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Lion;
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

public class LionTest {
    Lion lion = new Lion("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class), "Tail");

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
        Assert.assertEquals(lion.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(lion.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(lion.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        lion.setFoodType(food);
        Assert.assertNotEquals(lion.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(lion.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
