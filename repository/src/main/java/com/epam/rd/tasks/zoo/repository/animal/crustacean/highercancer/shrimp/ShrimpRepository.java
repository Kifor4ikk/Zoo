package com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.shrimp;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Shrimp;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanRepositoryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShrimpRepository extends CrustaceanRepositoryImpl {

    private ShrimpMapper shrimpMapper;

    public ShrimpRepository(Connection connection, ShrimpMapper shrimpMapper) {
        super(connection, shrimpMapper);
        this.shrimpMapper = shrimpMapper;
    }

    public void create(Shrimp shrimp, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        try {
            connection.setAutoCommit(false);
            try(ResultSet shrimpRaw = state().executeQuery("INSERT INTO Shrimp (ID,size) " +
                    "VALUES (" + super.create(shrimp, animalHouse, Shrimp.class) + ", '" + shrimp.getSize() + "') RETURNING ID;")){
                if(shrimpRaw != null && shrimpRaw.next() && shrimpRaw.getLong("id") == 0) throw new BadAnimalTypeException("Cant create Shrimp!");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            System.err.println(" -> operation shrimp creation was canceled <- ");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public Shrimp getById(Long id) throws SQLException{

        try (ResultSet crabRaw = state().executeQuery(getSelectAnimal() + getSelectCrustacean() + getSelectShrimp() +
                getFromInnerJoinAnimal() + getInnerJoinCrustacean() + getInnerJoinShrimp() + getWhereId(id))) {
            Shrimp shrimpGet = shrimpMapper.fromRawToShrimp(crabRaw, new Shrimp());
            if(shrimpGet != null && shrimpGet.getId() == 0) throw new BadAnimalTypeException("Shrimp with ID = " + id + " do not exist");

            return shrimpGet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NotFoundException("Shrimp with id " + id + " was not found!");
    }

    protected String getSelectShrimp(){
        return ", shrimp.size ";
    }

    protected String getInnerJoinShrimp(){
        return "INNER JOIN shrimp ON shrimp.id = an.id ";
    }

    protected String getWhereId(Long id) {
        return "WHERE an.id = " + id + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Shrimp'";
    }

    public boolean update(Shrimp shrimp) throws SQLException {
        try {
            connection.setAutoCommit(false);
            state().executeQuery("UPDATE Shrimp SET size = '" + shrimp.getSize() + "' WHERE ID = " + shrimp.getId() + " RETURNING true;");
            super.update(shrimp);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            System.err.println(e);

        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

}
