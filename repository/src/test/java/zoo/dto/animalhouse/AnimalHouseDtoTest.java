package zoo.dto.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Lion;
import com.epam.rd.tasks.zoo.dto.animalhouse.AnimalHouseDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class AnimalHouseDtoTest {

    Field field = new Field(1L, "Fields",2, List.of(Lion.class, Bullfinch.class), ClimateZone.MODERATE);

    public void dtoTest() throws ClassNotFoundException {
        System.out.println(field);
        System.out.println(AnimalHouseDto.toDto(field));
        System.out.println(AnimalHouseDto.fromDto(AnimalHouseDto.toDto(field)));
        Assert.assertEquals(field, AnimalHouseDto.fromDto(AnimalHouseDto.toDto(field)));
    }
}
