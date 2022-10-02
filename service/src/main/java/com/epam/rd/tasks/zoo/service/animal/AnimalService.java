package com.epam.rd.tasks.zoo.service.animal;

import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.repository.animal.AnimalGeneralMapper;
import com.epam.rd.tasks.zoo.repository.animal.AnimalGeneralRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.database.Database;
import org.checkerframework.checker.units.qual.A;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class AnimalService {

    private AnimalGeneralRepositoryImpl animalGeneralRepository;
    private AnimalServiceMapper animalServiceMapper;

    public AnimalService(Connection connection){
        animalGeneralRepository = new AnimalGeneralRepositoryImpl(connection, new AnimalGeneralMapper());
        animalServiceMapper = new AnimalServiceMapper(connection);
    }


    public List<Animal> getAllAnimals() throws SQLException, ClassNotFoundException {

        List<Animal> animalList = new ArrayList<>();
        Map<Long, String> animalsIdAndType = animalGeneralRepository.getAllIdAndTypeOfAnimal();

        for(Long id : animalsIdAndType.keySet()){
            animalList.add(
                    animalServiceMapper.chooseRepositoryByAnimalType(animalsIdAndType.get(id)).getById(id)
            );
        }
        return animalList;
    }

    public Set<String> getAllTypes() throws SQLException {
        return animalGeneralRepository.getAllTypesOfAnimal();
    }

    public Map<String, Long> getCountOfAllTypes() throws SQLException{
        Map<String,Long> tempData = new HashMap<>();
        for(String type : animalGeneralRepository.getAllIdAndTypeOfAnimal().values()){

            if(!tempData.containsKey(type)) tempData.put(type,1L);
            else tempData.replace(type, tempData.get(type) + 1);
        }
        return tempData;
    }
}
