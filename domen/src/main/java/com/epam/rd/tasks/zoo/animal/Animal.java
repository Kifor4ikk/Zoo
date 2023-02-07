package com.epam.rd.tasks.zoo.animal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.exception.WrongAgeException;
import com.epam.rd.tasks.zoo.food.Food;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class Animal {

    private Long id;
    private String name;
    private String describe;
    private int age;
    private Set<Class<? extends AnimalHouse>> livingZone = new HashSet<>();
    private Set<ClimateZone> climateZone = new HashSet<>();
    private Set<Class<? extends Food>> foodType = new HashSet<>();
    private Date date;
    private boolean isDeleted = false;

    private String typeOfAnimal = null;
    public Animal(){}

    public Animal(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType) {
        this.name = name;
        this.describe = describe;
        this.age = age;
        this.livingZone = livingZone;
        this.climateZone = climateZone;
        this.foodType = foodType;
        this.typeOfAnimal = this.getClass().getName();
    }

    public Animal(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, boolean isDeleted) {
        this(name,describe,age,livingZone,climateZone,foodType);
        this.isDeleted = isDeleted;
    }

    public Animal(String name, String describe, int age, Set<Class<? extends AnimalHouse>> livingZone, Set<ClimateZone> climateZone, Set<Class<? extends Food>> foodType, Date date, boolean isDeleted) {
        this(name,describe,age,livingZone,climateZone,foodType,isDeleted);
        this.date = date;
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

    public Set<Class<? extends AnimalHouse>> getLivingZone() {
        return livingZone;
    }

    public Set<ClimateZone> getClimateZone() {
        return climateZone;
    }

    public Set<Class<? extends Food>> getFoodType() {
        return foodType;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setLivingZone(Set<Class<? extends AnimalHouse>> livingZone) {
        this.livingZone = livingZone;
    }

    public void setClimateZone(Set<ClimateZone> climateZone) {
        this.climateZone = climateZone;
    }

    public void setFoodType(Set<Class<? extends Food>> foodType) {
        this.foodType = foodType;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
