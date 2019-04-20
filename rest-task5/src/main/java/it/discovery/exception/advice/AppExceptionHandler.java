package it.discovery.exception.advice;

import it.discovery.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(
            BookNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.NOT_FOUND);

    }
}
