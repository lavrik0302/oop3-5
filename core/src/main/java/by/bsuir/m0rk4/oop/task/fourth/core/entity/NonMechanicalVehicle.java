package by.bsuir.m0rk4.oop.task.fourth.core.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bicycle.class, name = "bicycle"),
        @JsonSubTypes.Type(value = Cart.class, name = "cart")
})
public abstract class NonMechanicalVehicle extends TransportVehicle {
    private MuscularPower muscularPower;

    public NonMechanicalVehicle(double maxSpeed, double weight, String brandName, MuscularPower muscularPower) {
        super(maxSpeed, weight, brandName);
        this.muscularPower = muscularPower;
    }

    public NonMechanicalVehicle(){
    }
    public MuscularPower getMuscularPower() {
        return muscularPower;
    }

    public void setMuscularPower(MuscularPower muscularPower) {
        this.muscularPower = muscularPower;
    }
}
