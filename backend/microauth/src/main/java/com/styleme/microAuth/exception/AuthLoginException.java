
package com.styleme.microAuth.exception;

public class AuthLoginException extends BusinessLogicException{

    public AuthLoginException() {
    }

    public AuthLoginException(String message) {
        super(message);
    }

    public AuthLoginException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
