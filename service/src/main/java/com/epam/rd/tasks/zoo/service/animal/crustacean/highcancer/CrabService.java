package com.epam.rd.tasks.zoo.service.animal.crustacean.highcancer;

import com.epam.rd.tasks.zoo.animal.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.crustacean.highcancer.crab.CrabRepositoryImpl;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class CrabService {

    private CrabRepositoryImpl crabRepository;

    private AnimalTypeRepositoryImpl animalTypeRepository;

    private SqlSession sqlSession;

    public CrabService(CrabRepositoryImpl crabRepository, AnimalTypeRepositoryImpl animalTypeRepository, SqlSession sqlSession) {
        this.crabRepository = crabRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.sqlSession = sqlSession;
    }

    public boolean create(Crab crab, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        crabRepository.create(crab,animalHouse);
        sqlSession.commit();
        return true;
    }

    public Crab getById(Long id) throws SQLException, ClassNotFoundException {
        Crab crab = crabRepository.findById(id);
        if(crab == null)
            throw new NotFoundException("Crab with id " + id + " not found");
        sqlSession.commit();
        return animalTypeRepository.putInfoAboutTypeOfAnimalByTypeOfAnimal(crab);
    }

    public boolean update(Crab crab) throws SQLException, ClassNotFoundException{
        Crab crabFromDB = getById(crab.getId());
        if(crabFromDB == null)
            throw new NotFoundException("Crab with id " + crab.getId() + " not found");
        crabRepository.update(crab);
        sqlSession.commit();
        return true;
    }

    public boolean setDeleteStatus(Long id, boolean isDeleted) throws SQLException, ClassNotFoundException{
        Crab crabFromDB = crabRepository.findById(id);
        if(crabFromDB == null)
            throw new NotFoundException("Crab with id " + id + " not found");
        crabRepository.setDeleteStatus(id, isDeleted);
        sqlSession.commit();
        return true;
    }

    public boolean hardDelete(Long id) throws SQLException, ClassNotFoundException{
        crabRepository.hardDelete(id);
        sqlSession.commit();
        return true;
    }
}
