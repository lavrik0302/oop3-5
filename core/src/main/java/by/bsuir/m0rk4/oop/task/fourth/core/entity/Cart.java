package by.bsuir.m0rk4.oop.task.fourth.core.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cart extends NonMechanicalVehicle {
    private int peopleCapacity;
    private int haulersCount;

    @JsonCreator
    public Cart(
            @JsonProperty("maxSpeed") double maxSpeed,
            @JsonProperty("weight") double weight,
            @JsonProperty("brandName") String brandName,
            @JsonProperty("muscularPower") MuscularPower muscularPower,
            @JsonProperty("peopleCapacity") int peopleCapacity,
            @JsonProperty("haulersCount") int haulersCount) {
        super(maxSpeed, weight, brandName, muscularPower);
        this.peopleCapacity = peopleCapacity;
        this.haulersCount = haulersCount;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public void setPeopleCapacity(int peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
    }

    public int getHaulersCount() {
        return haulersCount;
    }

    public void setHaulersCount(int haulersCount) {
        this.haulersCount = haulersCount;
    }
}
