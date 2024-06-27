
package com.styleme.microChallenge.exception;

public class ChallengeException extends BusinessLogicException{

    public ChallengeException() {
    }

    public ChallengeException(String message) {
        super(message);
    }

    public ChallengeException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
