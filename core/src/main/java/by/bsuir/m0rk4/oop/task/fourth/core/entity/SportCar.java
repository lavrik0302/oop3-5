package by.bsuir.m0rk4.oop.task.fourth.core.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SportCar extends MechanicalVehicle {
    private boolean hasSpoiler;

    @JsonCreator
    public SportCar(
            @JsonProperty("maxSpeed") double maxSpeed,
            @JsonProperty("weight") double weight,
            @JsonProperty("brandName") String brandName,
            @JsonProperty("engine") Engine engine,
            @JsonProperty("hasSpoiler") boolean hasSpoiler) {
        super(maxSpeed, weight, brandName, engine);
        this.hasSpoiler = hasSpoiler;
    }

    public boolean hasSpoiler() {
        return hasSpoiler;
    }

    public void setHasSpoiler(boolean hasSpoiler) {
        this.hasSpoiler = hasSpoiler;
    }
}
