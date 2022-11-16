package com.epam.rd.tasks.zoo.repository.animal.mammal.rodent.squirrel;

import com.epam.rd.tasks.zoo.animal.mammal.rodent.Squirrel;
import com.epam.rd.tasks.zoo.repository.animal.mammal.MammalMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SquirrelMapper extends MammalMapper {

    public Squirrel fromRawToSquirrel(ResultSet squirrelRaw, Squirrel squirrel) throws ClassNotFoundException, SQLException {
        while(squirrelRaw.next()) {
            squirrel = (Squirrel) fromRawToAnimal(squirrelRaw, squirrel);
            squirrel = (Squirrel) fromRawToMammal(squirrelRaw, squirrel);
            squirrel.setFur(squirrelRaw.getString("fur"));
        }
        return (Squirrel) squirrel;
    }

}
