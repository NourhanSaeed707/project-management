package com.example.ProjectManagementSystem.controller;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.request.LoginRequest;
import com.example.ProjectManagementSystem.response.AuthResponse;
import com.example.ProjectManagementSystem.service.Impl.AuthService;
import com.example.ProjectManagementSystem.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register (@RequestBody User user) throws Exception {
        return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
       return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.CREATED);
    }


}
