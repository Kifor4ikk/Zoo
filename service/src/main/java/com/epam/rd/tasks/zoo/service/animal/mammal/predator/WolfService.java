package com.epam.rd.tasks.zoo.service.animal.mammal.predator;
import com.epam.rd.tasks.zoo.animal.mammal.predator.Wolf;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.mammal.predator.WolfRepositoryImpl;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class WolfService {

    private WolfRepositoryImpl wolfRepository;
    private AnimalTypeRepositoryImpl animalTypeRepository;

    private SqlSession sqlSession;

    public WolfService(WolfRepositoryImpl wolfRepository, AnimalTypeRepositoryImpl animalTypeRepository, SqlSession sqlSession) {
        this.wolfRepository = wolfRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.sqlSession = sqlSession;
    }

    public boolean create(Wolf wolf, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        wolfRepository.create(wolf,animalHouse);
        sqlSession.commit();
        return true;
    }

    public Wolf getById(Long id) throws SQLException, ClassNotFoundException {
        Wolf wolf = wolfRepository.findById(id);
        if(wolf == null)
            throw new NotFoundException("Wolf with id " + id + " not found");
        sqlSession.commit();
        return animalTypeRepository.putInfoAboutTypeOfAnimalByTypeOfAnimal(wolf);
    }

    public boolean update(Wolf wolf) throws SQLException, ClassNotFoundException{
        Wolf wolfFromDB = getById(wolf.getId());
        if(wolfFromDB == null)
            throw new NotFoundException("Wolf with id " + wolf.getId() + " not found");
        wolfRepository.update(wolf);
        sqlSession.commit();
        return true;
    }

    public boolean setDeleteStatus(Long id, boolean isDeleted) throws SQLException, ClassNotFoundException{
        Wolf wolfFromDB = wolfRepository.findById(id);
        if(wolfFromDB == null)
            throw new NotFoundException("Wolf with id " + id + " not found");
        wolfRepository.setDeleteStatus(id, isDeleted);
        sqlSession.commit();
        return true;
    }

    public boolean hardDelete(Long id) throws SQLException, ClassNotFoundException{
        wolfRepository.hardDelete(id);
        sqlSession.commit();
        return true;
    }
}
