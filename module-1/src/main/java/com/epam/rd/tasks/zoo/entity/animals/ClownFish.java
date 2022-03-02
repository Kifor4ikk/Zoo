package com.epam.rd.tasks.zoo.entity.animals;

import com.epam.rd.tasks.zoo.entity.Animal;
import com.epam.rd.tasks.zoo.entity.animals.behaviour.Swimming;

public class ClownFish extends Animal implements Swimming{

    public ClownFish(String name, int age, String describe) {
        super(name, age, describe);
    }

    @Override
    public void sound() {
        System.out.println("ClownFish " + super.getName() + " make bloob");
    }

    @Override
    public void eat(String food) {
        System.out.println("ClownFish " + super.getName() + " is eating");
    }

    @Override
    public void swim() {
        System.out.println("ClownFish " + super.getName() + " is swimming");
    }
}
