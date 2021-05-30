package by.bsuir.m0rk4.oop.task.fourth.core.components;

import by.bsuir.m0rk4.oop.task.fourth.core.entity.Engine;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.SportCar;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.Optional;

public class SportCarForm extends MechanicalVehicleForm {

    private final Label hasSpoiler = new Label("Spoiler?");
    private final CheckBox spoilerCB = new CheckBox();

    public void setForm(SportCar sportCar) {
        super.setForm(sportCar);
        spoilerCB.setSelected(sportCar.hasSpoiler());
    }

    public SportCarForm() {
        super();
        ObservableList<Node> children = getChildren();
        children.addAll(
                hasSpoiler,
                spoilerCB
        );
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Optional<TransportVehicle> generateVehicle() {
        try {
            Engine engine = generateEngine();
            String brandName = getBrandName();
            double maxSpeed = getMaxSpeed();
            double weight = getWeight();
            boolean hasSpoiler = spoilerCB.isSelected();
            SportCar sportCar = new SportCar(maxSpeed, weight, brandName, engine, hasSpoiler);
            return Optional.of(sportCar);
        } catch (InvalidFormDataException e) {
            return Optional.empty();
        }
    }
}
