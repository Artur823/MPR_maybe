package pl.edu.pjatk.MPR_spring.exception;

public class CapybaraAlredyExist extends RuntimeException {
    public CapybaraAlredyExist() {
        super("Capybara alredy exist" );
    }
}
