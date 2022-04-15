package com.epam.rd.tasks.zoo.dto.animal;

import com.epam.rd.tasks.zoo.animals.Animal;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class AnimalDto {

    private String typeOfAnimal;
    private Long id;
    private String name;
    private String describe;
    private int age;
    private String livingZone;
    private List<String> climateZone;
    private String foodType;
    private boolean isDeleted = false;

    public static AnimalDto toDTO(Animal animal){
        return AnimalDto.builder()
                .name(animal.getName())
                .describe(animal.getDescribe())
                .age(animal.getAge())
                .livingZone(animal.getLivingZone().getName())
                .climateZone(animal.getClimateZone().stream().map(Enum::name).collect(Collectors.toList()))
                .foodType(animal.getFoodType().getName())
                .isDeleted(animal.isDeleted())
                .typeOfAnimal(animal.getClass().toString())
                .build();
    }


//    public static Animal fromDTO(AnimalDto animalDto) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        return new Animal(animalDto.getName(),
//                animalDto.getDescribe(),animalDto.getAge(),(Class<? extends AnimalHouse>) Class.forName(animalDto.getLivingZone()),
//                Arrays.stream(animalDto.getClimateZone()).map(s -> ClimateZone.valueOf(s)).collect(Collectors.toList()),
//                (Class<? extends Food>) Class.forName(animalDto.getFoodType()));
//    }

}
