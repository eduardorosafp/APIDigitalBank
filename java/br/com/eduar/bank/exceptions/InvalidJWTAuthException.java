package br.com.eduar.bank.exceptions;

import org.springframework.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJWTAuthException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public InvalidJWTAuthException(String str) {
        super(str);
    }

}
