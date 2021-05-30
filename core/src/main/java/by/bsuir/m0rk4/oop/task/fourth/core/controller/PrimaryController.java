package by.bsuir.m0rk4.oop.task.fourth.core.controller;

import by.bsuir.m0rk4.oop.task.fourth.core.components.*;
import by.bsuir.m0rk4.oop.task.fourth.core.data.VehicleFileProcessType;
import by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.VehicleDeserializer;
import by.bsuir.m0rk4.oop.task.fourth.core.data.deserialization.factory.VehicleDeserializerFactory;
import by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.VehicleSerializer;
import by.bsuir.m0rk4.oop.task.fourth.core.data.serialization.factory.VehicleSerializerFactory;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.*;
import by.bsuir.m0rk4.oop.task.fourth.core.service.DataArchivator;
import by.bsuir.m0rk4.oop.task.fourth.core.service.XmlToJsonMapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrimaryController {

    private final VehicleSerializerFactory serializerFactory;
    private final VehicleDeserializerFactory deserializerFactory;
    private VehicleFileProcessType vehicleFileProcessType = VehicleFileProcessType.BINARY;


    public CheckBox archivationCB;
    private XmlToJsonMapper xmlToJsonMapper;
    private DataArchivator dataArchivator;

    public RadioButton bicycleRB;
    public RadioButton cartRB;
    public RadioButton sportCarRB;
    public RadioButton tractorRB;
    public RadioButton motorcycleRB;

    public BorderPane formBorderPane;

    public Button saveButton;
    public Button loadButton;

    public Button saveVehicleButton;
    public Button addVehicleButton;
    public Button deleteVehicleButton;

    public Button convertXmlTOJsonButton;

    public TreeView<TransportVehicle> treeView;

    public RadioButton binaryRB;
    public RadioButton customRB;
    public RadioButton xmlRB;

    private final AbstractVehicleForm abstractVehicleForm = new BicycleForm();

    private List<TransportVehicle> transportVehicles;

    public PrimaryController(VehicleSerializerFactory serializerFactory, VehicleDeserializerFactory deserializerFactory) {
        this.serializerFactory = serializerFactory;
        this.deserializerFactory = deserializerFactory;
        this.transportVehicles = new ArrayList<>();
    }

    private final TreeItem<TransportVehicle> root = new TreeItem<>(new TransportVehicle() {
        @Override
        public String toString() {
            return "Transport Vehicles";
        }
    });
    private final TreeItem<TransportVehicle> mechanicalChild = new TreeItem<>(new MechanicalVehicle() {
        @Override
        public String toString() {
            return "Mechanical Vehicles";
        }
    });
    private final TreeItem<TransportVehicle> nonMechanicalChild = new TreeItem<>(new NonMechanicalVehicle() {
        @Override
        public String toString() {
            return "Non Mechanical Vehicles";
        }
    });

    private TransportVehicle currTransportVehicle;
    private TreeItem<TransportVehicle> currTransportItem;

    @FXML
    private void initialize() {
        binaryRB.setOnAction(e -> vehicleFileProcessType = VehicleFileProcessType.BINARY);
        xmlRB.setOnAction(e -> vehicleFileProcessType = VehicleFileProcessType.XML);
        customRB.setOnAction(e -> vehicleFileProcessType = VehicleFileProcessType.CUSTOM);

        saveButton.setOnAction(this::saveVehicles);
        loadButton.setOnAction(this::loadVehicles);

        formBorderPane.setCenter(abstractVehicleForm);

        root.getChildren().addAll(mechanicalChild, nonMechanicalChild);
        root.setExpanded(true);
        treeView.setRoot(root);
        treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        treeView.setOnMouseClicked(this::clickedView);

        bicycleRB.setOnAction(e -> selectedRBForm(new BicycleForm()));
        cartRB.setOnAction(e -> selectedRBForm(new CartForm()));
        sportCarRB.setOnAction(e -> selectedRBForm(new SportCarForm()));
        tractorRB.setOnAction(e -> selectedRBForm(new TractorForm()));
        motorcycleRB.setOnAction(e -> selectedRBForm(new MotorCycleForm()));

        addVehicleButton.setOnAction(this::addVehicleForm);
        deleteVehicleButton.setOnAction(this::deleteVehicle);
        saveVehicleButton.setOnAction(this::saveVehicleForm);

        convertXmlTOJsonButton.setOnAction(this::convertXmlToJson);
        if (xmlToJsonMapper == null) {
            showAlert(Alert.AlertType.WARNING, "XML to JSON Plugin", "Couldn't load xml mapper plugin");
            convertXmlTOJsonButton.setVisible(false);
        }
        if (dataArchivator == null) {
            showAlert(Alert.AlertType.WARNING, "Archivator", "Couldn't load archivator plugin");
            archivationCB.setVisible(false);
        }
    }

    private void convertXmlToJson(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.setTitle("Load XML");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            xmlToJsonMapper.map(file);
        }
    }

    private void saveVehicleForm(ActionEvent actionEvent) {
        if (currTransportVehicle != null) {
            AbstractVehicleForm center = (AbstractVehicleForm) formBorderPane.getCenter();
            Optional<TransportVehicle> transportVehicle = center.generateVehicle();
            if (transportVehicle.isPresent()) {
                TransportVehicle transportVehicleReal = transportVehicle.get();
                currTransportItem.setValue(transportVehicleReal);
                int i = transportVehicles.indexOf(currTransportVehicle);
                transportVehicles.set(i, transportVehicleReal);
                center.clear();
            } else {
                showAlert(Alert.AlertType.ERROR, "Validation", "Check data correctness.");
            }
        }
    }

    private void selectedRBForm(AbstractVehicleForm form) {
        formBorderPane.setCenter(form);
        currTransportVehicle = null;
        currTransportItem = null;
    }

    private void deleteVehicle(ActionEvent actionEvent) {
        if (currTransportVehicle != null) {
            mechanicalChild.getChildren().remove(currTransportItem);
            nonMechanicalChild.getChildren().remove(currTransportItem);
            transportVehicles.remove(currTransportVehicle);
            AbstractVehicleForm center = (AbstractVehicleForm) formBorderPane.getCenter();
            currTransportVehicle = null;
            currTransportItem = null;
            center.clear();
        }
    }

    private void clickedView(MouseEvent event) {
        ObservableList<TreeItem<TransportVehicle>> selectedItems = treeView.getSelectionModel().getSelectedItems();
        if (!selectedItems.isEmpty()) {

            currTransportItem = selectedItems.get(0);
            currTransportVehicle = currTransportItem.getValue();
            // roots
            AbstractVehicleForm center = (AbstractVehicleForm) formBorderPane.getCenter();
            if (currTransportVehicle.getBrandName() == null) {
                currTransportVehicle = null;
                currTransportItem = null;
                center.clear();
                return;
            }
            // just set forms and contents
            // and selected item
            if (currTransportVehicle instanceof Bicycle) {
                bicycleRB.setSelected(true);
                formBorderPane.setCenter(new BicycleForm());
                BicycleForm form = (BicycleForm) formBorderPane.getCenter();
                Bicycle el = (Bicycle) currTransportVehicle;
                form.setForm(el);
            } else if (currTransportVehicle instanceof Cart) {
                cartRB.setSelected(true);
                formBorderPane.setCenter(new CartForm());
                CartForm form = (CartForm) formBorderPane.getCenter();
                Cart el = (Cart) currTransportVehicle;
                form.setForm(el);
            } else if (currTransportVehicle instanceof Motorcycle) {
                motorcycleRB.setSelected(true);
                formBorderPane.setCenter(new MotorCycleForm());
                MotorCycleForm form = (MotorCycleForm) formBorderPane.getCenter();
                Motorcycle el = (Motorcycle) currTransportVehicle;
                form.setForm(el);
            } else if (currTransportVehicle instanceof SportCar) {
                sportCarRB.setSelected(true);
                formBorderPane.setCenter(new SportCarForm());
                SportCarForm form = (SportCarForm) formBorderPane.getCenter();
                SportCar el = (SportCar) currTransportVehicle;
                form.setForm(el);
            } else { // tractor
                tractorRB.setSelected(true);
                formBorderPane.setCenter(new TractorForm());
                TractorForm form = (TractorForm) formBorderPane.getCenter();
                Tractor el = (Tractor) currTransportVehicle;
                form.setForm(el);
            }
        }
    }

    private void addVehicleForm(ActionEvent actionEvent) {
        AbstractVehicleForm center = (AbstractVehicleForm) formBorderPane.getCenter();
        Optional<TransportVehicle> transportVehicle = center.generateVehicle();
        if (transportVehicle.isPresent()) {
            TransportVehicle transportVehicleReal = transportVehicle.get();
            transportVehicles.add(transportVehicleReal);
            if (transportVehicleReal instanceof MechanicalVehicle) {
                mechanicalChild.getChildren().add(new TreeItem<>(transportVehicleReal));
            } else {
                nonMechanicalChild.getChildren().add(new TreeItem<>(transportVehicleReal));
            }
            center.clear();
        } else {
            showAlert(Alert.AlertType.ERROR, "Validation", "Check data correctness.");
        }
    }

    private FileChooser.ExtensionFilter getExtensionFilter() {
        if (xmlRB.isSelected()) {
            return new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        } else if (customRB.isSelected()) {
            return new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        } else {
            return new FileChooser.ExtensionFilter("Binary files (*.custom-bin)", "*.custom-bin");
        }
    }

    private void loadVehicles(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.setTitle("Load Vehicles");
        ObservableList<FileChooser.ExtensionFilter> extensionFilters = fileChooser.getExtensionFilters();
        extensionFilters.add(getExtensionFilter());
        extensionFilters.add(new FileChooser.ExtensionFilter("Any file (*.*)", "*.*"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            if (archivationCB.isSelected()) {
                file = dataArchivator.dearchivate(file);
            }
            VehicleDeserializer vehicleDeserializer = deserializerFactory.createVehicleDeserializer(vehicleFileProcessType);
            transportVehicles = vehicleDeserializer.deserializeVehicles(file);
            if (archivationCB.isSelected()) {
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            buildTreeView(transportVehicles);
        }
    }

    private void buildTreeView(List<TransportVehicle> transportVehicles) {
        ObservableList<TreeItem<TransportVehicle>> mechanicalChildChildren = mechanicalChild.getChildren();
        ObservableList<TreeItem<TransportVehicle>> nonMechanicalChildChildren = nonMechanicalChild.getChildren();
        mechanicalChildChildren.clear();
        nonMechanicalChildChildren.clear();
        for (TransportVehicle transportVehicle : transportVehicles) {
            if (transportVehicle instanceof MechanicalVehicle) {
                mechanicalChildChildren.add(new TreeItem<>(transportVehicle));
            } else {
                nonMechanicalChildChildren.add(new TreeItem<>(transportVehicle));
            }
        }
    }

    private void saveVehicles(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.setTitle("Save Vehicles");
        ObservableList<FileChooser.ExtensionFilter> extensionFilters = fileChooser.getExtensionFilters();
        extensionFilters.add(getExtensionFilter());
        extensionFilters.add(new FileChooser.ExtensionFilter("Any file (*.*)", "*.*"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            VehicleSerializer vehicleSerializer = serializerFactory.createVehicleSerializer(vehicleFileProcessType);
            vehicleSerializer.serializeVehicles(file, transportVehicles);
            if (archivationCB.isSelected()) {
                dataArchivator.archivate(file);
            }
        }
    }

    private void showAlert(Alert.AlertType type, String title, String info) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(info);
        alert.show();
    }

    public void setDataArchivator(DataArchivator dataArchivator) {
        this.dataArchivator = dataArchivator;
    }

    public void setXmlToJsonMapper(XmlToJsonMapper xmlToJsonMapper) {
        this.xmlToJsonMapper = xmlToJsonMapper;
    }
}
