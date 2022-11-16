package com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.crab;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanRepositoryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrabRepository extends CrustaceanRepositoryImpl {

    private CrabMapper crabMapper;

    public CrabRepository(Connection connection, CrabMapper crabMapper) {
        super(connection, crabMapper, Crab.class);
        this.crabMapper = crabMapper;
    }

    public void create(Crab crustacean, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        try {
            connection.setAutoCommit(false);
            try(ResultSet crabRaw = state().executeQuery("INSERT INTO Crab (ID,taste) " +
                    "VALUES (" + super.create(crustacean, animalHouse, Crab.class) + ", '" + crustacean.getTaste() + "') RETURNING ID;")){
                if(crabRaw.next() && crabRaw.getLong("ID") == 0) throw new BadAnimalTypeException("Cant create crab!");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            System.err.println(" -> operation Crab creation was canceled <- ");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public Crab getById(Long id) throws SQLException {

        try (ResultSet crabRaw = state().executeQuery(getSelectAnimal() + getSelectCrustacean() + getSelectCrab() +
                getFromInnerJoinAnimal() + getInnerJoinCrustacean() + getInnerJoinCrab() + getWhereId(id))) {
            Crab crabGet = crabMapper.fromRawToCrab(crabRaw, new Crab());
            if(crabGet != null && crabGet.getId() == 0) throw new BadAnimalTypeException("Crab with ID = " + id + " do not exist");
            return crabGet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NotFoundException("Crab with id " + id + " was not found!");
    }


    public String getSelectCrab(){
        return ", crab.taste ";
    }

    public String getInnerJoinCrab(){
        return "INNER JOIN crab ON crab.id = an.id ";
    }

    public boolean update(Crab crab) throws SQLException, ClassNotFoundException {
        try {
            connection.setAutoCommit(false);
            state().executeQuery("UPDATE Crab SET taste = '" + crab.getTaste() + "' WHERE ID = " + crab.getId() + " RETURNING true;");
            super.update(crab);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
           System.err.println(e);

        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    protected String getWhereId(Long id) {
        return "WHERE an.id = " + id + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Crab'";
    }
}
