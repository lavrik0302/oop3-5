package by.bsuir.m0rk4.oop.task.fourth.core.components;

import by.bsuir.m0rk4.oop.task.fourth.core.entity.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;

public class BicycleForm  extends NonMechanicalVehicleForm {

    private final Label wheelsCount = new Label("Wheels Count");
    private final TextField wheelsTField = new TextField();

    private final Label isMultiSeat = new Label("Multiple seats?");
    private final CheckBox multi = new CheckBox();

    public BicycleForm() {
        super();
        ObservableList<Node> children = getChildren();
        children.addAll(
                wheelsCount,
                wheelsTField,
                isMultiSeat,
                multi
        );
    }

    public int getWheelsCount() throws InvalidFormDataException {
        String text = wheelsTField.getText();
        try {
            int i = Integer.parseInt(text);;
            if (i > 4) {
                throw new InvalidFormDataException("Wheels count of bicycle must be <= 4");
            }
            return i;
        } catch (NumberFormatException e) {
            throw new InvalidFormDataException(e.getMessage(), e.getCause());
        }
    }

    public void setForm(Bicycle bicycle) {
        super.setForm(bicycle);
        wheelsTField.setText(bicycle.getWheelsCount() + "");
        multi.setSelected(bicycle.hasMultiSeat());
    }

    @Override
    public void clear() {
        super.clear();
        wheelsTField.clear();
    }

    @Override
    public Optional<TransportVehicle> generateVehicle() {
        try {
            String brandName = getBrandName();
            double maxSpeed = getMaxSpeed();
            double weight = getWeight();
            int wheelsCount = getWheelsCount();
            MuscularPower muscularPower = generateMuscularPower();
            boolean isMultiSeat = multi.isSelected();
            Bicycle bicycle = new Bicycle(maxSpeed, weight, brandName, muscularPower, wheelsCount, isMultiSeat);
            return Optional.of(bicycle);
        } catch (InvalidFormDataException e) {
            return Optional.empty();
        }
    }
}
