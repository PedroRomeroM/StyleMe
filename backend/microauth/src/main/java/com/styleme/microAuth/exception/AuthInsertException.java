
package com.styleme.microAuth.exception;

public class AuthInsertException extends BusinessLogicException{

    public AuthInsertException() {
    }

    public AuthInsertException(String message) {
        super(message);
    }

    public AuthInsertException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
