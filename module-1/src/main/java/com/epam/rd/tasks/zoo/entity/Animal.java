package com.epam.rd.tasks.zoo.entity;

import java.util.Objects;

public abstract class Animal {

    private Integer id;
    private String name;
    private int age;
    private String describe;

    public Animal(String name, int age, String describe) {
        this.name = name;
        this.age = age;
        this.describe = describe;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void describe() {
        System.out.println(describe);
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age && Objects.equals(name, animal.name) && Objects.equals(describe, animal.describe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, describe);
    }

    @Override
    public String toString() {
        return "\nAnimal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", describe='" + describe + '\'' +
                '}';
    }
}
