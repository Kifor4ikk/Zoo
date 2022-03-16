package com.epam.rd.tasks.zoo;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.finance.Finance;
import com.epam.rd.tasks.zoo.finance.income.Income;
import com.epam.rd.tasks.zoo.food.Food;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zoo {
    private Long id;
    private String name;
    private String describe;
    private String contactInfo;
    private BigDecimal budget;
    private Address address;
    private final List<AnimalHouse> animalHouses = new ArrayList<>();
    private final Map<Food, Integer> foodCount = new HashMap<>();
    private final Map<Animal,AnimalHouse> animals = new HashMap<>();
    private final List<Finance> finance = new ArrayList<>();
    private final List<VisitorReview> visitorReviews = new ArrayList<>();
}

class Address{
    private String country;
    private String city;
    private String street;
    private String houseNumber;

    public Address(String country, String city, String street, String houseNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}

class VisitorReview{
    private String visitorName;
    private String review;
    private LocalDateTime visitTime;

    public VisitorReview(String visitorName, String review, LocalDateTime visitTime) {
        this.visitorName = visitorName;
        this.review = review;
        this.visitTime = visitTime;
    }
}
