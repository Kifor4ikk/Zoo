package com.epam.rd.tasks.zoo.repository.animal.mammal.predator.wolf;

import com.epam.rd.tasks.zoo.animal.mammal.predator.Wolf;
import com.epam.rd.tasks.zoo.repository.animal.mammal.MammalMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WolfMapper extends MammalMapper  {

    public Wolf fromRawToWolf(ResultSet wolfRaw, Wolf wolf) throws ClassNotFoundException, SQLException {
        while(wolfRaw.next()) {
            wolf = (Wolf) fromRawToAnimal(wolfRaw, wolf);
            wolf = (Wolf) fromRawToMammal(wolfRaw, wolf);
            wolf.setBehaviour(wolfRaw.getString("behaviour"));
        }
        return (Wolf) wolf;
    }
}
