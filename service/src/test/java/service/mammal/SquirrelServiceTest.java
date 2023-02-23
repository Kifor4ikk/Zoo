package service.mammal;

import com.epam.rd.tasks.zoo.animal.mammal.rodent.Squirrel;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.food.Meat;
import com.epam.rd.tasks.zoo.food.Wheat;
import com.epam.rd.tasks.zoo.service.animal.mammal.rodent.SquirrelService;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.ContextTest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SquirrelServiceTest extends ContextTest {


    SquirrelService crabService = context.getBean(SquirrelService.class);

    Squirrel animal = new Squirrel("TestAnimal", LocalTime.now().toString(), 3, Set.of(Field.class),
            Set.of(ClimateZone.SUBTROPICAL,ClimateZone.MODERATE, ClimateZone.ARCTIC, ClimateZone.SUBANTARCTIC),
            Set.of(Meat.class, Wheat.class, Bugs.class), "TestSeashell", "red");


    @BeforeTest
    public void beforeTest() throws SQLException, ClassNotFoundException, IOException, IOException {
        //Before test create
        context.getBean(Connection.class).createStatement().execute("DROP SCHEMA public CASCADE; CREATE SCHEMA public;");

        BufferedReader reader = new BufferedReader(new FileReader("D:\\java\\Zoo\\SQL_Script.txt"));
        String temp = reader.readLine();
        StringBuilder createScript = new StringBuilder();
        while (temp != null) {
            createScript.append(temp);
            createScript.append(System.lineSeparator());
            temp = reader.readLine();
        }
        context.getBean(Connection.class).createStatement().execute(String.valueOf(createScript));
        System.out.println("Database updated");
    }

    @AfterTest
    public void testxx() throws SQLException {
        Connection connection = (Connection) context.getBean("connection");
        System.out.println("Close connection - successful");
        connection.close();
    }

    @Test
    public void test() throws SQLException, ClassNotFoundException, InterruptedException {

        // Create test
        animal.setId(1L);
        crabService.create(animal, new Field());
        //Get Test
        Assert.assertEquals(animal, crabService.getById(animal.getId()));
        // Update test
        animal.setAge(4);
        animal.setName("Oleg");
        animal.setTail("newParam");
        animal.setFur("reed");
        Assert.assertNotEquals(animal, crabService.getById(animal.getId()));
        crabService.update(animal);
        Assert.assertEquals(animal, crabService.getById(animal.getId()));
        // change status test
        crabService.setDeleteStatus(animal.getId(), true);
        Assert.assertTrue(crabService.getById(animal.getId()).isDeleted());

        crabService.setDeleteStatus(animal.getId(), false);
        Assert.assertFalse(crabService.getById(animal.getId()).isDeleted());
        // delete test
        crabService.hardDelete(animal.getId());
        // thread insert tests

        Long countOfThreads = 5L;
        Long countOfWrites = 100L;

        ExecutorService es = Executors.newFixedThreadPool(5);
        synchronized (es) {
            for (int i = 0; i < countOfThreads; i++) {
                int finalI = i;
                es.execute(() -> {
                    System.out.println("Join thread " + finalI);
                    try {
                        for (int j = 0; j < countOfWrites; j++) {
                            animal.setName(finalI + " " + j + " Valeron De Goster");
                            animal.setAge(2);
                            crabService.create(animal, new Field());
                        }
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                });
            }

            es.wait(1000);
            es.shutdown();
        }
    }
}