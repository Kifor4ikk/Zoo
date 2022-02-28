package ru.kifor4ik.entity;

import java.util.*;

public class Zoo {

    private Integer id;
    private String name;
    private Address address;
    private String description;
    private String contactInfo;
    private final Set<Animal> animals = new HashSet<>();
    private final List<AnimalHouse> animalsHouse = new ArrayList<>();
    private final Map<String, Integer> food = new HashMap<>();
    private final List<Ticket> soldTickets = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Zoo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", description='" + description + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", animals=" + animals +
                ", animalsHouse=" + animalsHouse +
                ", food=" + food +
                ", soldTickets=" + soldTickets +
                '}' + "\n";
    }
}
