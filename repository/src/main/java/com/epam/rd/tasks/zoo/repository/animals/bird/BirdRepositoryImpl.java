package com.epam.rd.tasks.zoo.repository.animals.bird;

import com.epam.rd.tasks.zoo.animal.bird.Bird;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class BirdRepositoryImpl implements BirdRepository {

    private SqlSession session;

    public BirdRepositoryImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public Long create(Bird animal, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        session.insert("createBird", animal);
        return animal.getId();
    }

}
