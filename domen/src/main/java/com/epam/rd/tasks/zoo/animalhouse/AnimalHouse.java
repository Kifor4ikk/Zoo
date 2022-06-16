package com.epam.rd.tasks.zoo.animalhouse;

import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.exception.BadAnimalTypeException;
import com.epam.rd.tasks.zoo.exception.BadClimateException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AnimalHouse {
    private Long id;
    private String name;
    private Integer area;
    private List<Class<? extends Animal>> typeOfAnimal = new ArrayList<>();
    private List<Animal> animals = new ArrayList<>();
    private ClimateZone climateZone;
    private boolean isDeleted = false;

    public AnimalHouse(Long id, String name, Integer area,
                       List<Class<? extends Animal>> typeOfAnimal, ClimateZone climateZone) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.typeOfAnimal = typeOfAnimal;
        this.climateZone = climateZone;
    }

    public AnimalHouse() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
            if (!typeOfAnimal.containsAll(animals.stream()
                    .map(Animal::getClass)
                    .collect(Collectors.toList())))
                throw new BadAnimalTypeException("Cant change animal type in cage " + getName() + " cause another animal still living here!");
        }
        this.typeOfAnimal = typeOfAnimal;
    }

    public void setClimateZone(ClimateZone climateZone) {
        if (!animals.isEmpty()) {
            // есть вариант с темпорари листом, чтобы конкретно говорить какие типы животных тут ещё живут
            if (animals.stream()
                    .map(Animal::getClimateZone)
                    .collect(Collectors.toList())
                    .stream().anyMatch(climateZones -> !climateZones.contains(climateZone)))
                throw new BadClimateException("Cant change climate zone in cage " + getName() + " cause another animal still living here!");
        }
        this.climateZone = climateZone;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public ClimateZone getClimateZone() {
        return climateZone;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void addAnimal(Animal animal) {
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

    @Override
    public String toString() {
        return "AnimalHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", typeOfAnimal=" + typeOfAnimal +
                ", animals=" + animals +
                ", climateZone=" + climateZone +
                ", isDeleted=" + isDeleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalHouse that = (AnimalHouse) o;
        return isDeleted == that.isDeleted && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(area, that.area) && Objects.equals(typeOfAnimal, that.typeOfAnimal) && Objects.equals(animals, that.animals) && climateZone == that.climateZone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, area, typeOfAnimal, animals, climateZone, isDeleted);
    }
}
