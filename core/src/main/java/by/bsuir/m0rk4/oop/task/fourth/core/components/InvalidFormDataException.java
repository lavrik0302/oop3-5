package by.bsuir.m0rk4.oop.task.fourth.core.components;

public class InvalidFormDataException extends Exception {

    public InvalidFormDataException(String message) {
        super(message);
    }

    public InvalidFormDataException() {
    }

    public InvalidFormDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
