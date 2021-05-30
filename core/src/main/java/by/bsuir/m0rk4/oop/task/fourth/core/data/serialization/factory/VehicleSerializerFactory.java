package by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.factory;

import by.bsuir.m0rk4.oop.task.fourth.core.data.VehicleFileProcessType;
import by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.VehicleSerializer;
import by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.impl.BinarySerializer;
import by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.impl.CustomSerializer;
import by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.impl.XmlSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ObjectOutputStream;

public class VehicleSerializerFactory {
    private static VehicleSerializerFactory vehicleSerializerFactory;
    public VehicleSerializerFactory(){

    }
    public static synchronized VehicleSerializerFactory getVehicleSerializerFactory(){
        if (vehicleSerializerFactory==null) {
            vehicleSerializerFactory = new VehicleSerializerFactory();
        }
        return vehicleSerializerFactory;
    }
    public VehicleSerializer createVehicleSerializer(VehicleFileProcessType processType) {
        switch (processType) {
            case BINARY:
                return new BinarySerializer();
            case XML:
                return new XmlSerializer(new XmlMapper());
            case CUSTOM:
                return new CustomSerializer();
            default:
                throw new EnumConstantNotPresentException(processType.getClass(), processType.name());
        }
    }
}
