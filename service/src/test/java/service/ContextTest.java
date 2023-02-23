package service;

import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepositoryImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashSet;

public class ContextTest {
    public ApplicationContext context = new ClassPathXmlApplicationContext( "TestSpringBean.xml");
}
