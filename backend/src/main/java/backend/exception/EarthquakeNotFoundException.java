package backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Earthquake not found", value = HttpStatus.NOT_FOUND)
public class EarthquakeNotFoundException extends RuntimeException{
    public EarthquakeNotFoundException(String message) {
        super(message);
    }
}
