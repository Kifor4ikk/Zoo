package com.epam.rd.tasks.zoo.entity;

import java.util.ArrayList;
import java.util.List;

public class AnimalHouse {

    private Integer id;
    private String name;
    private int area;

    public AnimalHouse(Integer id, String name, int area) {
        this.id = id;
        this.name = name;
        this.area = area;
    }

    @Override
    public String toString() {
        return "\nAnimalHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                '}';
    }
}
