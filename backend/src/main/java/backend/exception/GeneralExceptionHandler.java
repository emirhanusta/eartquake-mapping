package backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EarthquakeNotFoundException.class)
    public ResponseEntity<String> handleEarthquakeNotFoundException(EarthquakeNotFoundException exception) {
        return ResponseEntity.status(404).body(exception.getMessage());
    }
}
