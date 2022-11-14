package com.epam.rd.tasks.zoo.repository.animal.bird.finche.bullfinch;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animal.bird.BirdRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BullfinchRepository extends BirdRepository {

    private BullfinchMapper bullfinchMapper;

    public BullfinchRepository(Connection connection, BullfinchMapper animalMapper) {
        super(connection, animalMapper, Bullfinch.class);
        this.bullfinchMapper = animalMapper;
    }
    
    public void create(Bullfinch bullfinch, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        try {
            connection.setAutoCommit(false);
            try(ResultSet BullfinchRaw = state().executeQuery("INSERT INTO Bullfinch (ID,color) " +
                    "VALUES (" + super.create(bullfinch, animalHouse, Bullfinch.class) + ", '" + bullfinch.getColor() + "') RETURNING ID;")){
                if(BullfinchRaw != null && BullfinchRaw.getLong("ID") == 0) throw new BadAnimalTypeException("Cant create Bullfinch!");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            System.err.println(" -> operation Bullfinch creation was canceled <- ");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public Bullfinch getById(Long id) throws SQLException{

        try (ResultSet bullfinchRaw = state().executeQuery(getSelectAnimal() + getSelectBird() + getSelectBullfinch() +
                getFromInnerJoinAnimal() + getInnerJoinBird() + getInnerJoinBullfinch() + getWhereId(id))) {
            Bullfinch bullfinchGet = bullfinchMapper.fromRawToBullfinch(bullfinchRaw, new Bullfinch());
            if(bullfinchGet != null && bullfinchGet.getId() == 0) throw new BadAnimalTypeException("Bullfinch with ID = " + id + " do not exist");
            return bullfinchGet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NotFoundException("Bullfinch with id " + id + " was not found!");
    }

    protected String getSelectBullfinch(){
        return ", bullfinch.color ";
    }

    protected String getInnerJoinBullfinch(){
        return "INNER JOIN bullfinch ON bullfinch.id = an.id ";
    }

    protected String getWhereId(Long id) {
        return "WHERE an.id = " + id + " AND an.isDeleted = false AND aty.animalType = 'com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch'";
    }

    public boolean update(Bullfinch bullfinch) throws SQLException {
        try {
            connection.setAutoCommit(false);
            state().executeQuery("UPDATE Bullfinch SET color = '" + bullfinch.getColor() + "' WHERE ID = " + bullfinch.getId() + " RETURNING true;");
            super.update(bullfinch);
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
