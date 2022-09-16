package animalTest.bird.anseriforme;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.bird.anseriforme.EiderDuck;
import com.epam.rd.tasks.zoo.animals.bird.pigeonlike.Vyakhir;
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
public class EiderDuckTest {
    EiderDuck eitherduck = new EiderDuck("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class), "Wings");

    public void nameTest(){
        Assert.assertEquals(eitherduck.getName(), "AnimalName");
        eitherduck.setName("TempNameForAnimal");
        Assert.assertNotEquals(eitherduck.getName(), "AnimalName");
        Assert.assertEquals(eitherduck.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(eitherduck.getDescribe(), "TestDescribe12");
        eitherduck.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(eitherduck.getDescribe(), "TestDescribe12");
        Assert.assertEquals(eitherduck.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(eitherduck.getAge(), 1);
        eitherduck.setAge(-1);
        eitherduck.setAge(2);
        Assert.assertEquals(eitherduck.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(eitherduck.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(eitherduck.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(eitherduck.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        eitherduck.setFoodType(food);
        Assert.assertNotEquals(eitherduck.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(eitherduck.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
