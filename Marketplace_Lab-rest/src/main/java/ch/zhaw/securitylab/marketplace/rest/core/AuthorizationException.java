package ch.zhaw.securitylab.marketplace.rest.core;

public class AuthorizationException extends RuntimeException { 
    public AuthorizationException(String errorMessage) {
        super(errorMessage);
    }
}