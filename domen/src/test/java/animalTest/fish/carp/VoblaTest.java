package animalTest.fish.carp;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.fish.carp.Bream;
import com.epam.rd.tasks.zoo.animals.fish.carp.Vobla;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class VoblaTest {
    Vobla vobla = new Vobla("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

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
        Assert.assertEquals(vobla.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(vobla.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(vobla.getFoodType(), Bugs.class);
        vobla.setFoodType(Meat.class);
        Assert.assertNotEquals(vobla.getFoodType(), Bugs.class);
        Assert.assertEquals(vobla.getFoodType(), Meat.class);
    }
}
