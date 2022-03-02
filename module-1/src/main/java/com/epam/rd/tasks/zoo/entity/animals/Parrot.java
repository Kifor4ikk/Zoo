package com.epam.rd.tasks.zoo.entity.animals;

import com.epam.rd.tasks.zoo.entity.Animal;
import com.epam.rd.tasks.zoo.entity.animals.behaviour.Flying;
import com.epam.rd.tasks.zoo.entity.animals.behaviour.Walking;

public class Parrot extends Animal implements Walking, Flying {

    public Parrot(String name, int age, String describe) {
        super(name, age, describe);
    }

    @Override
    public void sound() {
        System.out.println("Parrot " + super.getName() + " make sound");
    }

    @Override
    public void eat(String food) {
        System.out.println("Parrot " + super.getName() + " is eating");
    }

    @Override
    public void fly() {
        System.out.println("ClownFish " + super.getName() + " is flying");
    }

    @Override
    public void walk() {
        System.out.println("ClownFish " + super.getName() + " is walking");
    }
}
