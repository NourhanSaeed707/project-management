package com.example.ProjectManagementSystem.respository;
import com.example.ProjectManagementSystem.modal.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
