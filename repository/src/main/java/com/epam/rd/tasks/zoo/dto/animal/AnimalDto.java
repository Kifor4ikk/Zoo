package com.epam.rd.tasks.zoo.dto.animal;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.dto.animal.crustacean.CrustaceanDto;
import com.epam.rd.tasks.zoo.food.Food;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

    public static AnimalDto toDTO(Animal animal) {

        AnimalDto dto = new AnimalDto();

        dto.setName(animal.getName());
        dto.setDescribe(animal.getDescribe());
        dto.setAge(animal.getAge());
        dto.setLivingZone(animal.getLivingZone().getName());
        dto.setClimateZone(animal.getClimateZone().stream().map(Enum::name).collect(Collectors.toList()));
        dto.setFoodType(animal.getFoodType().getName());
        dto.setDeleted(animal.isDeleted());
        dto.setTypeOfAnimal(animal.getClass().getName());
        return dto;
    }

    public static <T extends Animal, Z extends AnimalDto> T fromDto(Z dto) throws ClassNotFoundException {
        T animal = null;
        try{
            Constructor<?> constructor = Class.forName(dto.getTypeOfAnimal()).getConstructor();
            animal = (T) constructor.newInstance();

            animal.setName(dto.getName());
            animal.setDescribe(dto.getDescribe());
            animal.setAge(dto.getAge());
            animal.setLivingZone((Class<? extends AnimalHouse>) Class.forName(dto.getLivingZone()));
            animal.setClimateZone(dto.getClimateZone().stream().map(ClimateZone::valueOf).collect(Collectors.toList()));
            animal.setFoodType((Class<? extends Food>)Class.forName(dto.getFoodType()));
            animal.setDeleted(dto.isDeleted());
            animal.setId(dto.getId());

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            System.err.println("->" + e);
        }
        return animal;
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
