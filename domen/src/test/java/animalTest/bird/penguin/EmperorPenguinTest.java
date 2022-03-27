package animalTest.bird.penguin;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.bird.penguin.EmperorPenguin;
import com.epam.rd.tasks.zoo.animals.bird.pigeonlike.Vyakhir;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class EmperorPenguinTest {
    EmperorPenguin emperorpenguin = new EmperorPenguin("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(emperorpenguin.getName(), "AnimalName");
        emperorpenguin.setName("TempNameForAnimal");
        Assert.assertNotEquals(emperorpenguin.getName(), "AnimalName");
        Assert.assertEquals(emperorpenguin.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(emperorpenguin.getDescribe(), "TestDescribe12");
        emperorpenguin.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(emperorpenguin.getDescribe(), "TestDescribe12");
        Assert.assertEquals(emperorpenguin.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(emperorpenguin.getAge(), 1);
        emperorpenguin.setAge(-1);
        emperorpenguin.setAge(2);
        Assert.assertEquals(emperorpenguin.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(emperorpenguin.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(emperorpenguin.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(emperorpenguin.getFoodType(), Bugs.class);
        emperorpenguin.setFoodType(Meat.class);
        Assert.assertNotEquals(emperorpenguin.getFoodType(), Bugs.class);
        Assert.assertEquals(emperorpenguin.getFoodType(), Meat.class);
    }
}
