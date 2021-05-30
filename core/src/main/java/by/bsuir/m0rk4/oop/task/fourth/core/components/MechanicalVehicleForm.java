package by.bsuir.m0rk4.oop.task.fourth.core.components;

import by.bsuir.m0rk4.oop.task.fourth.core.entity.Engine;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.MechanicalVehicle;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public abstract class MechanicalVehicleForm extends AbstractVehicleForm {

    private final Label engineCylinders = new Label("Engine Cylinders Count");
    private final TextField engineCylindersTField = new TextField();

    private final Label enginePower = new Label("Engine Power (watt)");
    private final TextField enginePowerTField = new TextField();

    public MechanicalVehicleForm() {
        super();
        ObservableList<Node> children = getChildren();
        children.addAll(
                engineCylinders,
                engineCylindersTField,
                enginePower,
                enginePowerTField
        );
    }

    @Override
    public void clear() {
        super.clear();
        enginePowerTField.clear();
        engineCylindersTField.clear();
    }

    public void setForm(MechanicalVehicle mechanicalVehicle) {
        super.setForm(mechanicalVehicle);
        Engine engine = mechanicalVehicle.getEngine();
        enginePowerTField.setText(engine.getPower() + "");
        engineCylindersTField.setText(engine.getCylinderCount() + "");
    }

    public Engine generateEngine() throws InvalidFormDataException {
        String cylindersTFieldText = engineCylindersTField.getText();
        String powerTFieldText = enginePowerTField.getText();
        try {
            int cylindersCount = Integer.parseInt(cylindersTFieldText);
            double power = Double.parseDouble(powerTFieldText);
            return new Engine(cylindersCount, power);
        } catch (NumberFormatException e) {
            throw new InvalidFormDataException(e.getMessage(), e.getCause());
        }
    }
}
