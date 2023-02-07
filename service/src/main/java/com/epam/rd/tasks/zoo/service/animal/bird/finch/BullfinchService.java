package com.epam.rd.tasks.zoo.service.animal.bird.finch;

import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.bird.BirdRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.bird.bullfinch.BullfinchRepositoryImpl;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class BullfinchService {

    private BullfinchRepositoryImpl bullfinchRepository;
    private BirdRepositoryImpl birdRepository;

    private AnimalRepositoryImpl animalRepository;
    private AnimalTypeRepositoryImpl animalTypeRepository;

    private SqlSession sqlSession;

    public BullfinchService(BullfinchRepositoryImpl bullfinchRepository, BirdRepositoryImpl birdRepository, AnimalRepositoryImpl animalRepository, AnimalTypeRepositoryImpl animalTypeRepository, SqlSession sqlSession) {
        this.bullfinchRepository = bullfinchRepository;
        this.birdRepository = birdRepository;
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.sqlSession = sqlSession;
    }

    public boolean create(Bullfinch bullfinch, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        animalRepository.create(bullfinch, animalHouse);
        birdRepository.create(bullfinch, animalHouse);
        bullfinchRepository.create(bullfinch,animalHouse);
        sqlSession.commit();
        return true;
    }
    public Bullfinch getById(Long id) throws SQLException, ClassNotFoundException {
        Bullfinch bullfinch = bullfinchRepository.findById(id);
        if(bullfinch == null)
            throw new SQLException("Bullfinch with id " + id + " not found");

        return animalTypeRepository.putInfoAboutTypeOfAnimalByTypeOfAnimal(bullfinch);
    }

    public boolean update(Bullfinch bullfinch) throws SQLException, ClassNotFoundException{
        Bullfinch bullfinchFromDB = bullfinchRepository.findById(bullfinch.getId());
        if(bullfinchFromDB == null)
            throw new SQLException("Bullfinch with id " + bullfinch.getId() + " not found");
        bullfinchRepository.update(bullfinch);
        return true;
    }

    public boolean setDeleteStatus(Long id) throws SQLException, ClassNotFoundException{
        Bullfinch bullfinchFromDB = bullfinchRepository.findById(id);
        if(bullfinchFromDB == null)
            throw new SQLException("Bullfinch with id " + id + " not found");
        bullfinchRepository.setDeleteStatus(id,true);
        return true;
    }

    public boolean hardDelete(Long id) throws SQLException, ClassNotFoundException{
        bullfinchRepository.hardDelete(id);
        return true;
    }
}
