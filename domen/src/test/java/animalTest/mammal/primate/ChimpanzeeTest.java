package animalTest.mammal.primate;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.mammal.primate.Chimpanzee;
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
public class ChimpanzeeTest {
    Chimpanzee chimpanzee = new Chimpanzee("AnimalName","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class), "Tail");

    public void nameTest(){
        Assert.assertEquals(chimpanzee.getName(), "AnimalName");
        chimpanzee.setName("TempNameForAnimal");
        Assert.assertNotEquals(chimpanzee.getName(), "AnimalName");
        Assert.assertEquals(chimpanzee.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(chimpanzee.getDescribe(), "TestDescribe12");
        chimpanzee.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(chimpanzee.getDescribe(), "TestDescribe12");
        Assert.assertEquals(chimpanzee.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(chimpanzee.getAge(), 1);
        chimpanzee.setAge(-1);
        chimpanzee.setAge(2);
        Assert.assertEquals(chimpanzee.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(chimpanzee.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(chimpanzee.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(chimpanzee.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        chimpanzee.setFoodType(food);
        Assert.assertNotEquals(chimpanzee.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(chimpanzee.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
