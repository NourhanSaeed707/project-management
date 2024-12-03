package com.example.ProjectManagementSystem.respository;
import com.example.ProjectManagementSystem.modal.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatIdOrderByCreatedAtAsc(Long chatId);
}
