package com.epam.rd.tasks.zoo;

import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animal.mammal.predator.Lion;
import com.epam.rd.tasks.zoo.animal.mammal.rodent.Squirrel;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Field;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.food.Bugs;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepository;
import com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.bird.BullfinchRepository;
import com.epam.rd.tasks.zoo.repository.animals.crustacean.highcancer.crab.CrabRepository;
import com.epam.rd.tasks.zoo.service.animal.bird.finch.BullfinchService;
import org.apache.ibatis.javassist.bytecode.analysis.Analyzer;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@ComponentScan
public class SpringStarter {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");

        AnimalTypeRepositoryImpl animalTypeRepositoryImpl = context.getBean("animalTypeRepository", AnimalTypeRepositoryImpl.class);

        //Sql session factory
        SqlSession session = context.getBean("sqlSessionFactory", SqlSessionFactory.class).openSession();

        BullfinchRepository bullfinchRepository = (BullfinchRepository) session.getMapper(BullfinchRepository.class);

        Bullfinch bullfinch = new Bullfinch("You are my test now", LocalTime.now().toString()   ,1, Set.of(Terrarium.class),
                Set.of(ClimateZone.SUBTROPICAL), Set.of(Bugs.class),"You are my wings now", "Yes indeed");

        context.getBean(BullfinchService.class).create(bullfinch, new Field());
        System.out.println(context.getBean(BullfinchService.class).getById(1L));

        session.commit();
    }


}
