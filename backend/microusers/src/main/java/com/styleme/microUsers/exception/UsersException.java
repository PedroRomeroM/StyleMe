
package com.styleme.microUsers.exception;

public class UsersException extends BusinessLogicException{

    public UsersException() {
    }

    public UsersException(String message) {
        super(message);
    }

    public UsersException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
