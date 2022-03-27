package animalTest.bird.anseriforme;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.bird.anseriforme.Gogol;
import com.epam.rd.tasks.zoo.animals.bird.pigeonlike.Vyakhir;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class GogolTest {
    Gogol Gogol = new Gogol("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(Gogol.getName(), "AnimalName");
        Gogol.setName("TempNameForAnimal");
        Assert.assertNotEquals(Gogol.getName(), "AnimalName");
        Assert.assertEquals(Gogol.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(Gogol.getDescribe(), "TestDescribe12");
        Gogol.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(Gogol.getDescribe(), "TestDescribe12");
        Assert.assertEquals(Gogol.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(Gogol.getAge(), 1);
        Gogol.setAge(-1);
        Gogol.setAge(2);
        Assert.assertEquals(Gogol.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(Gogol.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(Gogol.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(Gogol.getFoodType(), Bugs.class);
        Gogol.setFoodType(Meat.class);
        Assert.assertNotEquals(Gogol.getFoodType(), Bugs.class);
        Assert.assertEquals(Gogol.getFoodType(), Meat.class);
    }
}
