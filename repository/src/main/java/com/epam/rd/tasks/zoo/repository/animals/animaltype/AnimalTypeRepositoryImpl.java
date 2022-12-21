package com.epam.rd.tasks.zoo.repository.animals.animaltype;

import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class AnimalTypeRepositoryImpl {

    private final SqlSessionFactory sqlSessionFactory;
    public AnimalTypeRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public <T extends Animal> T putInfoAboutTypeOfAnimalByTypeOfAnimal(T animal) throws ClassNotFoundException {

        SqlSession session = sqlSessionFactory.openSession();
        for(AnimalType animalType : session.getMapper(AnimalTypeRepository.class).infoAboutAnimalTypeByName(animal.getClass().getName())){
            animal.getClimateZone().add(ClimateZone.valueOf(animalType.getClimateZone()));
            animal.getLivingZone().add((Class<? extends AnimalHouse>) Class.forName(animalType.getLivingZone()));
            animal.getFoodType().add((Class<? extends Food>) Class.forName(animalType.getFoodType()));
        }
        session.close();
        return animal;
    }
}
