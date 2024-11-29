package com.example.ProjectManagementSystem.exception;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
public class ProjectNotFound extends RuntimeException{
    private final HttpStatus status;
    public ProjectNotFound(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }
}
