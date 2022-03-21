package com.epam.rd.tasks.zoo.animals;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Food;

import java.util.List;

public abstract class Animal {

    private Long id;
    private String name;
    private String describe;
    private int age;
    private Class<? extends AnimalHouse> livingZone;
    private List<ClimateZone> climateZone;
    private Class<? extends Food> foodType;

    public Animal(String name, String describe, int age, Class<? extends AnimalHouse> livingZone,
                  List<ClimateZone> climateZone, Class<? extends Food> foodType) {
        this.name = name;
        this.describe = describe;
        this.age = age;
        this.livingZone = livingZone;
        this.climateZone = climateZone;
        this.foodType = foodType;
    }

    public Long getId() {
        return id;
    }

    //@TODO при добавлении БАЗЫ ДАННЫХ УБРАТЬ ЭТУ ФУНКЦИЮ
    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 0) throw new WrongAgeException("Age should be more than 0");
        this.age = age;
    }

    public Class<? extends AnimalHouse> getLivingZone() {
        return livingZone;
    }

    public List<ClimateZone> getClimateZone() {
        return climateZone;
    }

    public Class<? extends Food> getFoodType() {
        return foodType;
    }

    public void setFoodType(Class<? extends Food> foodType) {
        this.foodType = foodType;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", age=" + age +
                ", livingZone=" + livingZone +
                ", climateZone=" + climateZone +
                ", foodType=" + foodType +
                ", class=" + this.getClass() +
                '}';
    }
}
