package com.epam.rd.tasks.zoo.dto.animal;

import com.epam.rd.tasks.zoo.animals.Animal;

public class AnimalCreateDto {
    /*
    name,describe,age,id_animaltype,isdeleted
     */

    private String name;
    private String describe;
    private Integer age;
    private Long idAnimalType;
    private Class<? extends Animal> currentAnimalClass;
    private boolean isDeleted = false;

    public AnimalCreateDto() {
    }

    public AnimalCreateDto(String name, String describe, Integer age, Long idAnimalType, Class currentAnimalClass) {
        this.name = name;
        this.describe = describe;
        this.age = age;
        this.idAnimalType = idAnimalType;
        this.currentAnimalClass = currentAnimalClass;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getIdAnimalType() {
        return idAnimalType;
    }

    public void setIdAnimalType(Long idAnimalType) {
        this.idAnimalType = idAnimalType;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Class getCurrentAnimalClass() {
        return currentAnimalClass;
    }

    public void setCurrentAnimalClass(Class currentAnimalClass) {
        this.currentAnimalClass = currentAnimalClass;
    }
}
