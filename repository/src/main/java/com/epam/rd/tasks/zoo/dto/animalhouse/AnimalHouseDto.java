package com.epam.rd.tasks.zoo.dto.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.dto.animal.crustacean.CrustaceanDto;
import com.epam.rd.tasks.zoo.exception.ClassCastException;
import com.epam.rd.tasks.zoo.food.Food;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalHouseDto {

    private Long id;
    private String name;
    private Integer area;
    private List<Class<? extends Animal>> typeOfAnimals = new ArrayList<>();
    private String climateZone;
    private String typeOfHouse;
    private boolean isDeleted = false;

    //all animals living here
    private List<Animal> animals = new ArrayList<>();

    public AnimalHouseDto() {
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

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public List<Class<? extends Animal>> getTypeOfAnimals() {
        return typeOfAnimals;
    }

    public void setTypeOfAnimals(List<Class<? extends Animal>> typeOfAnimals) {
        this.typeOfAnimals = typeOfAnimals;
    }

    public String getClimateZone() {
        return climateZone;
    }

    public void setClimateZone(String climateZone) {
        this.climateZone = climateZone;
    }

    public String getTypeOfHouse() {
        return typeOfHouse;
    }

    public void setTypeOfHouse(String typeOfHouse) {
        this.typeOfHouse = typeOfHouse;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public static AnimalHouseDto toDto(AnimalHouse animalHouse) {
        AnimalHouseDto dto = new AnimalHouseDto();
        dto.setId(animalHouse.getId());
        dto.setName(animalHouse.getName());
        dto.setArea(animalHouse.getArea());
        dto.setTypeOfAnimals(animalHouse.getTypeOfAnimal());
        dto.setClimateZone(animalHouse.getClimateZone().name());
        dto.setTypeOfHouse(animalHouse.getClass().getName());
        dto.setAnimals(animalHouse.getAnimals());

        return dto;
    }

    public static <T extends AnimalHouse, Z extends AnimalHouseDto> T fromDto(Z dto) {
        T animalHouse = null;
        try {
            Constructor<?> constructor = Class.forName(dto.getTypeOfHouse()).getConstructor();
            animalHouse = (T) constructor.newInstance();

            animalHouse.setId(dto.getId());
            animalHouse.setName(dto.getName());
            animalHouse.setArea(dto.getArea());
            animalHouse.setTypeOfAnimal(dto.getTypeOfAnimals());
            animalHouse.setClimateZone(ClimateZone.valueOf(dto.getClimateZone()));
            animalHouse.setAnimals(dto.getAnimals());
        } catch (ClassNotFoundException e) {
            throw new ClassCastException("DTO have problem with converting class");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("->" + Arrays.toString(e.getStackTrace()));
        }
        return animalHouse;
    }

    @Override
    public String toString() {
        return "AnimalHouseDto{" + "id=" + id + ", name='" + name + '\'' + ", area=" + area + ", typeOfAnimal='" + typeOfAnimals + '\'' + ", climateZone='" + climateZone + '\'' + ", typeOfHouse='" + typeOfHouse + '\'' + ", isDeleted=" + isDeleted + ", animals=" + animals + '}';
    }
}
