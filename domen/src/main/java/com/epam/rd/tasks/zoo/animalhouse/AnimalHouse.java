package com.epam.rd.tasks.zoo.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.exception.BadClimateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AnimalHouse {
    private Long id;
    private String name;
    private Integer area;
    private List<Class<? extends Animal>> typeOfAnimal;
    private List<Animal> animals = new ArrayList<>();
    private ClimateZone climateZone;

    //@TODO при добавлении БАЗЫ ДАННЫХ УБРАТЬ ID ИЗ КОНСТРУКТОРА
    public AnimalHouse(Long id, String name, Integer area,
                       List<Class<? extends Animal>> typeOfAnimal, ClimateZone climateZone) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.typeOfAnimal = typeOfAnimal;
        this.climateZone = climateZone;
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

    public List<Class<? extends Animal>> getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(List<Class<? extends Animal>> typeOfAnimal) {
        if (!animals.isEmpty()) {
            // есть вариант с темпорари листом, чтобы конкретно говорить какие типы животных тут ещё живут
            if(!typeOfAnimal.containsAll(animals.stream()
                    .map(Animal::getClass)
                    .collect(Collectors.toList())))
                throw new BadAnimalTypeException("Cant change animal type in cage " + getName() + " cause another animal still living here!");
        }
        this.typeOfAnimal = typeOfAnimal;
    }

    public void setClimateZone(ClimateZone climateZone) {
        if (!animals.isEmpty()) {
            // есть вариант с темпорари листом, чтобы конкретно говорить какие типы животных тут ещё живут
            if(animals.stream()
                    .map(Animal::getClimateZone)
                    .collect(Collectors.toList())
                    .stream().anyMatch(climateZones -> !climateZones.contains(climateZone)))
                throw new BadClimateException("Cant change climate zone in cage " + getName() + " cause another animal still living here!");
        }
        this.climateZone = climateZone;
    }

    public ClimateZone getClimateZone() {
        return climateZone;
    }

    public void addAnimal(Animal animal) {
        if (!animal.getClimateZone().contains(climateZone))
            throw new BadClimateException(animal.getName() + " cant live in " + name + " because bad climate!");
        if (!animal.getLivingZone().equals(this.getClass()))
            throw new BadClimateException(animal.getName() + " cant live in " + name + " because bad living zone!");
        if(!typeOfAnimal.contains(animal.getClass()))
            throw new BadAnimalTypeException(animal.getName() + " cant live in " + name + " because this cage for different type of animals!");
        animals.add(animal);
    }

    public void removeAnimal(Long animalId) {
        animals.remove(animals.stream()
                .filter(animal -> Objects.equals(animal.getId(), animalId))
                .findFirst().orElseThrow());
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
