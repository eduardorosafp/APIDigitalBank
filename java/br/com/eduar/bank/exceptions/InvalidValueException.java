package br.com.eduar.bank.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidValueException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidValueException(String str) {
        super(str);
    }

    public InvalidValueException() {
        super("Put a valid value!");
    }

}
