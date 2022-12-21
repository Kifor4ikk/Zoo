package com.epam.rd.tasks.zoo.repository.animals.animaltype;

public class AnimalType {
    private String climateZone;
    private String foodType;
    private String livingZone;


    public String getClimateZone() {
        return climateZone;
    }

    public void setClimateZone(String climateZone) {
        this.climateZone = climateZone;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getLivingZone() {
        return livingZone;
    }

    public void setLivingZone(String livingZone) {
        this.livingZone = livingZone;
    }

    @Override
    public String toString() {
        return "TestAnimalType{" +
                "\nclimateZone=" + climateZone +
                "\nfoodType=" + foodType +
                "\nlivingZone=" + livingZone +
                '}';
    }
}
