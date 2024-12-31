package pl.edu.pjatk.MPR_spring.exception;

public class CapybaraNotFoundException extends RuntimeException {
    public CapybaraNotFoundException() {
        super("Capybara not found.");
    }

    public CapybaraNotFoundException(String message) {
        super(message);
    }
}
