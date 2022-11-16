package com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancer.crab;

import com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CrabMapper extends CrustaceanMapper {

    public Crab fromRawToCrab(ResultSet crabRaw, Crab crab) throws ClassNotFoundException, SQLException {
        while(crabRaw.next()) {
            crab = (Crab) fromRawToAnimal(crabRaw, crab);
            crab = (Crab) fromRawToCrustacean(crabRaw, crab);
            crab.setTaste(crabRaw.getString("taste"));
        }
        return (Crab) crab;
    }
}
