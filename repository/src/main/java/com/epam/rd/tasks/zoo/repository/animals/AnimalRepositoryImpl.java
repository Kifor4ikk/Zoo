package com.epam.rd.tasks.zoo.repository.animals;

import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.postgresql.core.ResultHandlerBase;
import org.springframework.ui.ModelMap;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalRepositoryImpl implements AnimalRepositoryFunctional<Animal, Long> {

    private SqlSession session;

    public AnimalRepositoryImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public Long create(Animal animal, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        session.insert("com.epam.rd.tasks.zoo.repository.animals.AnimalRepository.createAnimal", animal);
        return animal.getId() + 1;
    }
}
