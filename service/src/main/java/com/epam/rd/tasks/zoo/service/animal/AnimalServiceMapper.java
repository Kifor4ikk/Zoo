package com.epam.rd.tasks.zoo.service.animal;

import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Shrimp;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf;
import com.epam.rd.tasks.zoo.animals.mammal.rodent.Squirrel;
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

public class AnimalServiceMapper {

    private final BullfinchRepository bullfinchRepository;
    private final CrabRepository crabRepository;
    private final ShrimpRepository shrimpRepository;
    private final WolfRepository wolfRepository;
    private final SquirrelRepository squirrelRepository;

    public AnimalServiceMapper(Connection connection){
        bullfinchRepository = new BullfinchRepository(connection, new BullfinchMapper());
        crabRepository = new CrabRepository(connection, new CrabMapper());
        shrimpRepository = new ShrimpRepository(connection, new ShrimpMapper());
        wolfRepository = new WolfRepository(connection, new WolfMapper());
        squirrelRepository = new SquirrelRepository(connection,new SquirrelMapper());
    }

    public AnimalRepositoryImpl chooseRepositoryByAnimalType(String animalType){

        String bullFinchType = Bullfinch.class.getName();
        switch (animalType){
            case "com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch": return bullfinchRepository;
            case "com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab": return crabRepository;
            case "com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Shrimp": return shrimpRepository;
            case "com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf": return wolfRepository;
            case "com.epam.rd.tasks.zoo.animals.mammal.rodent.Squirrel": return squirrelRepository;
            default: throw new NotFoundException("Animal with type " + animalType + " was not found!");
        }
    }
}
