package com.epam.rd.tasks.zoo.entity;

import java.math.BigDecimal;
import java.util.*;

public class Zoo {

    private Integer id;
    private String name;
    private Address address;
    private String description;
    private String contactInfo;
    private BigDecimal budget = BigDecimal.ZERO;
    private final Set<Animal> animals = new HashSet<>();
    private final List<AnimalHouse> animalsHouse = new ArrayList<>();
    private final Map<Animal,AnimalHouse> animalInHouse = new HashMap<>();
    private final Map<String, Integer> food = new HashMap<>();
    private final List<Ticket> soldTickets = new ArrayList<>();
    private final List<Expense> expenses = new ArrayList<>();
    private final List<VisitorReview> visitorReviews = new ArrayList<>();

    public Zoo(Integer id,String name, Address address, String description, String contactInfo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.contactInfo = contactInfo;
    }

    public List<Ticket> getSoldTickets() {
        return soldTickets;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public List<AnimalHouse> getAnimalsHouse() {
        return animalsHouse;
    }

    public Map<String, Integer> getFood() {
        return food;
    }

    public Map<Animal, AnimalHouse> getAnimalInHouse() {
        return animalInHouse;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", budget=" + budget +
                ", description='" + description + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", animalsHouse=" + animalsHouse +
                ", food=" + food +
                ", soldTickets=" + soldTickets +
                ", animals in cage= " + animalInHouse +
                '}' + "\n";
    }
}
