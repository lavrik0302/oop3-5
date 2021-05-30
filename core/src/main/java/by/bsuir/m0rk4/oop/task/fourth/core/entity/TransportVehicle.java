package by.bsuir.m0rk4.oop.task.fourth.core.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "mech-type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MechanicalVehicle.class, name = "mechanical-vehicle"),
        @JsonSubTypes.Type(value = NonMechanicalVehicle.class, name = "non-mechanical-vehicle")
})
public abstract class TransportVehicle implements Serializable {
    private double maxSpeed;
    private double weight;
    private String brandName;


    public TransportVehicle(double maxSpeed, double weight, String brandName) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.brandName = brandName;
    }

    public TransportVehicle() {

    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return brandName + " - " + getClass().getSimpleName();
    }
}
