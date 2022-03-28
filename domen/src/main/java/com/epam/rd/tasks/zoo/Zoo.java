package com.epam.rd.tasks.zoo;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animals.Animal;
import com.epam.rd.tasks.zoo.exception.WrongCountException;
import com.epam.rd.tasks.zoo.finance.Finance;
import com.epam.rd.tasks.zoo.finance.expenses.Expenses;
import com.epam.rd.tasks.zoo.finance.income.Income;
import com.epam.rd.tasks.zoo.food.Food;

import java.math.BigDecimal;
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
    private final Address address;
    private final List<AnimalHouse> animalHouses = new ArrayList<>();
    private final Map<Class<? extends Food>, Integer> foodCount = new HashMap<>();
    private final Map<Animal,AnimalHouse> animals = new HashMap<>();
    private final List<Finance> finance = new ArrayList<>();
    private final List<VisitorReview> visitorReviews = new ArrayList<>();

    public Zoo(String name, String describe, String contactInfo, BigDecimal budget,
               String country, String city, String street, String houseNumber) {
        this.name = name;
        this.describe = describe;
        this.contactInfo = contactInfo;
        this.budget = budget;
        this.address = new Address(country,city,street,houseNumber);
    }

    public Long getId() {
        return id;
    }

    //@TODO При БД удалить.
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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget){
        this.budget = budget;
    }

//    public void addMoneyToBudget(Income income){
//        if(income.getCost().compareTo(BigDecimal.ZERO) > 0)
//            this.budget = this.budget.add(income.getCost());
//        else
//            throw new WrongCountException("Amount should be more than 0");
//    }
//
//    public void removeMoneyFromBudget(Expenses expenses) {
//        if (expenses.getCost().compareTo(BigDecimal.ZERO) > 0) {
//            //@TODO ес че включить, если бюджет меньше нуля не может быть.
////            if(this.budget.subtract(expenses.getCost()).compareTo(BigDecimal.ZERO) < 0)
////                throw new WrongCountException("Budget cant be less than ZERO");
//            this.budget = this.budget.subtract(expenses.getCost());
//        } else {
//            throw new WrongCountException("Amount should be more than 0");
//        }
//    }

    public Address getAddress() {
        return address;
    }

    public List<AnimalHouse> getAnimalHouses() {
        return animalHouses;
    }


    public Map<Class<? extends Food>, Integer> getFoodCount() {
        return foodCount;
    }

    public Map<Animal, AnimalHouse> getAnimals() {
        return animals;
    }

    public List<Finance> getFinance() {
        return finance;
    }

    public List<VisitorReview> getVisitorReviews() {
        return visitorReviews;
    }
}


