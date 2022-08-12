package com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancers.crab;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanRepositoryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrabRepository extends CrustaceanRepositoryImpl {

    private CrabMapper crabMapper;

    public CrabRepository(Connection connection, CrabMapper crabMapper) {
        super(connection, crabMapper);
        this.crabMapper = crabMapper;
    }

    public void create(Crustacean crustacean, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        try {
            connection.setAutoCommit(false);
            super.create(crustacean, animalHouse, Crab.class);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

    }

    public Crab getById(Long id) throws SQLException {

        try (ResultSet crabRaw = state().executeQuery(getSelectAnimal() + getSelectCrustacean() +
                getFromInnerJoinAnimal() + getInnerJoinCrustacean() + getWhereIdCrustacean(id))) {
            return crabMapper.fromRawToCrab(crabRaw);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NotFoundException("Crab with id " + id + " was not found!");
    }
}
