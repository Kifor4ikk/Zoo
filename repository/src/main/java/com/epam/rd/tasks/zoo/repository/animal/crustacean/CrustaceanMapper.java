package com.epam.rd.tasks.zoo.repository.animal.crustacean;

import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.sun.net.httpserver.Authenticator;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CrustaceanMapper {

    public static Crustacean fromRawToCrustacean(Animal animal, ResultSet data){

        Crustacean crustacean = (Crustacean) animal;
        try {
            crustacean.setSeashell(data.getString("seashell"));
        } catch (SQLException e){
            System.err.println(e);
        }
        return crustacean;
    }
}
