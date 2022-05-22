package com.epam.rd.tasks.zoo.dto.animal;

import com.epam.rd.tasks.zoo.animals.Animal;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalDto {

    private String typeOfAnimal;
    private Long id;
    private String name;
    private String describe;
    private int age;
    private String livingZone;
    private List<String> climateZone;
    private String foodType;
    private boolean isDeleted = false;

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        this.age = age;
    }

    public String getLivingZone() {
        return livingZone;
    }

    public void setLivingZone(String livingZone) {
        this.livingZone = livingZone;
    }

    public List<String> getClimateZone() {
        return climateZone;
    }

    public void setClimateZone(List<String> climateZone) {
        this.climateZone = climateZone;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "AnimalDto{" +
                "typeOfAnimal='" + typeOfAnimal + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", age=" + age +
                ", livingZone='" + livingZone + '\'' +
                ", climateZone=" + climateZone +
                ", foodType='" + foodType + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
