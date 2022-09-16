package com.epam.rd.tasks.zoo.repository.animal.mammal.predator.wolf;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Shrimp;
import com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.shrimp.ShrimpMapper;
import com.epam.rd.tasks.zoo.repository.animal.mammal.MammalRepository;
import com.epam.rd.tasks.zoo.repository.animal.mammal.MammalRepositoryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WolfRepository extends MammalRepositoryImpl {

    private WolfMapper wolfMapper;

    public WolfRepository(Connection connection, WolfMapper wolfMapper) {
        super(connection, wolfMapper);
        this.wolfMapper = wolfMapper;
    }

    public void create(Wolf wolf, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        try {
            connection.setAutoCommit(false);
            try(ResultSet wolfRaw = state().executeQuery("INSERT INTO wolf (ID,behaviour) " +
                    "VALUES (" + super.create(wolf, animalHouse, Wolf.class) + ", '" + wolf.getBehaviour() + "') RETURNING ID;")){
                if(wolfRaw.next() && wolfRaw.getLong("id") == 0) throw new BadAnimalTypeException("Cant create Wolf!");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            System.err.println(" -> operation wolf creation was canceled <- ");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public Wolf getById(Long id) throws SQLException{

        try (ResultSet wolfRaw = state().executeQuery(getSelectAnimal() + getSelectMammal() + getSelectWolf() +
                getFromInnerJoinAnimal() + getInnerJoinMammal() + getInnerJoinWolf() + getWhereId(id))) {
            Wolf wolfGet = wolfMapper.fromRawToWolf(wolfRaw, new Wolf());
            if(wolfGet != null && wolfGet.getId() == 0) throw new BadAnimalTypeException("Wolf with ID = " + id + " do not exist");
            return wolfGet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NotFoundException("Wolf with id " + id + " was not found!");
    }

    protected String getSelectWolf(){
        return ", wolf.behaviour ";
    }

    protected String getInnerJoinWolf(){
        return "INNER JOIN wolf ON wolf.id = an.id ";
    }

    protected String getWhereId(Long id) {
        return "WHERE an.id = " + id + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animals.mammal.predator.Wolf'";
    }

    public boolean update(Wolf wolf) throws SQLException {
        try {
            connection.setAutoCommit(false);
            state().executeQuery("UPDATE wolf SET behaviour = '" + wolf.getBehaviour() + "' WHERE ID = " + wolf.getId() + " RETURNING true;");
            super.update(wolf);
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
