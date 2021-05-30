package by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.impl;

import by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.VehicleDeserializer;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProxyCustomDeserializer implements VehicleDeserializer {
    private CustomDeserializer customDeserializer ;
    private ObjectMapper mapper ;

    public ProxyCustomDeserializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<TransportVehicle> deserializeVehicles(File file) {
     if (customDeserializer == null){
         customDeserializer = new CustomDeserializer(mapper);
     }
     List<TransportVehicle> temp = customDeserializer.deserializeVehicles(file);
     for (TransportVehicle vi:temp){
         System.out.println(vi);
     }
     return temp;
    }
}
