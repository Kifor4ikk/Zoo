package com.epam.rd.tasks.zoo.repository.animal.bird;

import com.epam.rd.tasks.zoo.animal.bird.Bird;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BirdMapper extends AnimalMapper {

    public Bird fromRawToBird(ResultSet data, Bird bird) {
        try {
            bird.setWings(data.getString("wings"));
        } catch (SQLException e) {
            System.err.println(e);
        }
        return bird;
    }
}
