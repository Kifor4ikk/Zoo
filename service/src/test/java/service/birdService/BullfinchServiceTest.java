package service.birdService;

import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.service.animal.bird.finch.BullfinchService;
import org.apache.ibatis.javassist.bytecode.analysis.Executor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.ContextTest;

import javax.naming.Context;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Test
public class BullfinchServiceTest extends ContextTest {

    BullfinchService bullfinchService = context.getBean(BullfinchService.class);

    Bullfinch animal = new Bullfinch("TestAnimal", LocalTime.now().toString(), 3, Set.of(Terrarium.class),
            Set.of(ClimateZone.SUBANTARCTIC), Set.of(Bugs.class), "TestWings", "TestColor");


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
        System.out.println("Im in!");
        // Create test
        bullfinchService.create(animal, new Field());
        //Get Test
        animal.setId(1L);
        Assert.assertEquals(bullfinchService.getById(animal.getId()), animal);
        // Update test
        animal.setAge(4);
        animal.setName("Oleg");
        animal.setWings("NewWings");
        Assert.assertNotEquals(animal, bullfinchService.getById(animal.getId()));
        bullfinchService.update(animal);
        Assert.assertEquals(animal, bullfinchService.getById(animal.getId()));
        // change status test
        bullfinchService.setDeleteStatus(animal.getId(), true);
        Assert.assertTrue(bullfinchService.getById(animal.getId()).isDeleted());

        bullfinchService.setDeleteStatus(animal.getId(), false);
        Assert.assertFalse(bullfinchService.getById(animal.getId()).isDeleted());
        // delete test
        bullfinchService.hardDelete(animal.getId());
        // thread insert tests

        long countOfThreads = 5L;
        long countOfWrites = 100L;

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
                            bullfinchService.create(animal, new Field());
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