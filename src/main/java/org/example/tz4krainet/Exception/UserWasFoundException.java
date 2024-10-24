package org.example.tz4krainet.Exception;

public class UserWasFoundException extends RuntimeException {
    public UserWasFoundException(String message) {
        super("user with this username: " + message +" was found");
    }
}
