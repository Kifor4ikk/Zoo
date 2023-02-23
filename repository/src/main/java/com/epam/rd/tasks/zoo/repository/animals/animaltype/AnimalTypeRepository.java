package com.epam.rd.tasks.zoo.repository.animals.animaltype;

import com.epam.rd.tasks.zoo.animal.Animal;

import java.util.List;
import java.util.Map;

public interface AnimalTypeRepository {

    public List<AnimalType> infoAboutAnimalTypeByType(String type);
    public <T extends Animal> T putInfoAboutTypeOfAnimalByTypeOfAnimal(T animal) throws ClassNotFoundException;
    public Map<Long, String> getIdAndTypeofAnimalMap();
}
