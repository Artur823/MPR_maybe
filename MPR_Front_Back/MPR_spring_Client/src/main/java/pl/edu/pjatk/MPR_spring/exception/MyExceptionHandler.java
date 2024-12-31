package pl.edu.pjatk.MPR_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    // Обработка исключения, если капибара не найдена
    @ExceptionHandler(value = {CapybaraNotFoundException.class})
    public ResponseEntity<Object> handleCapybaraNotFound(CapybaraNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Обработка исключения, если капибара уже существует
    @ExceptionHandler(value = {CapybaraAlreadyExist.class})
    public ResponseEntity<Object> handleCapybaraAlreadyExist(CapybaraAlreadyExist ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
