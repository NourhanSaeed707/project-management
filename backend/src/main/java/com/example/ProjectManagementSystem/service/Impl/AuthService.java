package com.example.ProjectManagementSystem.service.Impl;
import com.example.ProjectManagementSystem.config.JwtProvider;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.request.LoginRequest;
import com.example.ProjectManagementSystem.response.AuthResponse;
import com.example.ProjectManagementSystem.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsImpl customUserDetails;

    public AuthResponse register(User user) throws Exception {
        Optional<User> isUserExists = userRepository.findByEmail(user.getEmail());
        if(isUserExists.isPresent()) {
            throw new Exception("Email is already exists.");
        }
        User newUser = User.builder().email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .fullName(user.getFullName()).build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtProvider.generateToken(authentication);
        userRepository.save(newUser);
        return AuthResponse.builder().jwt(jwt).message("sign up success").build();

    }

    public AuthResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtProvider.generateToken(authentication);
        return AuthResponse.builder().jwt(jwt).message("login success").build();
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if(userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
