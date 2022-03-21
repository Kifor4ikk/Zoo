import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Lion;
import com.epam.rd.tasks.zoo.food.Meat;

import java.util.Collections;

public class Test {

    @org.testng.annotations.Test
    public void AnimalTest(){
        Lion lion = new Lion("Alexey","Lion",12, Field.class, Collections.singletonList(ClimateZone.SUBTROPICAL), Meat.class);
        lion.roar();
    }
}
