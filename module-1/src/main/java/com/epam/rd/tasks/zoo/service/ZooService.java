package com.epam.rd.tasks.zoo.service;

import com.epam.rd.tasks.zoo.entity.*;
import com.epam.rd.tasks.zoo.exception.NotFoundException;
import com.epam.rd.tasks.zoo.exception.WrongEnterException;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ZooService {

    private static final List<Zoo> ZOO_LIST = new ArrayList<>();

    //    private String name;
    //    private Address address;
    //    private String description;
    //    private String contactInfo;
    public void create(String name, String country, String city,
                       String street, String houseNumber,
                       String description, String contactInfo) {
        if (name.length() > 256 || name.length() < 4)
            throw new WrongEnterException("Name size should be between 4 and 256");
        if (country.length() > 256 || country.length() < 4)
            throw new WrongEnterException("Country size should be between 4 and 256");
        if (city.length() > 256 || city.length() < 4)
            throw new WrongEnterException("City size should be between 4 and 256");
        if (street.length() > 256 || street.length() < 4)
            throw new WrongEnterException("Street size should be between 4 and 256");
        if (houseNumber.length() > 10 || houseNumber.length() < 1)
            throw new WrongEnterException("House size should be between 1 and 10");
        if (description.length() > 2048 || description.length() < 10)
            throw new WrongEnterException("Description size should be between 10 and 2048");
        if (contactInfo.length() > 512 || contactInfo.length() < 10)
            throw new WrongEnterException("Contact info size should be between 10 and 512");
        ZOO_LIST.add(new Zoo((ZOO_LIST.size()), name, new Address(country, city, street, houseNumber), description, contactInfo));
    }

    public List<Zoo> getZooList() {
        return ZOO_LIST;
    }

    public Zoo getZooById(Integer id) {
        return ZOO_LIST.get(id);
    }

    public void addAnimal(Integer zooId, Animal animal) {
        animal.setId(ZOO_LIST.get(zooId).getAnimals().size());
        ZOO_LIST.get(zooId).getAnimals().add(animal);
    }

    public void changeAnimal(Integer zooId, Integer animalId, String name, Integer age, String describe) {
        Animal changeAnimal =
                ZOO_LIST.get(zooId).getAnimals()
                        .stream()
                        .filter(animal -> Objects.equals(animal.getId(), animalId))
                        .collect(Collectors.toList())
                        .get(0);
        if (name != null) changeAnimal.setName(name);
        if (age > 0 && age < 200) changeAnimal.setAge(age);
        if (describe != null) changeAnimal.setAge(age);
    }

    public Animal getAnimalById(Integer zooId, Integer animalId) {
        return ZOO_LIST.get(zooId).getAnimals()
                .stream()
                .filter(animal -> Objects.equals(animal.getId(), animalId))
                .collect(Collectors.toList())
                .stream().findAny().orElseThrow();
    }

    public void addAnimalHouse(Integer zooId, String name, Integer area) {
        ZOO_LIST.get(zooId).getAnimalsHouse()
                .add(new AnimalHouse(ZOO_LIST.get(zooId).getAnimalsHouse().size(), name, area));
    }

    public void addAnimalToHouse(Integer zooId,Integer houseId, Integer animalId){
        ZOO_LIST.get(zooId).getAnimalInHouse().put(getAnimalById(zooId,animalId),ZOO_LIST.get(zooId).getAnimalsHouse().get(houseId));
    }

    public void addFood(Integer zooId, String name, Integer count) {
        if(count <= 0) throw new WrongEnterException("Count of food should be more than 0!");
        if (ZOO_LIST.get(zooId).getFood().containsKey(name))
            ZOO_LIST.get(zooId).getFood().put(name, count + ZOO_LIST.get(zooId).getFood().get(name));
        else
            ZOO_LIST.get(zooId).getFood().put(name, count);
    }

    public void sellTicket(Integer zooId, BigDecimal price, boolean isForChild, Integer count) {
        if (price.compareTo(BigDecimal.ZERO) < 1) throw new WrongEnterException("Price should be positive!");
        BigDecimal tempPrice = price;
        if (isForChild) tempPrice = price.divide(BigDecimal.valueOf(2));
        for (int i = 0; i < count; i++)
            ZOO_LIST.get(zooId).getSoldTickets().add(new Ticket(isForChild, tempPrice, LocalDateTime.now()));
    }

}
