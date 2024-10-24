package org.example.tz4krainet.Exception;

public class ProjectWasFoundException extends RuntimeException {

    public ProjectWasFoundException(String message) {
        super("Project name was found, change project name, your current project name: " + message);
    }
}
