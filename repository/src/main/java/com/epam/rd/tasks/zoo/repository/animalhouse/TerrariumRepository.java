package com.epam.rd.tasks.zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;

import java.sql.SQLException;

public interface TerrariumRepository {

    public void create(Terrarium terrarium) throws SQLException;
    public Terrarium getById(Long id) throws SQLException;
    public void update(Long id);
    public void delete(Long id);
}
