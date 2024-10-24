package org.example.tz4krainet.Exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Integer project_id) {
        super("Project with ID: " + project_id + " Not Found");
    }
    public ProjectNotFoundException(String project_name) {
        super("Project with project name: " + project_name + " Not Found");
    }
}
