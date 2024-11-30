package com.example.ProjectManagementSystem.exception;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TokenInvalid extends  RuntimeException{
    private final HttpStatus status;
    public TokenInvalid(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
