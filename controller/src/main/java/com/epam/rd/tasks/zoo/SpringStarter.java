package com.epam.rd.tasks.zoo;


import com.epam.rd.tasks.zoo.service.animal.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EntityScan
@EnableAutoConfiguration
@SpringBootApplication
public class SpringStarter implements CommandLineRunner {


    private AnimalService animalService;

    @Autowired
    public SpringStarter(AnimalService animalService) {
        this.animalService = animalService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringStarter.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(animalService.getAllTypes());
        System.out.println(animalService.getAllAnimals());
    }
}
