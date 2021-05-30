package by.bsuir.m0rk4.oop.task.fourth.core.data.serialization;

import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;

import java.io.File;
import java.util.List;

public interface VehicleSerializer {
    void serializeVehicles(File file, List<TransportVehicle> transportVehicles);
}
