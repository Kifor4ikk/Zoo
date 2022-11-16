package com.epam.rd.tasks.zoo.service.animal;

import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animal.AnimalRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animal.bird.finche.bullfinch.BullfinchMapper;
import com.epam.rd.tasks.zoo.repository.animal.bird.finche.bullfinch.BullfinchRepository;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.crab.CrabMapper;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.crab.CrabRepository;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.shrimp.ShrimpMapper;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.shrimp.ShrimpRepository;
import com.epam.rd.tasks.zoo.repository.animal.mammal.predator.wolf.WolfMapper;
import com.epam.rd.tasks.zoo.repository.animal.mammal.predator.wolf.WolfRepository;
import com.epam.rd.tasks.zoo.repository.animal.mammal.rodent.squirrel.SquirrelMapper;
import com.epam.rd.tasks.zoo.repository.animal.mammal.rodent.squirrel.SquirrelRepository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AnimalServiceMapper {

    List<AnimalRepositoryImpl> repositories = new ArrayList<>();

    public AnimalServiceMapper(Connection connection){

        repositories.add(new BullfinchRepository(connection, new BullfinchMapper()));
        repositories.add(new CrabRepository(connection, new CrabMapper()));
        repositories.add(new ShrimpRepository(connection, new ShrimpMapper()));
        repositories.add(new WolfRepository(connection, new WolfMapper()));
        repositories.add(new SquirrelRepository(connection,new SquirrelMapper()));
    }

    @Deprecated
    public AnimalRepositoryImpl chooseRepositoryByAnimalType(String animalType){

        switch (animalType){
            case "com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch": return repositories.get(0);
            case "com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Crab": return repositories.get(1);
            case "com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Shrimp": return repositories.get(2);
            case "com.epam.rd.tasks.zoo.animal.mammal.predator.Wolf": return repositories.get(3);
            case "com.epam.rd.tasks.zoo.animal.mammal.rodent.Squirrel": return repositories.get(4);
            default: throw new NotFoundException("Animal with type " + animalType + " was not found!");
        }
    }

    public AnimalRepositoryImpl chooseRepository(String animalType){
        for(AnimalRepositoryImpl animalRepository : repositories)
            if(animalRepository.getForAnimal().getName().equals(animalType)) return animalRepository;
        throw new NotFoundException("Animal with type " + animalType + " was not found!");
    }
}
