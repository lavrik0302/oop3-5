package by.bsuir.m0rk4.oop.task.fourth.core;

import by.bsuir.m0rk4.oop.task.fourth.core.controller.PrimaryController;
import by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.factory.VehicleDeserializerFactory;
import by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.factory.VehicleSerializerFactory;
import by.bsuir.m0rk4.oop.task.fourth.core.service.DataArchivator;
import by.bsuir.m0rk4.oop.task.fourth.core.service.XmlToJsonMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class App extends Application {

    private static final String FXML_VIEW_NAME = "primary.fxml";
    private static final String APP_TITLE = "OOP";
    private static final String PLUGINS_DIRECTORY = "plugins";
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 550;
    private ModuleLayer moduleLayer;

    @Override
    public void init() {
        Path path = Paths.get(PLUGINS_DIRECTORY);
        ModuleFinder pluginsFinder = ModuleFinder.of(path);
        List<String> plugins = pluginsFinder
                .findAll()
                .stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .collect(Collectors.toList());

        Configuration pluginsConfiguration = ModuleLayer
                .boot()
                .configuration()
                .resolve(pluginsFinder, ModuleFinder.of(), plugins);

        moduleLayer = ModuleLayer
                .boot()
                .defineModulesWithOneLoader(
                        pluginsConfiguration,
                        ClassLoader.getSystemClassLoader()
                );
    }

    @Override
    public void start(Stage stage) throws IOException {
        URL resource = App.class.getResource(FXML_VIEW_NAME);
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Optional<XmlToJsonMapper> xmlService = XmlToJsonMapper.getService(moduleLayer);
        Optional<DataArchivator> dataArchivatorService = DataArchivator.getService(moduleLayer);
        PrimaryController primaryController =
                new PrimaryController(VehicleSerializerFactory.getVehicleSerializerFactory(),VehicleDeserializerFactory.getVehicleDeserializerFactory());
        xmlService.ifPresent(primaryController::setXmlToJsonMapper);
        dataArchivatorService.ifPresent(primaryController::setDataArchivator);

        fxmlLoader.setControllerFactory(c -> primaryController);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        stage.setTitle(APP_TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}