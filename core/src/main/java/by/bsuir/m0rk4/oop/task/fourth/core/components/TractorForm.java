package by.bsuir.m0rk4.oop.task.fourth.core.components;

import by.bsuir.m0rk4.oop.task.fourth.core.entity.Engine;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.Tractor;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;

public class TractorForm extends MechanicalVehicleForm {

    private final Label thrustForce = new Label("Thrust Force");
    private final TextField thrustForceTField = new TextField();

    public void setForm(Tractor tractor) {
        super.setForm(tractor);
        thrustForceTField.setText(tractor.getThrustForce() + "");
    }

    public TractorForm() {
        super();
        ObservableList<Node> children = getChildren();
        children.addAll(
                thrustForce,
                thrustForceTField
        );
    }

    public double getThrustForce() throws InvalidFormDataException {
        String text = thrustForceTField.getText();
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new InvalidFormDataException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void clear() {
        super.clear();
        thrustForceTField.clear();
    }

    @Override
    public Optional<TransportVehicle> generateVehicle() {
        try {
            Engine engine = generateEngine();
            String brandName = getBrandName();
            double maxSpeed = getMaxSpeed();
            double weight = getWeight();
            double thrustForce = getThrustForce();
            Tractor tractor = new Tractor(maxSpeed, weight, brandName, engine, thrustForce);
            return Optional.of(tractor);
        } catch (InvalidFormDataException e) {
            return Optional.empty();
        }
    }
}
