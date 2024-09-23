package arden.java.kudago.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class GeneralException extends RuntimeException {
    private final String description = "Problems in app";
    private final HttpStatusCode httpStatusCode = HttpStatus.NOT_FOUND;

    public GeneralException(final String message) {
        super(message);
    }
}
