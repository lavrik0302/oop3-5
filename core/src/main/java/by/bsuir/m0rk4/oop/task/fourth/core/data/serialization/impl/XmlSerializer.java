package by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.impl;

import by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.VehicleSerializer;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.Vehicles;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlSerializer implements VehicleSerializer {
    private XmlMapper xmlMapper;
    public XmlSerializer(XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }

    @Override
    public void serializeVehicles(File file, List<TransportVehicle> transportVehicles) {
        Vehicles vehicles = new Vehicles(transportVehicles);
        try {
            this.xmlMapper.writeValue(file, vehicles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
