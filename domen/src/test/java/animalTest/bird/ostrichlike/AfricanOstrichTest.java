package animalTest.bird.ostrichlike;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.bird.ostrichlike.AfricanOstrich;
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
public class AfricanOstrichTest {
    AfricanOstrich ostrich = new AfricanOstrich("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class),"Test");

    public void nameTest(){
        Assert.assertEquals(ostrich.getName(), "AnimalName");
        ostrich.setName("TempNameForAnimal");
        Assert.assertNotEquals(ostrich.getName(), "AnimalName");
        Assert.assertEquals(ostrich.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(ostrich.getDescribe(), "TestDescribe12");
        ostrich.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(ostrich.getDescribe(), "TestDescribe12");
        Assert.assertEquals(ostrich.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(ostrich.getAge(), 1);
        ostrich.setAge(-1);
        ostrich.setAge(2);
        Assert.assertEquals(ostrich.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(ostrich.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(ostrich.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(ostrich.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        ostrich.setFoodType(food);
        Assert.assertNotEquals(ostrich.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(ostrich.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
