package animalTest.reptile.scaled;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animal.reptile.scaled.Chameleon;
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
public class ChameleonTest {

    Chameleon chameleon = new Chameleon("Cham","TestDescribe12",1, Set.of(Terrarium.class),
            Set.of(ClimateZone.TROPICAL), Set.of(Bugs.class));

    public void nameTest(){
        Assert.assertEquals(chameleon.getName(), "Cham");
        chameleon.setName("TempNameForAnimal");
        Assert.assertNotEquals(chameleon.getName(), "Cham");
        Assert.assertEquals(chameleon.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(chameleon.getDescribe(), "TestDescribe12");
        chameleon.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(chameleon.getDescribe(), "TestDescribe12");
        Assert.assertEquals(chameleon.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(chameleon.getAge(), 1);
        chameleon.setAge(-1);
        chameleon.setAge(2);
        Assert.assertEquals(chameleon.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(chameleon.getLivingZone(), Set.of(Terrarium.class));
    }

    public void climateZoneTest(){
        Assert.assertEquals(chameleon.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(chameleon.getFoodType(), Set.of(Bugs.class));
        Set<Class<? extends Food>> food = new HashSet<>();
        food.addAll(Set.of(Meat.class,Bugs.class));
        chameleon.setFoodType(food);
        Assert.assertNotEquals(chameleon.getFoodType(), Set.of(Bugs.class));
        Assert.assertEquals(chameleon.getFoodType(), Set.of(Meat.class, Bugs.class));
    }
}
