import by.bsuir.m0rk4.oop.task.fourth.core.service.XmlToJsonMapper;
import by.bsuir.m0rk4.oop.task.fourth.xmltojson.XmlToJsonMapperImpl;

module xmltojson {
    requires core;

    provides XmlToJsonMapper with XmlToJsonMapperImpl;
}