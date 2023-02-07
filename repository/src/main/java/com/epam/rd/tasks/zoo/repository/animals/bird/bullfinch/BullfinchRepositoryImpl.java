package com.epam.rd.tasks.zoo.repository.animals.bird.bullfinch;

import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class BullfinchRepositoryImpl implements BullfinchRepository {

    private SqlSession session;

    public BullfinchRepositoryImpl(SqlSession sqlSession) {
        this.session = sqlSession;
    }

    @Override
    public Long create(Bullfinch animal, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        session.insert("createBullfinch", animal);
        return animal.getId();
    }

    @Override
    public Bullfinch findById(Long aLong) throws SQLException, ClassNotFoundException {
        return session.selectOne("findBullfinchById", aLong);
    }

    @Override
    public void update(Bullfinch animal) throws SQLException, ClassNotFoundException {
        session.update("updateBullfinch", animal);
        session.commit();
    }

    @Override
    public void setDeleteStatus(Long aLong, boolean status) throws SQLException {
        Bullfinch bullfinch = new Bullfinch();
        bullfinch.setId(aLong);
        bullfinch.setDeleted(status);
        session.update("setDeleteStatusBullfinch", bullfinch);
        session.commit();
    }

    @Override
    public void hardDelete(Long aLong) throws SQLException {
        Bullfinch bullfinch = new Bullfinch();
        bullfinch.setId(aLong);
        session.delete("hardDeleteBullfinch", bullfinch);
        session.commit();
    }
}

