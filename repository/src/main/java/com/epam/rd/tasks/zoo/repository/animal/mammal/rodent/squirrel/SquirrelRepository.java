package com.epam.rd.tasks.zoo.repository.animal.mammal.rodent.squirrel;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.mammal.Mammal;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf;
import com.epam.rd.tasks.zoo.animals.mammal.rodent.Squirrel;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;
import com.epam.rd.tasks.zoo.repository.animal.mammal.MammalRepository;
import com.epam.rd.tasks.zoo.repository.animal.mammal.MammalRepositoryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SquirrelRepository extends MammalRepositoryImpl {

    private SquirrelMapper squirrelMapper;

    public SquirrelRepository(Connection connection, SquirrelMapper squirrelMapper) {
        super(connection, squirrelMapper);
        this.squirrelMapper = squirrelMapper;
    }

    public void create(Squirrel squirrel, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        try {
            connection.setAutoCommit(false);
            try(ResultSet SquirrelRaw = state().executeQuery("INSERT INTO Squirrel (ID,fur) " +
                    "VALUES (" + super.create(squirrel, animalHouse, Squirrel.class) + ", '" + squirrel.getFur() + "') RETURNING ID;")){
                if(SquirrelRaw != null && SquirrelRaw.getLong("ID") == 0) throw new BadAnimalTypeException("Cant create Squirrel!");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            System.err.println(" -> operation Squirrel creation was canceled <- ");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public Squirrel getById(Long id) throws SQLException{

        try (ResultSet squirrelRaw = state().executeQuery(getSelectAnimal() + getSelectMammal() + getSelectSquirrel() +
                getFromInnerJoinAnimal() + getInnerJoinMammal() + getInnerJoinSquirrel() + getWhereId(id))) {
            Squirrel squirrelGet = squirrelMapper.fromRawToSquirrel(squirrelRaw, new Squirrel());
            if(squirrelGet != null && squirrelGet.getId() == 0) throw new BadAnimalTypeException("Squirrel with ID = " + id + " do not exist");
            return squirrelGet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NotFoundException("Squirrel with id " + id + " was not found!");
    }

    protected String getSelectSquirrel(){
        return ", squirrel.fur ";
    }

    protected String getInnerJoinSquirrel(){
        return "INNER JOIN squirrel ON squirrel.id = an.id ";
    }

    protected String getWhereId(Long id) {
        return "WHERE an.id = " + id + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animals.mammal.rodent.Squirrel'";
    }

    public boolean update(Squirrel squirrel) throws SQLException {
        try {
            connection.setAutoCommit(false);
            state().executeQuery("UPDATE Squirrel SET fur = '" + squirrel.getFur() + "' WHERE ID = " + squirrel.getId() + " RETURNING true;");
            super.update(squirrel);
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
