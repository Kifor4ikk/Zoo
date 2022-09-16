package com.epam.rd.tasks.zoo.repository.animal.mammal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.animals.mammal.Mammal;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;
import com.epam.rd.tasks.zoo.repository.animal.AnimalRepositoryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MammalRepositoryImpl extends AnimalRepositoryImpl {

    public MammalRepositoryImpl(Connection connection, AnimalMapper animalMapper) {
        super(connection, animalMapper);
    }

    public long create(Mammal mammal, AnimalHouse animalHouse, Class<? extends Animal> typeOfAnimal) throws SQLException, ClassNotFoundException {
        try (ResultSet mammalRaw = state().executeQuery("INSERT INTO mammal (ID,tail) VALUES (" +
                super.create(mammal, animalHouse, typeOfAnimal) + ",'" + mammal.getTail() + "') RETURNING ID;")) {
            if (mammalRaw.next() && mammalRaw.getLong("id") == 0)
                throw new BadAnimalTypeException("Cant create Mammal!");
            return mammalRaw.getLong("id");
        }
    }

    protected String getInnerJoinMammal() {
        return "INNER JOIN mammal ON mammal.id = an.id ";
    }

    protected String getSelectMammal() {
        return ", mammal.tail ";
    }

    public void update(Mammal mammal) throws SQLException, ClassNotFoundException {
        super.update(mammal);
        state().executeQuery("UPDATE mammal SET tail = '" + mammal.getTail() + "' WHERE ID = " + mammal.getId() + " RETURNING true;");
    }

}
