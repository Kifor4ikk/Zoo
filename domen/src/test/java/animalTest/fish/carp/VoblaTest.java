package animalTest.fish.carp;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.fish.carp.Vobla;
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
public class VoblaTest {
    Vobla vobla = new Vobla("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class));

    public void nameTest(){
        Assert.assertEquals(vobla.getName(), "AnimalName");
        vobla.setName("TempNameForAnimal");
        Assert.assertNotEquals(vobla.getName(), "AnimalName");
        Assert.assertEquals(vobla.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(vobla.getDescribe(), "TestDescribe12");
        vobla.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(vobla.getDescribe(), "TestDescribe12");
        Assert.assertEquals(vobla.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(vobla.getAge(), 1);
        vobla.setAge(-1);
        vobla.setAge(2);
        Assert.assertEquals(vobla.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(vobla.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(vobla.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(vobla.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        vobla.setFoodType(food);
        Assert.assertNotEquals(vobla.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(vobla.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
