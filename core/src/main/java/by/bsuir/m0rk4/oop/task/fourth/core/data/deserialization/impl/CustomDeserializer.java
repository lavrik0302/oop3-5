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

public class CustomDeserializer implements VehicleDeserializer {
    private ObjectMapper mapper;

    public CustomDeserializer(ObjectMapper mapper) {
        this.mapper = mapper;

    }

    @Override
    public List<TransportVehicle> deserializeVehicles(File file) {
        mapper.enableDefaultTyping();
        try {
            List<String> strings = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            String reduce = strings.stream().reduce("", (s1, s2) -> s1 + s2);
            return mapper.readValue(reduce, LinkedList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
