package animalTest.reptile.scaled;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Chameleon;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Python;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class PythonTest {
    Python python = new Python("Phyt","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(python.getName(), "Phyt");
        python.setName("TempNameForAnimal");
        Assert.assertNotEquals(python.getName(), "Phyt");
        Assert.assertEquals(python.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(python.getDescribe(), "TestDescribe12");
        python.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(python.getDescribe(), "TestDescribe12");
        Assert.assertEquals(python.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(python.getAge(), 1);
        python.setAge(-1);
        python.setAge(2);
        Assert.assertEquals(python.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(python.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(python.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(python.getFoodType(), Bugs.class);
        python.setFoodType(Meat.class);
        Assert.assertNotEquals(python.getFoodType(), Bugs.class);
        Assert.assertEquals(python.getFoodType(), Meat.class);
    }
}
