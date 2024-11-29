package com.example.ProjectManagementSystem.respository;
import com.example.ProjectManagementSystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
