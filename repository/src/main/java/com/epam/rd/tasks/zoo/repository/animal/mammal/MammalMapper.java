package com.epam.rd.tasks.zoo.repository.animal.mammal;

import com.epam.rd.tasks.zoo.animal.mammal.Mammal;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MammalMapper extends AnimalMapper {

    public Mammal fromRawToMammal(ResultSet data, Mammal mammal) {

        try {
            mammal.setTail(data.getString("tail"));
        } catch (SQLException e) {
            System.err.println(e);
        }
        return mammal;
    }
}
