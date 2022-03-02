package com.epam.rd.tasks.zoo.entity.animals;

import com.epam.rd.tasks.zoo.entity.Animal;
import com.epam.rd.tasks.zoo.entity.animals.behaviour.Walking;

public class Rabbit extends Animal implements Walking {
    public Rabbit(String name, int age, String describe) {
        super(name, age, describe);
    }

    @Override
    public void sound() {
        System.out.println("Rabbit " + super.getName() + " squeek");
    }

    @Override
    public void eat(String food) {
        System.out.println("Rabbit " + super.getName() + " eating " + food);
    }

    @Override
    public void walk() {
        System.out.println("Rabbit " + super.getName() + " is jumping");
    }
}
