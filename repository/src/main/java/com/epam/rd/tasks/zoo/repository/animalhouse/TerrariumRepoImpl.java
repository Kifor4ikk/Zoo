package com.epam.rd.tasks.zoo.repository.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animalhouse.zoneType.Terrarium;
import com.epam.rd.tasks.zoo.animals.Animal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TerrariumRepoImpl extends HouseRepoImpl implements TerrariumRepository {
    public TerrariumRepoImpl(Connection connection) {
        super(connection);
    }

    /*
    private Long id;
    private String name;
    private Integer area;
    private List<String> typeOfAnimal;
    //private List<Animal> animals = new ArrayList<>();
    private String climateZone;
    private boolean isDeleted = false;
    */

    @Override
    public void create(Terrarium terrarium) throws SQLException {
        //@TODO change to StringBuilder
        state().executeQuery("INSERT INTO terrariumhouse (name,area,animaltype,climatezone,isdeleted) VALUES ('" +
                terrarium.getName() + "','" +
                terrarium.getArea() + "','" +
                Arrays.toString(terrarium.getTypeOfAnimal().stream()
                        .map(Class::getName).toArray()) + "','" +
                terrarium.getClimateZone().name() + "','" +
                terrarium.isDeleted() + "');"
        );
    }

    @Override
    public Terrarium getById(Long id) throws SQLException {
        Terrarium terrarium = new Terrarium();
        try (ResultSet resultSet = state()
                .executeQuery("SELECT * FROM TerrariumHouse WHERE isDeleted = false AND id = " + id)) {
            if (resultSet.next()) {
                terrarium.setId(id);
                terrarium.setName(resultSet.getString("name"));
                terrarium.setArea(resultSet.getInt("area"));
                //@TODO исправить/улучшить/заменить/что-нибудь сделать с этим!
                List<String> list = Arrays.stream(resultSet.getString("animaltype")
                        .substring(1, resultSet.getString("animaltype").length() - 1)
                        .split(", ")).collect(Collectors.toList());
                List<Class<? extends Animal>> classes = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    classes.add((Class<? extends Animal>) Class.forName(list.get(i)));
                }
                terrarium.setTypeOfAnimal(classes);
                terrarium.setClimateZone(ClimateZone.valueOf(resultSet.getString("climateZone")));
                terrarium.setDeleted(resultSet.getBoolean("isDeleted"));
            }
            return terrarium;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return terrarium;
    }


    @Override
    public void update(Long id) {

    }

    @Override
    public void delete(Long id) {

    }
}
