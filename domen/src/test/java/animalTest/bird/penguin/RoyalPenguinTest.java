package animalTest.bird.penguin;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.bird.penguin.RoyalPenguin;
import com.epam.rd.tasks.zoo.animals.bird.pigeonlike.Vyakhir;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Test
public class RoyalPenguinTest {
    RoyalPenguin royalpenguin = new RoyalPenguin("AnimalName","TestDescribe12",1, Terrarium.class,
            Collections.singletonList(ClimateZone.TROPICAL), Bugs.class);

    public void nameTest(){
        Assert.assertEquals(royalpenguin.getName(), "AnimalName");
        royalpenguin.setName("TempNameForAnimal");
        Assert.assertNotEquals(royalpenguin.getName(), "AnimalName");
        Assert.assertEquals(royalpenguin.getName(), "TempNameForAnimal");
    }

    public void describeTest(){
        Assert.assertEquals(royalpenguin.getDescribe(), "TestDescribe12");
        royalpenguin.setDescribe("TempDescribeForAnimal");
        Assert.assertNotEquals(royalpenguin.getDescribe(), "TestDescribe12");
        Assert.assertEquals(royalpenguin.getDescribe(), "TempDescribeForAnimal");
    }

    @Test(expectedExceptions = WrongAgeException.class)
    public void ageTest(){
        Assert.assertEquals(royalpenguin.getAge(), 1);
        royalpenguin.setAge(-1);
        royalpenguin.setAge(2);
        Assert.assertEquals(royalpenguin.getAge(), 2);
    }

    public void livingZoneTest(){
        Assert.assertEquals(royalpenguin.getLivingZone(), Terrarium.class);
    }

    public void climateZoneTest(){
        Assert.assertEquals(royalpenguin.getClimateZone(), List.of(ClimateZone.TROPICAL));
    }

    public void foodTest(){
        Assert.assertEquals(royalpenguin.getFoodType(), Bugs.class);
        royalpenguin.setFoodType(Meat.class);
        Assert.assertNotEquals(royalpenguin.getFoodType(), Bugs.class);
        Assert.assertEquals(royalpenguin.getFoodType(), Meat.class);
    }
}
