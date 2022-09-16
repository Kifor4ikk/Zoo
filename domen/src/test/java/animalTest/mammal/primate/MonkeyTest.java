package animalTest.mammal.primate;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.mammal.primate.Monkey;
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
public class MonkeyTest {
    Monkey monkey = new Monkey("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL),Set.of(Bugs.class), "Tail");

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
        Assert.assertEquals(monkey.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(monkey.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(monkey.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        monkey.setFoodType(food);
        Assert.assertNotEquals(monkey.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(monkey.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
