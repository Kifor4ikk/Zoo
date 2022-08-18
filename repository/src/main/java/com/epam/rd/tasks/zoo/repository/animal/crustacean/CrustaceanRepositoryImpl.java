package com.epam.rd.tasks.zoo.repository.animal.crustacean;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.exception.AlreadyExistException;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animal.AnimalRepositoryImpl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrustaceanRepositoryImpl extends AnimalRepositoryImpl {

    public CrustaceanRepositoryImpl(Connection connection, CrustaceanMapper crustaceanMapper) {
        super(connection, crustaceanMapper);
    }

    public void create(Crustacean crustacean, AnimalHouse animalHouse, Class<? extends Animal> typeOfAnimal) throws SQLException, ClassNotFoundException {

        try(ResultSet crustaceanRaw = state().executeQuery("INSERT INTO Crustacean (Animal_Id,seashell) VALUES (" +
                super.create(crustacean, animalHouse, typeOfAnimal) + ",'" + crustacean.getSeashell() + "') RETURNING ID;") ){
            if(crustaceanRaw.next() && crustaceanRaw.getLong("id") == 0) throw new BadAnimalTypeException("Cant create crustacean!");
        };
    }

    @Override
    public Animal getById(Long id) throws SQLException, ClassNotFoundException {
        return null;
    }

    protected String getSelectCrustacean() {
        return ", cr.seashell ";
    }

    protected String getInnerJoinCrustacean() {
        return "INNER JOIN crustacean cr ON cr.animal_id = an.id ";
    }

    protected String getWhereIdCrustacean(Long id) {
        return "WHERE cr.id = " + id + " AND an.isDeleted = false ";
    }

    public void update(Crustacean data) throws SQLException, ClassNotFoundException {
        super.update(data);
        state().executeQuery("UPDATE crustacean SET seashell = '" + data.getSeashell() + "' WHERE animal_Id = " + data.getId() + " RETURNING true;");
    }

    @Override
    public void setDeleteStatus(Long id, boolean status) throws SQLException {
        state().execute("UPDATE Animal SET isDeleted = '" + status + "' WHERE id = " + id);
    }

    @Override
    public void hardDelete(Long id) throws SQLException {
        state().execute("DELETE FROM Animal WHERE id = " + id);
    }
}
