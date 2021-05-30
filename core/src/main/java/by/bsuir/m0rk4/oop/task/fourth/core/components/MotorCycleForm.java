package by.bsuir.m0rk4.oop.task.fourth.core.components;

import by.bsuir.m0rk4.oop.task.fourth.core.entity.Engine;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.Motorcycle;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;

public class MotorCycleForm extends MechanicalVehicleForm {

    private final Label passengerCount = new Label("Passengers Count");
    private final TextField passengersTField = new TextField();

    private final Label hasFrontGlass = new Label("Front Glass?");
    private final CheckBox frontCB = new CheckBox();

    public void setForm(Motorcycle motorcycle) {
        super.setForm(motorcycle);
        passengersTField.setText(motorcycle.getPassengersCount() + "");
        frontCB.setSelected(motorcycle.hasFrontGlass());
    }

    public MotorCycleForm() {
        super();
        ObservableList<Node> children = getChildren();
        children.addAll(
                passengerCount,
                passengersTField,
                hasFrontGlass,
                frontCB
        );
    }

    public int getPassengersCount() throws InvalidFormDataException {
        String text = passengersTField.getText();
        try {
            int i = Integer.parseInt(text);
            if (i > 5) {
                throw new InvalidFormDataException("Passengers count must be < 5");
            }
            return i;
        } catch (NumberFormatException e) {
            throw new InvalidFormDataException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void clear() {
        super.clear();
        passengersTField.clear();
    }

    @Override
    public Optional<TransportVehicle> generateVehicle() {
        try {
            Engine engine = generateEngine();
            String brandName = getBrandName();
            double maxSpeed = getMaxSpeed();
            double weight = getWeight();
            int passengersCount = getPassengersCount();
            boolean front = frontCB.isSelected();
            Motorcycle motorcycle = new Motorcycle(maxSpeed, weight, brandName, engine, passengersCount, front);
            return Optional.of(motorcycle);
        } catch (InvalidFormDataException e) {
            return Optional.empty();
        }
    }


}
