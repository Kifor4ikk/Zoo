package com.epam.rd.tasks.zoo.repository.animal.crustacean;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.animal.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.repository.animal.AnimalRepositoryImpl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrustaceanRepositoryImpl extends AnimalRepositoryImpl {

    public CrustaceanRepositoryImpl(Connection connection, CrustaceanMapper crustaceanMapper, Class<? extends Crustacean> forAnimal) {
        super(connection, crustaceanMapper, forAnimal);
    }

    public long create(Crustacean crustacean, AnimalHouse animalHouse, Class<? extends Animal> typeOfAnimal) throws SQLException, ClassNotFoundException {
        try (ResultSet crustaceanRaw = state().executeQuery("INSERT INTO Crustacean (ID,seashell) VALUES (" +
                super.create(crustacean, animalHouse, typeOfAnimal) + ",'" + crustacean.getSeashell() + "') RETURNING ID;")) {
            if (crustaceanRaw.next() && crustaceanRaw.getLong("id") == 0)
                throw new BadAnimalTypeException("Cant create crustacean!");
            return crustaceanRaw.getLong("ID");
        }
    }

    protected String getSelectCrustacean() {
        return ", cr.seashell ";
    }

    protected String getInnerJoinCrustacean() {
        return "INNER JOIN crustacean cr ON cr.id = an.id ";
    }

    public void update(Crustacean data) throws SQLException, ClassNotFoundException {
        super.update(data);
        state().executeQuery("UPDATE crustacean SET seashell = '" + data.getSeashell() + "' WHERE ID = " + data.getId() + " RETURNING true;");
    }
}
