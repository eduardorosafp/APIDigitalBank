package br.com.eduar.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongPasswordException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public WrongPasswordException(String str) {
        super(str);
    }
    public WrongPasswordException() {
        super("The password that was entered does not match with the registered!");
    }

}
