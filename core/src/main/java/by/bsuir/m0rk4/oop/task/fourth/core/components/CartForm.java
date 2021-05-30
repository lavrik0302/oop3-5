package by.bsuir.m0rk4.oop.task.fourth.core.components;

import by.bsuir.m0rk4.oop.task.fourth.core.entity.Cart;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.MuscularPower;
import by.bsuir.m0rk4.oop.task.fourth.core.entity.TransportVehicle;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;

public class CartForm extends NonMechanicalVehicleForm {
    private final Label peopleCapacity = new Label("People Capacity");
    private final TextField peopleCapacityTField = new TextField();

    private final Label haulersCount = new Label("Haulers Count");
    private final TextField haulersCountTField = new TextField();

    public CartForm() {
        super();
        ObservableList<Node> children = getChildren();
        children.addAll(
                peopleCapacity,
                peopleCapacityTField,
                haulersCount,
                haulersCountTField
        );
    }

    @Override
    public void clear() {
        super.clear();
        peopleCapacityTField.clear();
        haulersCountTField.clear();
    }

    public void setForm(Cart cart) {
        super.setForm(cart);
        peopleCapacityTField.setText(cart.getPeopleCapacity() + "");
        haulersCountTField.setText(cart.getHaulersCount() + "");
    }

    public int getPeopleCapacity() throws InvalidFormDataException {
        String text = peopleCapacityTField.getText();
        try {
            int i = Integer.parseInt(text);
            if (i > 10) {
                throw new InvalidFormDataException("People capacity of cart must be <= 10");
            }
            return i;
        } catch (NumberFormatException e) {
            throw new InvalidFormDataException(e.getMessage(), e.getCause());
        }
    }

    public int getHaulersCount() throws InvalidFormDataException {
        String text = haulersCountTField.getText();
        try {
            int i = Integer.parseInt(text);
            if (i > 4) {
                throw new InvalidFormDataException("Haulers count of cart must be <= 4");
            }
            return i;
        } catch (NumberFormatException e) {
            throw new InvalidFormDataException(e.getMessage(), e.getCause());
        }
    }


    @Override
    public Optional<TransportVehicle> generateVehicle() {
        try {
            String brandName = getBrandName();
            double maxSpeed = getMaxSpeed();
            double weight = getWeight();
            MuscularPower muscularPower = generateMuscularPower();
            int peopleCapacity = getPeopleCapacity();
            int haulersCount = getHaulersCount();
            Cart cart = new Cart(maxSpeed, weight, brandName, muscularPower, peopleCapacity, haulersCount);
            return Optional.of(cart);
        } catch (InvalidFormDataException e) {
            return Optional.empty();
        }
    }
}
