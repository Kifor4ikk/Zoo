package com.epam.rd.tasks.zoo;

import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animal.mammal.predator.Lion;
import com.epam.rd.tasks.zoo.animal.mammal.rodent.Squirrel;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepository;
import com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.bird.BullfinchRepository;
import org.apache.ibatis.javassist.bytecode.analysis.Analyzer;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

@ComponentScan
public class SpringStarter {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");

        AnimalTypeRepositoryImpl animalTypeRepositoryImpl = context.getBean("animalTypeRepository", AnimalTypeRepositoryImpl.class);

        //Sql session factory
        SqlSession session = context.getBean("sqlSessionFactory", SqlSessionFactory.class).openSession();

        session.commit();
    }


}
