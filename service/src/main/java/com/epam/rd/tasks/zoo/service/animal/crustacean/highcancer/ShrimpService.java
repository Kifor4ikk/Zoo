package com.epam.rd.tasks.zoo.service.animal.crustacean.highcancer;

import com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Shrimp;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.crustacean.highcancer.shrimp.ShrimpRepositoryImpl;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class ShrimpService {
    private ShrimpRepositoryImpl shrimpRepository;

    private AnimalTypeRepositoryImpl animalTypeRepository;

    private SqlSession sqlSession;

    public ShrimpService(ShrimpRepositoryImpl shrimpRepository, AnimalTypeRepositoryImpl animalTypeRepository, SqlSession sqlSession) {
        this.shrimpRepository = shrimpRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.sqlSession = sqlSession;
    }

    public boolean create(Shrimp shrimp, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        shrimpRepository.create(shrimp,animalHouse);
        sqlSession.commit();
        return true;
    }

    public Shrimp getById(Long id) throws SQLException, ClassNotFoundException {
        Shrimp shrimp = shrimpRepository.findById(id);
        if(shrimp == null)
            throw new NotFoundException("Shrimp with id " + id + " not found");
        sqlSession.commit();
        return animalTypeRepository.putInfoAboutTypeOfAnimalByTypeOfAnimal(shrimp);
    }

    public boolean update(Shrimp shrimp) throws SQLException, ClassNotFoundException{
        Shrimp shrimpFromDB = getById(shrimp.getId());
        if(shrimpFromDB == null)
            throw new NotFoundException("Shrimp with id " + shrimp.getId() + " not found");
        shrimpRepository.update(shrimp);
        sqlSession.commit();
        return true;
    }

    public boolean setDeleteStatus(Long id, boolean isDeleted) throws SQLException, ClassNotFoundException{
        Shrimp shrimpFromDB = shrimpRepository.findById(id);
        if(shrimpFromDB == null)
            throw new NotFoundException("Shrimp with id " + id + " not found");
        shrimpRepository.setDeleteStatus(id, isDeleted);
        sqlSession.commit();
        return true;
    }

    public boolean hardDelete(Long id) throws SQLException, ClassNotFoundException{
        shrimpRepository.hardDelete(id);
        sqlSession.commit();
        return true;
    }
}
