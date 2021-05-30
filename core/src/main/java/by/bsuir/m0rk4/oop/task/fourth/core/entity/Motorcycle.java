package by.bsuir.m0rk4.oop.task.fourth.core.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Motorcycle extends MechanicalVehicle {
    private int passengersCount;
    private boolean hasFrontGlass;

    @JsonCreator
    public Motorcycle(
            @JsonProperty("maxSpeed") double maxSpeed,
            @JsonProperty("weight") double weight,
            @JsonProperty("brandName") String brandName,
            @JsonProperty("engine") Engine engine,
            @JsonProperty("passengersCount") int passengersCount,
            @JsonProperty("hasFrontGlass") boolean front) {
        super(maxSpeed, weight, brandName, engine);
        this.passengersCount = passengersCount;
        this.hasFrontGlass = front;
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }

    public boolean hasFrontGlass() {
        return hasFrontGlass;
    }

    public void setHasFrontGlass(boolean hasFrontGlass) {
        this.hasFrontGlass = hasFrontGlass;
    }

}
