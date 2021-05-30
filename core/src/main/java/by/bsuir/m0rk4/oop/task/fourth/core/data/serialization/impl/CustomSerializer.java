package by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.impl;

import by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.VehicleSerializer;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class CustomSerializer implements VehicleSerializer {
    @Override
    public void serializeVehicles(File file, List<TransportVehicle> transportVehicles) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
            String line = mapper.writeValueAsString(transportVehicles);
            Path path = file.toPath();
            Files.write(path, Collections.singletonList(line), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
