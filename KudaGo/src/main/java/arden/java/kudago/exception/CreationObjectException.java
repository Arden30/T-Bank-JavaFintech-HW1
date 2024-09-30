package arden.java.kudago.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class CreationObjectException extends GeneralException {
    private final String description = "Can't create a new object";
    private final HttpStatusCode httpStatusCode = HttpStatus.NOT_FOUND;

    public CreationObjectException(final String message) {
        super(message);
    }
}
