package com.epam.rd.tasks.zoo.repository.animal;

public class AnimalIdTypeAndHouseId {

    private Long id;
    private String type;
    private Long houseId;

    public AnimalIdTypeAndHouseId() {
    }

    public AnimalIdTypeAndHouseId(Long id, String type, Long houseId) {
        this.id = id;
        this.type = type;
        this.houseId = houseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
}