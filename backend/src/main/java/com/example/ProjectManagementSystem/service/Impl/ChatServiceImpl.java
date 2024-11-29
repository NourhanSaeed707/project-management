package com.example.ProjectManagementSystem.service.Impl;
import com.example.ProjectManagementSystem.modal.Chat;
import com.example.ProjectManagementSystem.respository.ChatRepository;
import com.example.ProjectManagementSystem.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat create(Chat chat) {
        return chatRepository.save(chat);
    }
}
