package com.epam.rd.tasks.zoo.repository.animal.crustacean;

import com.epam.rd.tasks.zoo.Zoo;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.dto.animal.crustacean.CrustaceanDto;
import com.epam.rd.tasks.zoo.dto.animalhouse.AnimalHouseDto;
import com.epam.rd.tasks.zoo.exception.AlreadyExistException;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animal.AnimalRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animalhouse.AnimalHouseRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.database.RepositoryConnection;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CrustaceanRepositoryImpl extends AnimalRepositoryImpl {

    public CrustaceanRepositoryImpl(Connection connection) {
        super(connection);
    }

    //@TODO

    public void create(Crustacean crustacean, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        CrustaceanDto animal = CrustaceanDto.toDTO(crustacean);
        try {
            if (getIdByParams(crustacean) != 0)
                throw new AlreadyExistException("Crustacean with current params already exist!");
        } catch (NotFoundException e) {
            state().execute(
                    "INSERT INTO Animal (name, describe,age,zoneType,climateZone,foodtype,typeofanimal,isdeleted) VALUES ('" +
                            animal.getName() + "','" +
                            animal.getDescribe() + "','" +
                            animal.getAge() + "','" +
                            animal.getLivingZone() + "','" +
                            animal.getClimateZone() + "','" +
                            animal.getFoodType() + "','" +
                            animal.getTypeOfAnimal() + "','" +
                            animal.isDeleted() + "')");
            state().execute("INSERT INTO Crustacean (Animal_Id,seashell) VALUES (" +
                    getIdByParams(crustacean) + ",'" + animal.getSeashell() + "');");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Crustacean getById(Long id) throws SQLException, ClassNotFoundException {

        return null;
    }


    private long getIdByParams(Crustacean crustacean) throws SQLException, ClassNotFoundException {
        CrustaceanDto animal = CrustaceanDto.toDTO(crustacean);
        try (ResultSet resultSet = state().executeQuery("SELECT * FROM Animal WHERE isDeleted = false"
                + " AND name = '" + animal.getName()
                + "' AND describe = '" + animal.getDescribe()
                + "' AND age = '" + animal.getAge()
                + "' AND zonetype = '" + animal.getLivingZone()
                + "' AND foodtype = '" + animal.getFoodType()
                + "' AND climatezone ='" + animal.getClimateZone()
                + "' AND typeofanimal ='" + animal.getTypeOfAnimal() + "'")
        ) {
            if (resultSet.next()) {
                animal.setId(resultSet.getLong("id"));

                if (resultSet.getLong("id") == 0)
                    throw new NotFoundException("Crustacean was not found");
            } else throw new NotFoundException("Crustacean was not found");
        }
        return animal.getId();
    }

    //Have a 3 think how to realise this method
    //now this very SAFETY method
    public void update(Crustacean data) throws SQLException, ClassNotFoundException {
        CrustaceanDto crustacean = CrustaceanDto.toDTO(getById(data.getId()));
        CrustaceanDto animal = CrustaceanDto.toDTO(data);

        if (animal.getName() != null) crustacean.setName(animal.getName());
        if (animal.getDescribe() != null) crustacean.setDescribe(animal.getDescribe());
        if (animal.getAge() != crustacean.getAge()) crustacean.setAge(animal.getAge());
        if (animal.getLivingZone() != null) crustacean.setLivingZone(animal.getLivingZone());
        if (animal.getClimateZone() != null) crustacean.setClimateZone(animal.getClimateZone());
        if (animal.getFoodType() != null) crustacean.setFoodType(animal.getFoodType());
        if (animal.getSeashell() != null) crustacean.setSeashell(animal.getSeashell());

        state().execute("UPDATE Animal SET "
                + "name = '" + animal.getName()
                + "', describe = '" + animal.getDescribe()
                + "', age = '" + animal.getAge()
                + "', zonetype = '" + animal.getLivingZone()
                + "', foodtype = '" + animal.getFoodType()
                + "', climatezone = '" + animal.getClimateZone()
                + "', typeofanimal = '" + animal.getTypeOfAnimal()
                + "' WHERE isDeleted = false AND id = " + data.getId());
        state().execute(
                "UPDATE Crustacean SET seashell = '" + animal.getSeashell() + "' WHERE Animal_id = " + data.getId()
        );
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
