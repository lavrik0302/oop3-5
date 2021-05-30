package by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.factory;

import by.bsuir.m0rk4.oop.task.fourth.core.data.VehicleFileProcessType;
import by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.VehicleDeserializer;
import by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.impl.BinaryDeserializer;
import by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.impl.CustomDeserializer;
import by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.impl.ProxyCustomDeserializer;
import by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.impl.XmlDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class VehicleDeserializerFactory {
    private static VehicleDeserializerFactory vehicleDeserializerFactory;

    private VehicleDeserializerFactory() {
    }

    public static VehicleDeserializerFactory getVehicleDeserializerFactory(){
        if (vehicleDeserializerFactory==null){
            vehicleDeserializerFactory = new VehicleDeserializerFactory();
        }
        return vehicleDeserializerFactory;
    }
    public VehicleDeserializer createVehicleDeserializer(VehicleFileProcessType processType) {
        switch (processType) {
            case BINARY:
                return new BinaryDeserializer();
            case XML:
                return new XmlDeserializer();
            case CUSTOM:
                return new ProxyCustomDeserializer(new ObjectMapper());
            default:
                throw new EnumConstantNotPresentException(processType.getClass(), processType.name());
        }
    }
}
