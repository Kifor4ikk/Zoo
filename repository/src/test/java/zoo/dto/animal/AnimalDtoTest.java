package zoo.dto.animal;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.dto.animal.AnimalDto;
import com.epam.rd.tasks.zoo.dto.animal.crustacean.CrustaceanDto;
import com.epam.rd.tasks.zoo.food.Bugs;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class AnimalDtoTest {
    Crab crab = new Crab("AnimalName","TestDescribe12",1, Terrarium.class,
            List.of(ClimateZone.TROPICAL, ClimateZone.SUBARCTIC,ClimateZone.ANTARCTIC), Bugs.class,"Red chitin");

    public void animalDtoTest() throws ClassNotFoundException {
        Assert.assertEquals(crab,(AnimalDto.fromDto(AnimalDto.toDTO(crab))));
        Assert.assertEquals(AnimalDto.class, AnimalDto.toDTO(crab).getClass());
    }
}
