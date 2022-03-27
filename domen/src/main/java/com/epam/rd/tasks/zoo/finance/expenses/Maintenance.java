package com.epam.rd.tasks.zoo.finance.expenses;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Maintenance extends Expenses{

    public Maintenance(String describe, LocalDateTime localDateTime, BigDecimal cost, AnimalHouse animalHouse) {
        super(describe, localDateTime, cost);
        this.animalHouse = animalHouse;
    }

    public AnimalHouse getAnimalHouse() {
        return animalHouse;
    }

    private AnimalHouse animalHouse;
}
