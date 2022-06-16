package animalHouseTest;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Aquarium;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.fish.carp.Bream;
import com.epam.rd.tasks.zoo.animals.fish.carp.Vobla;
import com.epam.rd.tasks.zoo.animals.fish.sturgeon.Beluga;
import com.epam.rd.tasks.zoo.animals.fish.sturgeon.Sterlet;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Chameleon;
import com.epam.rd.tasks.zoo.animals.reptile.scaled.Python;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.exception.BadClimateException;
import com.epam.rd.tasks.zoo.food.Wheat;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Test
public class TerrariumTest {
    Terrarium terrarium = new Terrarium(1L, "Terrarium", 4, List.of(Chameleon.class), ClimateZone.TROPICAL);
    Chameleon chameleon = new Chameleon("Cham","Test",12,Set.of(Terrarium.class), Set.of(ClimateZone.TROPICAL), Set.of(Wheat.class));
    Chameleon chameleon2 = new Chameleon("Cham2","Test2",1, Set.of(Terrarium.class), Set.of(ClimateZone.TROPICAL), Set.of(Wheat.class));

    @BeforeTest
    public void beforeTest(){
        //Устанавливаем рыбам ID потому как их нужно вроде из БД достать, а там в БД им самим ID выдаст, пока так.
        chameleon.setId(1L);
        chameleon2.setId(2L);
        //пускаем в аквариум
        terrarium.addAnimal(chameleon);
        terrarium.addAnimal(chameleon2);
    }
    public void nameTest(){
        Assert.assertEquals(terrarium.getName(), "Terrarium");
        terrarium.setName("newTerrarium");
        Assert.assertNotEquals(terrarium.getName(), "Terrarium");
        Assert.assertEquals(terrarium.getName(), "newTerrarium");
    }

    public void areaTest(){
        Assert.assertEquals(terrarium.getArea(),4);
        terrarium.setArea(8);
        Assert.assertNotEquals((int) terrarium.getArea(), 4);
        Assert.assertEquals(terrarium.getArea(),8);
    }

    @Test(expectedExceptions = BadAnimalTypeException.class)
    public void typeOfAnimalTest(){
        //Проверка на совпадение начальных данных
        Assert.assertEquals(terrarium.getTypeOfAnimal(),List.of(Chameleon.class));
        //Пытаемся поменять типы обитаемых хамелионов на питонов но это не сработает!
        //и кинет ошибку BadAnimalTypeException
        terrarium.setTypeOfAnimal(List.of(Python.class));
        //Проверяем, не прошло ли случайно
        Assert.assertNotEquals(terrarium.getTypeOfAnimal(), List.of(Python.class));
        //Тут сработает т.к. не нарушает жизнь рыбов.
        terrarium.setTypeOfAnimal(List.of(Python.class));
        //Проверяем сработало ли оно (должно было)
        Assert.assertEquals(terrarium.getTypeOfAnimal(), List.of(Python.class));
    }

    @Test(expectedExceptions = BadClimateException.class)
    public void climateZoneTest(){
        //Проверка начальных данных.
        Assert.assertEquals(terrarium.getClimateZone(),ClimateZone.TROPICAL);
        //Пытаемся установить хамелам плохой климат
        terrarium.setClimateZone(ClimateZone.SUBTROPICAL);
        //Проверяем что он не поставился
        Assert.assertNotEquals(terrarium.getClimateZone(), ClimateZone.SUBTROPICAL);
        //Ставим хамелам хороший климат(который и стоит)
        terrarium.setClimateZone(ClimateZone.TROPICAL);
        //Проверяем сходство
        Assert.assertEquals(terrarium.getClimateZone(),ClimateZone.TROPICAL);
        //Убираем хамелов
        terrarium.removeAnimal(1L);
        terrarium.removeAnimal(2L);
        //Меняем на тропический климат
        terrarium.setClimateZone(ClimateZone.SUBTROPICAL);
        //Чекаем тропики, ВУАЛЯ!
        Assert.assertEquals(terrarium.getClimateZone(),ClimateZone.SUBTROPICAL);
    }

    @AfterTest
    public void message(){
        System.out.println("Хамеллионов в аквариуме - " + terrarium.getAnimals().size());
        System.out.println("В ходе тестов ни один хамеллион не пострадал!\nDuring the tests, not a single chameleon was harmed!");
    }
}
