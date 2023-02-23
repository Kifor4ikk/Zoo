package com.epam.rd.tasks.zoo.repository.animals.animaltype;

import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.food.Food;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalTypeRepositoryImpl implements AnimalTypeRepository {

    private final SqlSession session;

    public AnimalTypeRepositoryImpl(SqlSession sqlSession) {
        this.session = sqlSession;
    }

    public <T extends Animal> T putInfoAboutTypeOfAnimalByTypeOfAnimal(T animal) throws ClassNotFoundException {

       List<AnimalType> animalTypes = session.selectList("infoAboutAnimalTypeByType", animal.getClass().getName());

        for(AnimalType animalTypeObject : animalTypes){
            animal.getClimateZone().add(ClimateZone.valueOf(animalTypeObject.getClimateZone()));
            animal.getLivingZone().add((Class<? extends AnimalHouse>) Class.forName(animalTypeObject.getLivingZone()));
            animal.getFoodType().add((Class<? extends Food>) Class.forName(animalTypeObject.getFoodType()));
        }
        session.commit();
        return animal;
    }

    public List<AnimalIdAndType> getAnimalsIdsWithTypeOfAnimal (Integer page, Integer size) {
        List<AnimalIdAndType> allAnimals = new ArrayList<>();
        if(page == null) page = 0;
        if(size == null) size = 10;
        allAnimals.add(session.selectOne("com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepository.getIdAndTypeofAnimalMap", 10L));
        return allAnimals;
    }

    @Override
    public List<AnimalType> infoAboutAnimalTypeByType(String type) {
        return null;
    }

    @Override
    public Map<Long, String> getIdAndTypeofAnimalMap() {
        return null;
    }
}
