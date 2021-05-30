package by.bsuir.m0rk4.oop.task.fourth.core.components;

import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Optional;

public abstract class AbstractVehicleForm extends VBox {

    private final Label brandName = new Label("Brand");
    private final TextField brandTField = new TextField();

    private final Label maxSpeed = new Label("Max Speed (km/h)");
    private final TextField maxSpeedTField = new TextField();

    private final Label weight = new Label("Weight (kg)");
    private final TextField weightTField = new TextField();

    public AbstractVehicleForm() {
        setPadding(new Insets(5,10,5,10));
        setSpacing(5.0);
        ObservableList<Node> children = getChildren();
        children.addAll(
                brandName,
                brandTField,
                maxSpeed,
                maxSpeedTField,
                weight,
                weightTField      
        );
    }

    public void clear() {
        brandTField.clear();
        maxSpeedTField.clear();
        weightTField.clear();
    }

    public void setForm(TransportVehicle transportVehicle) {
        brandTField.setText(transportVehicle.getBrandName());
        maxSpeedTField.setText(transportVehicle.getMaxSpeed() + "");
        weightTField.setText(transportVehicle.getWeight() + "");
    }

    protected String getBrandName() throws InvalidFormDataException {
        String text = brandTField.getText();
        if (text.isBlank()) {
            throw new InvalidFormDataException("Check Brand\n");
        } else {
            return text;
        }
    }


    protected double getMaxSpeed() throws InvalidFormDataException {
        String text = maxSpeedTField.getText();
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new InvalidFormDataException(e.getMessage(), e.getCause());
        }
    }

    protected double getWeight() throws InvalidFormDataException {
        String text = weightTField.getText();
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new InvalidFormDataException(e.getMessage(), e.getCause());
        }
    }

    public abstract Optional<TransportVehicle> generateVehicle();
}
