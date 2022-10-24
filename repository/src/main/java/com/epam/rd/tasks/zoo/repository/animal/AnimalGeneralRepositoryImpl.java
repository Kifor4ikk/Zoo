package com.epam.rd.tasks.zoo.repository.animal;

import com.epam.rd.tasks.zoo.repository.database.RepositoryConnection;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnimalGeneralRepositoryImpl extends RepositoryConnection {

    private AnimalGeneralMapper animalGeneralMapper;

    public AnimalGeneralRepositoryImpl(Connection connection, AnimalGeneralMapper animalGeneralMapper) {
        super(connection);
        this.animalGeneralMapper = animalGeneralMapper;
    }

    public Map<Long,String> getAllIdAndTypeOfAnimal() throws SQLException {

        try (ResultSet idAndTypeOfAnimalRaw = state().executeQuery("select animal.id, animaltype.animaltype from animal " +
                "INNER JOIN animaltype ON animaltype.id = animal.id_animaltype")){
            return animalGeneralMapper.idAndTypeFromRawToMap(idAndTypeOfAnimalRaw);
        }
    }

    public Map<Long,String> getAllIdAndTypeOfAnimalByName(String name) throws SQLException {

        try (ResultSet idAndTypeOfAnimalRaw = state().executeQuery("select animal.id, animaltype.animaltype from animal " +
                "INNER JOIN animaltype ON animaltype.id = animal.id_animaltype WHERE animal.name LIKE '%" + name + "%'")){
            return animalGeneralMapper.idAndTypeFromRawToMap(idAndTypeOfAnimalRaw);
        }
    }

    public Map<Long,String> getAllIdAndTypeOfAnimalByDateCreation(Date date) throws SQLException {

        try (ResultSet idAndTypeOfAnimalRaw = state().executeQuery("select animal.id, animaltype.animaltype from animal " +
                "INNER JOIN animaltype ON animaltype.id = animal.id_animaltype WHERE animal.createDate = '"+ date + "'")){
            return animalGeneralMapper.idAndTypeFromRawToMap(idAndTypeOfAnimalRaw);
        }
    }

    public Set<String> getAllTypesOfAnimal() throws SQLException {
        try (ResultSet rawTypes = state().executeQuery("select DISTINCT animalType from animaltype")){
            return animalGeneralMapper.typeFromRawToSet(rawTypes);
        }
    }

    public AnimalIdTypeAndHouseId getAllIdAndTypeOfAnimalAndHouseIdByAnimalId(Long id) throws SQLException {

        try (ResultSet idAndTypeOfAnimalRaw = state().executeQuery("select animal.id, animaltype.animaltype, animalhouse.id as animalHouseId from animal" +
                " INNER JOIN animaltype ON animaltype.id = animal.id_animaltype" +
                " INNER JOIN animalinhouse ON animalinhouse.animal_id = animal.id" +
                " INNER JOIN animalhouse ON animalhouse.id = animalinhouse.animalhouse_id" +
                " WHERE animal.id = " + id)){
            return animalGeneralMapper.fromRawToAnimalIdTypeAndHouseId(idAndTypeOfAnimalRaw);
        }
    }

    public Map<Long, String> getAllAnimalInHouseByHouseId(Long id) throws SQLException {

        try (ResultSet idAndTypeOfAnimalRaw = state().executeQuery("select animal.id, animaltype.animaltype from animal" +
                " INNER JOIN animaltype ON animaltype.id = animal.id_animaltype" +
                " INNER JOIN animalinhouse ON animalinhouse.animal_id = animal.id" +
                " INNER JOIN animalhouse ON animalhouse.id = animalinhouse.animalhouse_id" +
                " WHERE animalhouse.id = " + id)){
            return animalGeneralMapper.idAndTypeFromRawToMap(idAndTypeOfAnimalRaw);
        }
    }
}
