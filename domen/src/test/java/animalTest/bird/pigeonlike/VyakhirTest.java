package animalTest.bird.pigeonlike;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.bird.pigeonlike.Vyakhir;
import com.epam.rd.tasks.zoo.animals.fish.carp.Bream;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class VyakhirTest {
    Vyakhir vyakhir = new Vyakhir("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(vyakhir.getName(), "AnimalName");
        vyakhir.setName("TempNameForAnimal");
        Assert.assertNotEquals(vyakhir.getName(), "AnimalName");
        Assert.assertEquals(vyakhir.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(vyakhir.getDescribe(), "TestDescribe12");
        vyakhir.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(vyakhir.getDescribe(), "TestDescribe12");
        Assert.assertEquals(vyakhir.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(vyakhir.getAge(), 1);
        vyakhir.setAge(-1);
        vyakhir.setAge(2);
        Assert.assertEquals(vyakhir.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(vyakhir.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(vyakhir.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(vyakhir.getFoodType(), Bugs.class);
        vyakhir.setFoodType(Meat.class);
        Assert.assertNotEquals(vyakhir.getFoodType(), Bugs.class);
        Assert.assertEquals(vyakhir.getFoodType(), Meat.class);
    }
}
