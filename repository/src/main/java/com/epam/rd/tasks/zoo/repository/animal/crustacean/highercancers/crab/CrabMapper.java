package com.epam.rd.tasks.zoo.repository.animal.crustacean.highercancers.crab;

import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.repository.animal.crustacean.CrustaceanMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CrabMapper extends CrustaceanMapper {

    public Crab fromRawToCrab(ResultSet crabRaw) throws ClassNotFoundException, SQLException {
        Crab crab = new Crab();
        while(crabRaw.next()) {
            crab = (Crab) fromRawToAnimal(crabRaw, crab);
            crab = (Crab) fromRawToCrustacean(crabRaw, crab);
        }
        return (Crab) crab;
    }
}
