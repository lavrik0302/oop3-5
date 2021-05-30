import by.bsuir.m0rk4.oop.task.fourth.core.service.DataArchivator;
import by.bsuir.m0rk4.oop.task.fourth.core.service.XmlToJsonMapper;

module core {

    exports by.bsuir.m0rk4.oop.task.fourth.core;

    requires javafx.controls;
    requires javafx.fxml;

    opens by.bsuir.m0rk4.oop.task.fourth.core.controller to javafx.fxml;


    exports by.bsuir.m0rk4.oop.task.fourth.core.service;

    exports by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.impl;
    exports by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization;

    exports by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.impl;
    exports by.bsuir.m0rk4.oop.task.fourth.core.data.serialization;

    exports by.bsuir.m0rk4.oop.task.fourth.core.entity;


    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.module.jaxb;

    opens by.bsuir.m0rk4.oop.task.fourth.core.entity to com.fasterxml.jackson.databind;

    uses XmlToJsonMapper;
    uses DataArchivator;
}