package by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.impl;

import by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.VehicleDeserializer;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.Vehicles;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlDeserializer implements VehicleDeserializer {
      XmlMapper xmlMapper = new XmlMapper() ;

    @Override
    public List<TransportVehicle> deserializeVehicles(File file) {
        try {
            Vehicles vehicles = xmlMapper.readValue(file, Vehicles.class);
            return vehicles.getVehicles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
