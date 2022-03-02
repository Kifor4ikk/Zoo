package com.epam.rd.tasks.zoo.entity.animals;

import com.epam.rd.tasks.zoo.entity.Animal;
import com.epam.rd.tasks.zoo.entity.animals.behaviour.Flying;
import com.epam.rd.tasks.zoo.entity.animals.behaviour.Swimming;
import com.epam.rd.tasks.zoo.entity.animals.behaviour.Walking;

public class Duck extends Animal implements Walking, Flying, Swimming {

    public Duck(String name, int age, String describe) {
        super(name, age, describe);
    }

    @Override
    public void sound() {
        System.out.println("Duck " + super.getName() + " make kryak");
    }

    @Override
    public void eat(String food) {
        System.out.println("Duck " + super.getName() + " is eating");
    }

    @Override
    public void fly() {
        System.out.println("Duck " + super.getName() + " is flying");
    }

    @Override
    public void swim() {
        System.out.println("Duck " + super.getName() + " is swimming");
    }

    @Override
    public void walk() {
        System.out.println("Duck " + super.getName() + " is walking");
    }
}
