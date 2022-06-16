package animalHouseTest;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.mammal.proboscis.Elephant;
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
public class FieldTest {
    Field field = new Field(1L, "Field for Eleph", 4, List.of(Elephant.class), ClimateZone.TROPICAL);
    Elephant elephant = new Elephant("Elephant","Test",12, Set.of(Field.class), Set.of(ClimateZone.TROPICAL), Set.of(Wheat.class));
    Elephant elephant1 = new Elephant("Elephant2","Test2",1, Set.of(Field.class), Set.of(ClimateZone.TROPICAL), Set.of(Wheat.class));

    @BeforeTest
    public void beforeTest(){
        //Устанавливаем рыбам ID потому как их нужно вроде из БД достать, а там в БД им самим ID выдаст, пока так.
        elephant.setId(1L);
        elephant1.setId(2L);
        //пускаем в аквариум
        field.addAnimal(elephant);
        field.addAnimal(elephant1);
    }
    public void nameTest(){
        Assert.assertEquals(field.getName(), "Field for Eleph");
        field.setName("newField for Eleph");
        Assert.assertNotEquals(field.getName(), "Field for Eleph");
        Assert.assertEquals(field.getName(), "newField for Eleph");
    }

    public void areaTest(){
        Assert.assertEquals(field.getArea(),4);
        field.setArea(8);
        Assert.assertNotEquals((int) field.getArea(), 4);
        Assert.assertEquals(field.getArea(),8);
    }

    @Test(expectedExceptions = BadAnimalTypeException.class)
    public void typeOfAnimalTest(){
        //Проверка на совпадение начальных данных
        Assert.assertEquals(field.getTypeOfAnimal(),List.of(Elephant.class));
        //Пытаемся поменять типы обитаемых хамелионов на питонов но это не сработает!
        //и кинет ошибку BadAnimalTypeException
        field.setTypeOfAnimal(List.of(Python.class));
        //Проверяем, не прошло ли случайно
        Assert.assertNotEquals(field.getTypeOfAnimal(), List.of(Python.class));
        //Тут сработает т.к. не нарушает жизнь слонов.
        field.setTypeOfAnimal(List.of(Elephant.class));
        //Проверяем сработало ли оно (должно было)
        Assert.assertEquals(field.getTypeOfAnimal(), List.of(Elephant.class));
    }

    @Test(expectedExceptions = BadClimateException.class)
    public void climateZoneTest(){
        //Проверка начальных данных.
        Assert.assertEquals(field.getClimateZone(),ClimateZone.TROPICAL);
        //Пытаемся установить плохой климат
        field.setClimateZone(ClimateZone.SUBTROPICAL);
        //Проверяем что он не поставился
        Assert.assertNotEquals(field.getClimateZone(), ClimateZone.SUBTROPICAL);
        //Ставим хороший климат(который и стоит)
        field.setClimateZone(ClimateZone.TROPICAL);
        //Проверяем сходство
        Assert.assertEquals(field.getClimateZone(),ClimateZone.TROPICAL);
        //Убираем хамелов
        field.removeAnimal(1L);
        field.removeAnimal(2L);
        //Меняем на тропический климат
        field.setClimateZone(ClimateZone.SUBTROPICAL);
        //Чекаем тропики, ВУАЛЯ!
        Assert.assertEquals(field.getClimateZone(),ClimateZone.SUBTROPICAL);
    }

    @AfterTest
    public void message(){
        System.out.println("Слонов в поле - " + field.getAnimals().size());
        System.out.println("В ходе тестов ни один слон не пострадал!\nDuring the tests, not a single elephant was harmed!");
    }
}
