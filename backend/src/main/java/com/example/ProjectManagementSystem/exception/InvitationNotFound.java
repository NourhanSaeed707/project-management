package com.example.ProjectManagementSystem.exception;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvitationNotFound extends RuntimeException{
    private final HttpStatus status;
    public InvitationNotFound(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }
}
