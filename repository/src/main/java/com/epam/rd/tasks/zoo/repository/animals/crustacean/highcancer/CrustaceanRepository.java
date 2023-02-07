package com.epam.rd.tasks.zoo.repository.animals.crustacean.highcancer;

import com.epam.rd.tasks.zoo.animal.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.repository.animals.AnimalRepository;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface CrustaceanRepository extends AnimalRepository<Crustacean, Long> {
}
