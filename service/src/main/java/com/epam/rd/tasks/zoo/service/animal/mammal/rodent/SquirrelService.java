package com.epam.rd.tasks.zoo.service.animal.mammal.rodent;

import com.epam.rd.tasks.zoo.animal.mammal.rodent.Squirrel;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.mammal.rodent.SquirrelRepositoryImpl;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class SquirrelService {
    
    private SquirrelRepositoryImpl squirrelRepository;
    private AnimalTypeRepositoryImpl animalTypeRepository;

    private SqlSession sqlSession;

    public SquirrelService(SquirrelRepositoryImpl squirrelRepository, AnimalTypeRepositoryImpl animalTypeRepository, SqlSession sqlSession) {
        this.squirrelRepository = squirrelRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.sqlSession = sqlSession;
    }

    public boolean create(Squirrel squirrel, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        squirrelRepository.create(squirrel,animalHouse);
        sqlSession.commit();
        return true;
    }

    public Squirrel getById(Long id) throws SQLException, ClassNotFoundException {
        Squirrel squirrel = squirrelRepository.findById(id);
        if(squirrel == null)
            throw new NotFoundException("Squirrel with id " + id + " not found");
        sqlSession.commit();
        return animalTypeRepository.putInfoAboutTypeOfAnimalByTypeOfAnimal(squirrel);
    }

    public boolean update(Squirrel squirrel) throws SQLException, ClassNotFoundException{
        Squirrel squirrelFromDB = getById(squirrel.getId());
        if(squirrelFromDB == null)
            throw new NotFoundException("Squirrel with id " + squirrel.getId() + " not found");
        squirrelRepository.update(squirrel);
        sqlSession.commit();
        return true;
    }

    public boolean setDeleteStatus(Long id, boolean isDeleted) throws SQLException, ClassNotFoundException{
        Squirrel squirrelFromDB = squirrelRepository.findById(id);
        if(squirrelFromDB == null)
            throw new NotFoundException("Squirrel with id " + id + " not found");
        squirrelRepository.setDeleteStatus(id, isDeleted);
        sqlSession.commit();
        return true;
    }

    public boolean hardDelete(Long id) throws SQLException, ClassNotFoundException{
        squirrelRepository.hardDelete(id);
        sqlSession.commit();
        return true;
    }
}
