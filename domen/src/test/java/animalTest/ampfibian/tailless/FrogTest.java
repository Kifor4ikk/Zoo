package animalTest.ampfibian.tailless;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animal.amphibian.tailless.Frog;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Food;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

@Test
public class FrogTest {
    Frog frog = new Frog("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class));

    public void nameTest(){
        Assert.assertEquals(frog.getName(), "AnimalName");
        frog.setName("TempNameForAnimal");
        Assert.assertNotEquals(frog.getName(), "AnimalName");
        Assert.assertEquals(frog.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(frog.getDescribe(), "TestDescribe12");
        frog.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(frog.getDescribe(), "TestDescribe12");
        Assert.assertEquals(frog.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(frog.getAge(), 1);
        frog.setAge(-1);
        frog.setAge(2);
        Assert.assertEquals(frog.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(frog.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(frog.getClimateZone(), Set.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(frog.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        frog.setFoodType(food);
        Assert.assertNotEquals(frog.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(frog.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
