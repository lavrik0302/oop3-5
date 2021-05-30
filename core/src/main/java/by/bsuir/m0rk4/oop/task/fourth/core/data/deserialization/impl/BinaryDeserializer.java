package by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.impl;

import by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.VehicleDeserializer;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class BinaryDeserializer implements VehicleDeserializer {
    @Override
    public List<TransportVehicle> deserializeVehicles(File file) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (List<TransportVehicle>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
