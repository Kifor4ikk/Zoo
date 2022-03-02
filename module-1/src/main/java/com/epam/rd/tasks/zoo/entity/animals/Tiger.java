package com.epam.rd.tasks.zoo.entity.animals;

import com.epam.rd.tasks.zoo.entity.Animal;
import com.epam.rd.tasks.zoo.entity.animals.behaviour.Walking;

public class Tiger extends Animal implements Walking {

    public Tiger(String name, int age, String describe) {
        super(name, age, describe);
    }

    @Override
    public void sound() {
        System.out.println("The tiger " + super.getName() + " is roaring!");
    }

    @Override
    public void eat(String food) {
        System.out.println("The tiger " + super.getName() + " eating " + food);
    }

    @Override
    public void walk() {
        System.out.println("Tiger " + super.getName() + " is walking");
    }
}
