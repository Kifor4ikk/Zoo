package animalHouseTest;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Aquarium;
import com.epam.rd.tasks.zoo.animals.fish.carp.Bream;
import com.epam.rd.tasks.zoo.animals.fish.carp.Vobla;
import com.epam.rd.tasks.zoo.animals.fish.sturgeon.Beluga;
import com.epam.rd.tasks.zoo.animals.fish.sturgeon.Sterlet;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.exception.BadClimateException;
import com.epam.rd.tasks.zoo.food.Meat;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Test
public class AquariumTest {

    Aquarium aquarium = new Aquarium(1L,"Aqualand",4, List.of(Beluga.class, Sterlet.class, Bream.class), ClimateZone.MODERATE);
    Bream bream = new Bream("oleg","norm",2,Aquarium.class, Collections.singletonList(ClimateZone.MODERATE), Meat.class);
    Sterlet sterlet = new Sterlet("Sterlyad","2",3,Aquarium.class, Collections.singletonList(ClimateZone.MODERATE), Meat.class);

    @BeforeTest
    public void beforeTest(){
        //Устанавливаем рыбам ID потому как их нужно вроде из БД достать, а там в БД им самим ID выдаст, пока так.
        bream.setId(1L);
        sterlet.setId(2L);
        //пускаем в аквариум
        aquarium.addAnimal(bream);
        aquarium.addAnimal(sterlet);
    }
    public void nameTest(){
        Assert.assertEquals(aquarium.getName(), "Aqualand");
        aquarium.setName("newAquaLand");
        Assert.assertNotEquals(aquarium.getName(), "Aqualand");
        Assert.assertEquals(aquarium.getName(), "newAquaLand");
    }

    public void areaTest(){
        Assert.assertEquals(aquarium.getArea(),4);
        aquarium.setArea(8);
        Assert.assertNotEquals((int) aquarium.getArea(), 4);
        Assert.assertEquals(aquarium.getArea(),8);
    }

    //@TODO поменять на Ассерт жду эксепшн
    @Test(expectedExceptions = BadAnimalTypeException.class)
    public void typeOfAnimalTest(){
        //Проверка на совпадение начальных данных
        Assert.assertEquals(aquarium.getTypeOfAnimal(),List.of(Beluga.class, Sterlet.class, Bream.class));
        //Пытаемся поменять типы обитаемых рыб на белугу и стерлядь, но у нас живет ещё Лещ, поэтому это не сработает!
        //и кинет ошибку BadAnimalTypeException
        aquarium.setTypeOfAnimal(List.of(Beluga.class, Sterlet.class));
        //Проверяем, не прошло ли случайно
        Assert.assertNotEquals(aquarium.getTypeOfAnimal(), List.of(Beluga.class, Sterlet.class));
        //Тут сработает т.к. не нарушает жизнь рыбов.
        aquarium.setTypeOfAnimal(List.of(Sterlet.class, Bream.class, Vobla.class));
        //Проверяем сработало ли оно (должно было)
        Assert.assertEquals(aquarium.getTypeOfAnimal(), List.of(Sterlet.class, Vobla.class,Bream.class));
    }

    //@TODO поменять на Ассерт жду эксепшн
    @Test(expectedExceptions = BadClimateException.class)
    public void climateZoneTest(){
        //Проверка начальных данных.
        Assert.assertEquals(aquarium.getClimateZone(),ClimateZone.MODERATE);
        //Пытаемся установить рыбам плохой климат
        aquarium.setClimateZone(ClimateZone.SUBTROPICAL);
        //Проверяем что он не поставился
        Assert.assertNotEquals(aquarium.getClimateZone(), ClimateZone.SUBTROPICAL);
        //Ставим рыбам хороший климат(который и стоит)
        aquarium.setClimateZone(ClimateZone.MODERATE);
        //Проверяем сходство
        Assert.assertEquals(aquarium.getClimateZone(),ClimateZone.MODERATE);
        //Убираем рыбов
        aquarium.removeAnimal(1L);
        aquarium.removeAnimal(2L);
        //Меняем на тропический климат
        aquarium.setClimateZone(ClimateZone.SUBTROPICAL);
        //Чекаем тропики, ВУАЛЯ!
        Assert.assertEquals(aquarium.getClimateZone(),ClimateZone.SUBTROPICAL);
    }

    @AfterTest
    public void message(){
        System.out.println("Рыбов в аквариуме - " + aquarium.getAnimals().size());
        System.out.println("В ходе тестов ни одна рыба не пострадала!\nDuring the tests, not a single fish was harmed!");
    }
}
