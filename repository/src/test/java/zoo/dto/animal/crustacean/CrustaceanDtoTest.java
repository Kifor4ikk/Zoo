package zoo.dto.animal.crustacean;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.dto.animal.crustacean.CrustaceanDto;
import com.epam.rd.tasks.zoo.food.Bugs;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class CrustaceanDtoTest {

    public void dto() throws ClassNotFoundException {
        Crab crab = new Crab("AnimalName","TestDescribe12",1, Terrarium.class,
                List.of(ClimateZone.TROPICAL, ClimateZone.SUBARCTIC,ClimateZone.ANTARCTIC), Bugs.class,"Red chitin");
        Assert.assertEquals(crab,(CrustaceanDto.fromDto(CrustaceanDto.toDTO(crab))));
        Assert.assertEquals(CrustaceanDto.class, CrustaceanDto.toDTO(crab).getClass());
    }
}
