package animalTest.mammal.predator;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf;
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
public class WolfTest {
    Wolf wolf = new Wolf("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class), "Tail");

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
        Assert.assertEquals(wolf.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(wolf.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(wolf.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        wolf.setFoodType(food);
        Assert.assertNotEquals(wolf.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(wolf.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
