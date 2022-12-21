package com.epam.rd.tasks.zoo.repository.animals.animaltype;

import java.util.List;

public interface AnimalTypeRepository {

    public List<AnimalType> infoAboutAnimalTypeByName(String type);

}
