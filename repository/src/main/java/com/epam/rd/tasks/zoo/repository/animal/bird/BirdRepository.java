package com.epam.rd.tasks.zoo.repository.animal.bird;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.bird.Bird;
import com.epam.rd.tasks.zoo.animals.mammal.Mammal;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;
import com.epam.rd.tasks.zoo.repository.animal.AnimalRepositoryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BirdRepository extends AnimalRepositoryImpl {

    public BirdRepository(Connection connection, AnimalMapper animalMapper, Class<? extends Bird> forAnimal) {
        super(connection, animalMapper, forAnimal);
    }

    public long create(Bird bird, AnimalHouse animalHouse, Class<? extends Animal> typeOfAnimal) throws SQLException, ClassNotFoundException {
        try (ResultSet BirdRaw = state().executeQuery("INSERT INTO bird (ID,wings) VALUES (" +
                super.create(bird, animalHouse, typeOfAnimal) + ",'" + bird.getWings() + "') RETURNING ID;")) {
            if (BirdRaw.next() && BirdRaw.getLong("id") == 0)
                throw new BadAnimalTypeException("Cant create Bird!");
            return BirdRaw.getLong("id");
        }
    }

    protected String getInnerJoinBird() {
        return "INNER JOIN bird ON bird.id = an.id ";
    }

    protected String getSelectBird() {
        return ", bird.wings ";
    }

    public void update(Bird bird) throws SQLException, ClassNotFoundException {
        super.update(bird);
        state().executeQuery("UPDATE bird SET wings = '" + bird.getWings() + "' WHERE ID = " + bird.getId() + " RETURNING true;");
    }
}
