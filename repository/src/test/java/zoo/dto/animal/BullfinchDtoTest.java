package zoo.dto.animal;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.dto.animal.AnimalDto;
import com.epam.rd.tasks.zoo.dto.animal.BullFinchDto;
import com.epam.rd.tasks.zoo.dto.animal.CrabDto;
import com.epam.rd.tasks.zoo.food.Bugs;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

@Test
public class BullfinchDtoTest {

    Bullfinch bullfinch = new Bullfinch("AnimalName","TestDescribe12",1, Terrarium.class,
            List.of(ClimateZone.TROPICAL,ClimateZone.MODERATE), Bugs.class);

    public void DTO() throws ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {

        Assert.assertEquals(BullFinchDto.fromDTO(AnimalDto.toDTO(bullfinch)), bullfinch);
    }
}
