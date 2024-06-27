
package com.styleme.microAuth.exception;

public class AuthValidateException extends BusinessLogicException{

    public AuthValidateException() {
    }

    public AuthValidateException(String message) {
        super(message);
    }

    public AuthValidateException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
