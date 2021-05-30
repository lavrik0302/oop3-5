package by.bsuir.m0rk4.oop.task.fourth.core.service;

import java.io.File;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

public interface XmlToJsonMapper {
    void map(File xmlFile);

    static Optional<XmlToJsonMapper> getService(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, XmlToJsonMapper.class)
                .stream()
                .map(Provider::get)
                .findFirst();
    }
}
