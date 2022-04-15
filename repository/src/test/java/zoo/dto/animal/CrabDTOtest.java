package zoo.dto.animal;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.dto.animal.AnimalDto;
import com.epam.rd.tasks.zoo.dto.animal.CrabDto;
import com.epam.rd.tasks.zoo.food.Bugs;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Test
public class CrabDTOtest {
    Crab crab = new Crab("AnimalName","TestDescribe12",1, Terrarium.class,
            List.of(ClimateZone.TROPICAL, ClimateZone.SUBARCTIC), Bugs.class);

    public void DTO() throws ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {

        Assert.assertEquals(CrabDto.fromDTO(AnimalDto.toDTO(crab)), crab);
    }
}
