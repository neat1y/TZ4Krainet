package org.example.tz4krainet.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer user_id) {
        super("User with ID: " + user_id + " not found");
    }
    public UserNotFoundException(String user_id) {
        super("User with this username: " + user_id + " not found");
    }

}
