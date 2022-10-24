package com.epam.rd.tasks.zoo.service.animal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.repository.animal.AnimalGeneralMapper;
import com.epam.rd.tasks.zoo.repository.animal.AnimalGeneralRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animal.AnimalIdTypeAndHouseId;
import com.epam.rd.tasks.zoo.repository.animalhouse.AnimalHouseRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.database.Database;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Component
public class AnimalService {

    private AnimalGeneralRepositoryImpl animalGeneralRepository;
    private AnimalHouseRepositoryImpl animalHouseRepository;
    private AnimalServiceMapper animalServiceMapper;

    @Autowired
    public AnimalService(Connection connection){
        animalGeneralRepository = new AnimalGeneralRepositoryImpl(connection, new AnimalGeneralMapper());
        animalHouseRepository = new AnimalHouseRepositoryImpl(connection);
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

    public List<Animal> getAnimalByName(String name) throws SQLException, ClassNotFoundException {
        List<Animal> animalList = new ArrayList<>();
        Map<Long, String> animalsIdAndType = animalGeneralRepository.getAllIdAndTypeOfAnimalByName(name);
        for(Long id : animalsIdAndType.keySet()){
            animalList.add(
                    animalServiceMapper.chooseRepositoryByAnimalType(animalsIdAndType.get(id)).getById(id)
            );
        }
        return animalList;
    }

    public List<Animal> getAnimalByCreationDate(java.sql.Date date) throws SQLException, ClassNotFoundException {
        List<Animal> animalList = new ArrayList<>();
        Map<Long, String> animalsIdAndType = animalGeneralRepository.getAllIdAndTypeOfAnimalByDateCreation(date);
        for(Long id : animalsIdAndType.keySet()){
            animalList.add(
                    animalServiceMapper.chooseRepositoryByAnimalType(animalsIdAndType.get(id)).getById(id)
            );
        }
        return animalList;
    }

    public Map<Animal, AnimalHouse> getAnimalAndHouseByAnimalId(Long id) throws SQLException, ClassNotFoundException {

        Map<Animal, AnimalHouse> tempMap = new HashMap<>();
        AnimalIdTypeAndHouseId tempAnimalIdTypeAndHouseId = animalGeneralRepository.getAllIdAndTypeOfAnimalAndHouseIdByAnimalId(id);

        tempMap.put(animalServiceMapper.chooseRepositoryByAnimalType(tempAnimalIdTypeAndHouseId.getType()).getById(tempAnimalIdTypeAndHouseId.getId()),
                animalHouseRepository.getById(tempAnimalIdTypeAndHouseId.getHouseId()));
        return tempMap;
    }

    public List<Animal> getAllAnimalsInHouseByHouseId(Long houseId) throws SQLException, ClassNotFoundException {
        List<Animal> animalList = new ArrayList<>();
        Map<Long, String> animalsIdAndType = animalGeneralRepository.getAllAnimalInHouseByHouseId(houseId);
        for(Long id : animalsIdAndType.keySet()){
            animalList.add(
                    animalServiceMapper.chooseRepositoryByAnimalType(animalsIdAndType.get(id)).getById(id)
            );
        }
        return animalList;
    }

}
