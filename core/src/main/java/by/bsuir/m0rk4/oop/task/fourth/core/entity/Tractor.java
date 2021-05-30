package by.bsuir.m0rk4.oop.task.fourth.core.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Tractor extends MechanicalVehicle {
    private double thrustForce;

    @JsonCreator
    public Tractor(
            @JsonProperty("maxSpeed") double maxSpeed,
            @JsonProperty("weight") double weight,
            @JsonProperty("brandName") String brandName,
            @JsonProperty("engine") Engine engine,
            @JsonProperty("thrustForce") double thrustForce) {
        super(maxSpeed, weight, brandName, engine);
        this.thrustForce = thrustForce;
    }

    public double getThrustForce() {
        return thrustForce;
    }

    public void setThrustForce(double thrustForce) {
        this.thrustForce = thrustForce;
    }
}
