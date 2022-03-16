package com.epam.rd.tasks.zoo.animalhouse.climate;

public enum ClimateZone {

    SUBARCTIC(new Climate(-55,+2)),
    ARCTIC(new Climate( -55, +2)),
    SUBANTARCTIC(new Climate(-55,+10)),
    ANTARCTIC(new Climate(0,0)),
    MODERATE(new Climate(0,0)),
    SUBTROPICAL(new Climate(0,0)),
    TROPICAL(new Climate(0,0)),
    SUBEQUATORIAL(new Climate(0,0)),
    EQUATORIAL(new Climate(0,0));;
    ClimateZone(Climate climate) {}
}

class Climate{
    private Integer tempMax;
    private Integer tempMin;

    public Climate(Integer tempMin, Integer tempMax) {
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    //... add another param as u wish
}