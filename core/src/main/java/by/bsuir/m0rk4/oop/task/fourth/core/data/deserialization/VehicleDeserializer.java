package by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization;

import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;

import java.io.File;
import java.util.List;

public interface VehicleDeserializer {
    List<TransportVehicle> deserializeVehicles(File file);
}
