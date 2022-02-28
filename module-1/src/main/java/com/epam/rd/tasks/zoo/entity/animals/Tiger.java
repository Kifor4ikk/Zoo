package com.epam.rd.tasks.zoo.entity.animals;

import com.epam.rd.tasks.zoo.entity.Animal;

public class Tiger extends Animal {

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
}