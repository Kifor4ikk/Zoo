package com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.shrimp;

import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Shrimp;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShrimpMapper extends CrustaceanMapper {

    public Shrimp fromRawToShrimp(ResultSet shrimpRaw, Shrimp shrimp) throws ClassNotFoundException, SQLException {
        while(shrimpRaw.next()) {
            shrimp = (Shrimp) fromRawToAnimal(shrimpRaw, shrimp);
            shrimp = (Shrimp) fromRawToCrustacean(shrimpRaw, shrimp);
            shrimp.setSize(shrimpRaw.getString("size"));
        }
        return (Shrimp) shrimp;
    }

}
