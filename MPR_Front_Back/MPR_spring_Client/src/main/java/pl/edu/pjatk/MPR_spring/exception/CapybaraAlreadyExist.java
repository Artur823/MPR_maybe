package pl.edu.pjatk.MPR_spring.exception;

public class CapybaraAlreadyExist extends RuntimeException {
    public CapybaraAlreadyExist() {
        super("Capybara already exist" );
    }
}
