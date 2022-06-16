package animalTest.bird.finche;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
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
public class BullfinchTest {
    Bullfinch bullfinch = new Bullfinch("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class));

    public void nameTest(){
        Assert.assertEquals(bullfinch.getName(), "AnimalName");
        bullfinch.setName("TempNameForAnimal");
        Assert.assertNotEquals(bullfinch.getName(), "AnimalName");
        Assert.assertEquals(bullfinch.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(bullfinch.getDescribe(), "TestDescribe12");
        bullfinch.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(bullfinch.getDescribe(), "TestDescribe12");
        Assert.assertEquals(bullfinch.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(bullfinch.getAge(), 1);
        bullfinch.setAge(-1);
        bullfinch.setAge(2);
        Assert.assertEquals(bullfinch.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(bullfinch.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(bullfinch.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(bullfinch.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        bullfinch.setFoodType(food);
        Assert.assertNotEquals(bullfinch.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(bullfinch.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
