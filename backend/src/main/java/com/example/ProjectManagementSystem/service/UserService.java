package com.example.ProjectManagementSystem.service;
import com.example.ProjectManagementSystem.modal.User;

import java.util.Optional;

public interface UserService {
    User findUserProfileByJwt(String jwt) throws Exception;
    User findByEmail(String email);
    User findUserById(Long userId) throws Exception;
    User updateUsersProjectSize(User user, int number);

}
