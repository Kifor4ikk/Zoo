package com.epam.rd.tasks.zoo.repository.animal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AnimalGeneralMapper {

    public Map<Long,String> idAndTypeFromRawToMap(ResultSet rawData) throws SQLException {
        Map<Long,String> resultData = new HashMap<>();

        while (rawData.next()){
            resultData.put(rawData.getLong("id"), rawData.getString("animaltype"));
        }
        return resultData;
    }

    public Set<String> typeFromRawToSet(ResultSet rawData) throws SQLException{
        Set<String> types = new HashSet<>();
        while (rawData.next()){
            types.add(rawData.getString("animaltype"));
        }
        return types;
    }

    public AnimalIdTypeAndHouseId fromRawToAnimalIdTypeAndHouseId(ResultSet rawData) throws SQLException{

        AnimalIdTypeAndHouseId temp = new AnimalIdTypeAndHouseId();
        while (rawData.next()){
            temp.setId(rawData.getLong("id"));
            temp.setType(rawData.getString("animaltype"));
            temp.setHouseId(rawData.getLong("animalHouseId"));
        }
        return temp;
    }
}
