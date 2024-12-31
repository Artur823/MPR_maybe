package pl.edu.pjatk.MPR_spring.exception;

public class NoChangesDetectedException extends RuntimeException {
    public NoChangesDetectedException(String message) {
        super(message);
    }
}