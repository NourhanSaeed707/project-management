package com.example.ProjectManagementSystem.service.Impl;
import com.example.ProjectManagementSystem.exception.NotFoundException;
import com.example.ProjectManagementSystem.modal.Chat;
import com.example.ProjectManagementSystem.modal.Message;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.respository.MessageRepository;
import com.example.ProjectManagementSystem.respository.UserRepository;
import com.example.ProjectManagementSystem.service.MessageService;
import com.example.ProjectManagementSystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectService projectService;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User sender = userRepository.findById(senderId).orElseThrow(()-> new NotFoundException("User not found"));
        Chat chat = projectService.getChatByProjectId(projectId);
        Message message = Message.builder().content(content).sender(sender).createdAt(LocalDateTime.now()).chat(chat).build();
        Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        return savedMessage;
    }

    @Override
    public List<Message> getMessageByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        return messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
    }
}
