package com.epam.rd.tasks.zoo.dto.animal.crustacean;

import com.epam.rd.tasks.zoo.animalhouse.AnimalHouse;
import com.epam.rd.tasks.zoo.animalhouse.climate.ClimateZone;
import com.epam.rd.tasks.zoo.animals.crustacean.Crustacean;
import com.epam.rd.tasks.zoo.animals.crustacean.highercancers.Crab;
import com.epam.rd.tasks.zoo.dto.animal.AnimalDto;
import com.epam.rd.tasks.zoo.food.Food;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;

public class CrustaceanDto extends AnimalDto {

    private String seashell;
    public static CrustaceanDto toDTO(Crustacean animal) {

        CrustaceanDto dto = new CrustaceanDto();

        dto.setName(animal.getName());
        dto.setDescribe(animal.getDescribe());
        dto.setAge(animal.getAge());
        //dto.setLivingZone(animal.getLivingZone().getName());
        dto.setClimateZone(animal.getClimateZone().stream().map(Enum::name).collect(Collectors.toList()));
        //dto.setFoodType(animal.getFoodType().getName());
        dto.setDeleted(animal.isDeleted());
        dto.setTypeOfAnimal(animal.getClass().getName());
        dto.setSeashell(animal.getSeashell());

        return dto;
    }

    public static <T extends Crustacean, Z extends CrustaceanDto> T fromDto(Z dto) throws ClassNotFoundException {
        T animal = null;
        try{
            Constructor<?> constructor = Class.forName(dto.getTypeOfAnimal()).getConstructor();
            animal = (T) constructor.newInstance();

            animal.setName(dto.getName());
            animal.setDescribe(dto.getDescribe());
            animal.setAge(dto.getAge());
            //animal.setLivingZone((Class<? extends AnimalHouse>) Class.forName(dto.getLivingZone()));
            //animal.setClimateZone(dto.getClimateZone().stream().map(ClimateZone::valueOf).collect(Collectors.toList()));
            //animal.setFoodType((Class<? extends Food>)Class.forName(dto.getFoodType()));
            animal.setDeleted(dto.isDeleted());
            animal.setSeashell(dto.getSeashell());
            animal.setId(dto.getId());

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            System.err.println("->" + e);
        }
        return animal;
    }

    public CrustaceanDto() {}

    public CrustaceanDto(String seashell) {
        this.seashell = seashell;
    }

    public String getSeashell() {
        return seashell;
    }

    public void setSeashell(String seashell) {
        this.seashell = seashell;
    }

    @Override
    public String toString() {
        return "CrustaceanDto{|" + super.toString() +
                "seashell='" + seashell + '\'' +
                '}';
    }


}
