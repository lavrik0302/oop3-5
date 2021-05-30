package by.bsuir.m0rk4.oop.task.fourth.core.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Motorcycle.class, name = "moto"),
        @JsonSubTypes.Type(value = Tractor.class, name = "tractor"),
        @JsonSubTypes.Type(value = SportCar.class, name = "sport")
})
public abstract class MechanicalVehicle extends TransportVehicle  {
    private Engine engine;

    public MechanicalVehicle(double maxSpeed, double weight, String brandName, Engine engine) {
        super(maxSpeed, weight, brandName);
        this.engine = engine;
    }

    public MechanicalVehicle() {
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
