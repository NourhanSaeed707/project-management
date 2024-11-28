package com.example.ProjectManagementSystem.respository;
import com.example.ProjectManagementSystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
