package zooTest;

import com.epam.rd.tasks.zoo.Zoo;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Aquarium;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animals.bird.penguin.EmperorPenguin;
import com.epam.rd.tasks.zoo.animals.bird.penguin.RoyalPenguin;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Lion;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Chameleon;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Python;
import com.epam.rd.tasks.zoo.finance.expenses.Salary;
import com.epam.rd.tasks.zoo.finance.income.Ticket;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import com.epam.rd.tasks.zoo.food.Wheat;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Test
public class ZooTest {

    Zoo zoo = new Zoo("AnimalPlanet", "Best zoo in whole cyberworld!", "vk.com/kifor4ik", BigDecimal.ZERO,
            "CyberCountry", "CyberCity", "CyberStreet", "1488A");

    Bullfinch bullfinch = new Bullfinch("Snegir", "Norm takoi", 1, Set.of(Field.class),
            Set.of(ClimateZone.MODERATE, ClimateZone.TROPICAL), Set.of(Wheat.class), "Wings", "Red");
    Bullfinch bullfinch2 = new Bullfinch("Snegir2", "Norm takoi2", 2, Set.of(Field.class),
            Set.of(ClimateZone.MODERATE, ClimateZone.TROPICAL), Set.of(Wheat.class), "Wings", "Red");

    Lion lion = new Lion("Lion", "Norm takoi", 4, Set.of(Field.class),
            Set.of(ClimateZone.TROPICAL, ClimateZone.SUBTROPICAL), Set.of(Meat.class), "Tail");

    EmperorPenguin emperorPenguin = new EmperorPenguin("Emperor Penguin", "Norm takoi", 2, Set.of(Aquarium.class),
            Set.of(ClimateZone.ARCTIC, ClimateZone.SUBANTARCTIC, ClimateZone.ANTARCTIC), Set.of(Wheat.class), "Wings");

    Chameleon chameleon = new Chameleon("Chameleon", "Norm takoi", 2, Set.of(Aquarium.class),
            Set.of(ClimateZone.TROPICAL, ClimateZone.SUBEQUATORIAL), Set.of(Bugs.class));

    Field field = new Field(1L, "Savanna field", 128, List.of(Lion.class), ClimateZone.TROPICAL);
    Field field2 = new Field(2L, "Forest field", 128, List.of(Bullfinch.class), ClimateZone.MODERATE);
    Aquarium aquarium = new Aquarium(3L, "Acrtic aquarium", 128, List.of(EmperorPenguin.class, RoyalPenguin.class), ClimateZone.ARCTIC);
    Terrarium terrarium = new Terrarium(4L, "Terrarium", 128, List.of(Chameleon.class, Python.class), ClimateZone.TROPICAL);
    //finance
    Salary salary = new Salary("Salary for workers", LocalDateTime.now(), BigDecimal.valueOf(10));
    Ticket ticket = new Ticket("Ticket", LocalDateTime.now(), BigDecimal.valueOf(12));

    @BeforeTest
    public void setIdForAllEntities() {
        zoo.setId(1L);
        //animals
        bullfinch.setId(1L);
        bullfinch2.setId(2L);
        lion.setId(3L);
        emperorPenguin.setId(4L);
        chameleon.setId(5L);
    }

    public void idTest() {
        Assert.assertEquals(zoo.getId(), 1);
    }

    public void nameTest() {
        Assert.assertEquals(zoo.getName(), "AnimalPlanet");
        zoo.setName("Animal Planet");
        Assert.assertNotEquals(zoo.getName(), "AnimalPlanet");
        Assert.assertEquals(zoo.getName(), "Animal Planet");
    }

    public void describeTest() {
        Assert.assertEquals(zoo.getDescribe(), "Best zoo in whole cyberworld!");
        zoo.setDescribe("Best zoo in whole cyber world!");
        Assert.assertNotEquals(zoo.getDescribe(), "Best zoo in whole cyberworld!");
        Assert.assertEquals(zoo.getDescribe(), "Best zoo in whole cyber world!");
    }

    public void testContactInfo() {
        Assert.assertEquals(zoo.getContactInfo(), "vk.com/kifor4ik");
        zoo.setContactInfo("vk.com/kifor4ik | tg.me/kifor4ik");
        Assert.assertNotEquals(zoo.getContactInfo(), "vk.com/kifor4ik");
        Assert.assertEquals(zoo.getContactInfo(), "vk.com/kifor4ik | tg.me/kifor4ik");
    }

    public void budgetTest() {
        Assert.assertEquals(zoo.getBudget(), BigDecimal.ZERO);
        zoo.setBudget(BigDecimal.TEN);
        Assert.assertNotEquals(zoo.getBudget(), BigDecimal.ZERO);
        Assert.assertEquals(zoo.getBudget(), BigDecimal.TEN);
    }

    public void addressTest() {
        Assert.assertEquals(zoo.getAddress().getCountry(), "CyberCountry");
        zoo.getAddress().setCountry("Cyber Country 12");
        Assert.assertNotEquals(zoo.getAddress().getCountry(), "CyberCountry");
        Assert.assertEquals(zoo.getAddress().getCountry(), "Cyber Country 12");
        // ---
        Assert.assertEquals(zoo.getAddress().getCity(), "CyberCity");
        zoo.getAddress().setCity("Cyber City 12");
        Assert.assertNotEquals(zoo.getAddress().getCity(), "CyberCity");
        Assert.assertEquals(zoo.getAddress().getCity(), "Cyber City 12");
        // ---
        Assert.assertEquals(zoo.getAddress().getStreet(), "CyberStreet");
        zoo.getAddress().setStreet("Cyber Street 12");
        Assert.assertNotEquals(zoo.getAddress().getStreet(), "CyberStreet");
        Assert.assertEquals(zoo.getAddress().getStreet(), "Cyber Street 12");
        // ---
        Assert.assertEquals(zoo.getAddress().getHouseNumber(), "1488A");
        zoo.getAddress().setHouseNumber("1487B");
        Assert.assertNotEquals(zoo.getAddress().getHouseNumber(), "1488A");
        Assert.assertEquals(zoo.getAddress().getHouseNumber(), "1487B");
    }

    public void animalHouseTest() {
        zoo.getAnimalHouses().add(aquarium);
        zoo.getAnimalHouses().add(aquarium);
        zoo.getAnimalHouses().add(terrarium);
        Assert.assertEquals(zoo.getAnimalHouses().size(), 3);
        Assert.assertEquals(zoo.getAnimalHouses().get(0), aquarium);
        Assert.assertEquals(zoo.getAnimalHouses().get(1), aquarium);
        Assert.assertEquals(zoo.getAnimalHouses().get(2), terrarium);
    }

    public void foodTest() {
        zoo.getFoodCount().put(Wheat.class, 10);
        zoo.getFoodCount().put(Meat.class, 0);
        Assert.assertEquals(zoo.getFoodCount().get(Wheat.class), 10);
        Assert.assertEquals(zoo.getFoodCount().get(Meat.class), 0);
    }

    public void financeTest(){
        zoo.getFinance().add(salary);
        zoo.getFinance().add(ticket);
        Assert.assertTrue(zoo.getFinance().contains(ticket));
        Assert.assertTrue(zoo.getFinance().contains(salary));

    }

}
