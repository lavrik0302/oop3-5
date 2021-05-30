package by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.impl;

import by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.VehicleSerializer;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class BinarySerializer implements VehicleSerializer {
    private ObjectOutputStream objectOutputStream;
    @Override
    public void serializeVehicles(File file, List<TransportVehicle> transportVehicles) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(transportVehicles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
