package com.example.pharmacy.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author nirmal
 */
public class NotFoundException extends ResponseStatusException {

    public NotFoundException() {
        super(NOT_FOUND);
    }

    public NotFoundException(String reason) {
        super(NOT_FOUND, reason);
    }

    public NotFoundException(String reason, Throwable cause) {
        super(NOT_FOUND, reason, cause);
    }
}
