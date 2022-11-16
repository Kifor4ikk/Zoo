package com.epam.rd.tasks.zoo.repository.animal.crustacean;

import com.epam.rd.tasks.zoo.animal.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.repository.animal.AnimalMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CrustaceanMapper extends AnimalMapper {

    public Crustacean fromRawToCrustacean(ResultSet data, Crustacean crustacean) {

        try {
            crustacean.setSeashell(data.getString("seashell"));
        } catch (SQLException e) {
            System.err.println(e);
        }
        return crustacean;
    }
}
