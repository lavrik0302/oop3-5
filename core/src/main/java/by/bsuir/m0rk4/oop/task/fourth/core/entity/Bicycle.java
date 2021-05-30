package by.bsuir.m0rk4.oop.task.fourth.core.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Bicycle extends NonMechanicalVehicle {
    private int wheelsCount;
    private boolean hasMultiSeat;

    @JsonCreator
    public Bicycle(
            @JsonProperty("maxSpeed") double maxSpeed,
            @JsonProperty("weight") double weight,
            @JsonProperty("brandName") String brandName,
            @JsonProperty("muscularPower") MuscularPower muscularPower,
            @JsonProperty("wheelsCount") int wheelsCount,
            @JsonProperty("hasMultiSeat") boolean hasMultiSeat) {
        super(maxSpeed, weight, brandName, muscularPower);
        this.hasMultiSeat = hasMultiSeat;
        this.wheelsCount = wheelsCount;
    }

    public int getWheelsCount() {
        return wheelsCount;
    }

    public void setWheelsCount(int wheelsCount) {
        this.wheelsCount = wheelsCount;
    }

    public boolean hasMultiSeat() {
        return hasMultiSeat;
    }

    public void setHasMultiSeat(boolean multiSeat) {
        hasMultiSeat = multiSeat;
    }
}
