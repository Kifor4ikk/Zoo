package com.epam.rd.tasks.zoo.service.animal.bird.finch;

import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.bird.bullfinch.BullfinchRepositoryImpl;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class BullfinchService {

    private BullfinchRepositoryImpl bullfinchRepository;

    private AnimalTypeRepositoryImpl animalTypeRepository;

    private SqlSession sqlSession;

    public BullfinchService(BullfinchRepositoryImpl bullfinchRepository, AnimalTypeRepositoryImpl animalTypeRepository, SqlSession sqlSession) {
        this.bullfinchRepository = bullfinchRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.sqlSession = sqlSession;
    }

    public boolean create(Bullfinch bullfinch, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        bullfinchRepository.create(bullfinch,animalHouse);
        sqlSession.commit();
        return true;
    }

    public Bullfinch getById(Long id) throws SQLException, ClassNotFoundException {
        Bullfinch bullfinch = bullfinchRepository.findById(id);
        if(bullfinch == null)
            throw new NotFoundException("Bullfinch with id " + id + " not found");
        sqlSession.commit();
        return animalTypeRepository.putInfoAboutTypeOfAnimalByTypeOfAnimal(bullfinch);
    }

    public boolean update(Bullfinch bullfinch) throws SQLException, ClassNotFoundException{
        Bullfinch bullfinchFromDB = getById(bullfinch.getId());
        if(bullfinchFromDB == null)
            throw new NotFoundException("Bullfinch with id " + bullfinch.getId() + " not found");
        bullfinchRepository.update(bullfinch);
        sqlSession.commit();
        return true;
    }

    public boolean setDeleteStatus(Long id, boolean isDeleted) throws SQLException, ClassNotFoundException{
        Bullfinch bullfinchFromDB = bullfinchRepository.findById(id);
        if(bullfinchFromDB == null)
            throw new NotFoundException("Bullfinch with id " + id + " not found");
        bullfinchRepository.setDeleteStatus(id, isDeleted);
        sqlSession.commit();
        return true;
    }

    public boolean hardDelete(Long id) throws SQLException, ClassNotFoundException{
        bullfinchRepository.hardDelete(id);
        sqlSession.commit();
        return true;
    }
}
