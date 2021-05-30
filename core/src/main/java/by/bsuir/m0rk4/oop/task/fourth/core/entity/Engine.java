package by.bsuir.m0rk4.oop.task.fourth.core.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Engine implements Serializable {
    private int cylinderCount;
    private double power;

    @JsonCreator
    public Engine(
            @JsonProperty("cylindersCount") int cylindersCount,
            @JsonProperty("power") double power) {
        this.cylinderCount = cylindersCount;
        this.power = power;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public int getCylinderCount() {
        return cylinderCount;
    }

    public void setCylinderCount(int cylinderCount) {
        this.cylinderCount = cylinderCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return cylinderCount == engine.cylinderCount &&
                Double.compare(engine.power, power) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cylinderCount, power);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "cylinderCount=" + cylinderCount +
                ", power=" + power +
                '}';
    }
}
