package by.bsuir.m0rk4.oop.task.fourth.core.entity;

import java.util.List;

public class Vehicles {
    private List<TransportVehicle> vehicles;

    public Vehicles(List<TransportVehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Vehicles() {
    }

    public List<TransportVehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<TransportVehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
