
package com.styleme.microAuth.exception;

public class AuthUpdateException extends BusinessLogicException{

    public AuthUpdateException() {
    }

    public AuthUpdateException(String message) {
        super(message);
    }

    public AuthUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
