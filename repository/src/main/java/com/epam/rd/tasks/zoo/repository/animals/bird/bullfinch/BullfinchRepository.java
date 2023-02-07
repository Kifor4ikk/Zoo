package com.epam.rd.tasks.zoo.repository.animals.bird.bullfinch;

import com.epam.rd.tasks.zoo.animal.Animal;
import com.epam.rd.tasks.zoo.animal.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepository;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface BullfinchRepository extends AnimalRepository<Bullfinch, Long> {

}
