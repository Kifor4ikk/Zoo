package com.epam.rd.tasks.zoo.service.animal.bird.finch;

import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.repository.animals.animaltype.AnimalTypeRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animals.bird.BullfinchRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;

public class BullfinchService {

    private BullfinchRepository bullfinchRepository;
    private AnimalTypeRepositoryImpl animalTypeRepository;

    public BullfinchService(SqlSessionFactory session, AnimalTypeRepositoryImpl animalTypeRepository) {
        this.bullfinchRepository = session.openSession().getMapper(BullfinchRepository.class);
        this.animalTypeRepository = animalTypeRepository;
    }

    public boolean create(Bullfinch bullfinch, AnimalHouse animalHouse) throws SQLException, ClassNotFoundException {
        bullfinchRepository.create(bullfinch, animalHouse);
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
