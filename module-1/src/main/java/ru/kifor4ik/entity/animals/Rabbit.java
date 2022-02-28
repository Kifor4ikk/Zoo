package ru.kifor4ik.entity.animals;

import ru.kifor4ik.entity.Animal;

public class Rabbit extends Animal {
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
}
