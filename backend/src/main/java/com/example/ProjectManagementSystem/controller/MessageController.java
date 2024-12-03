package com.example.ProjectManagementSystem.controller;

import com.example.ProjectManagementSystem.DTO.MessageReqDTO;
import com.example.ProjectManagementSystem.modal.Chat;
import com.example.ProjectManagementSystem.modal.Message;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.service.MessageService;
import com.example.ProjectManagementSystem.service.ProjectService;
import com.example.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageReqDTO messageReqDTO) throws Exception {
        User user = userService.findUserById(messageReqDTO.getSenderId());
        Chat chat = projectService.getProjectById(messageReqDTO.getProjectId()).getChat();
        if(chat == null) {
            throw new Exception("Chats not found");
        }
        Message sendMessage = messageService.sendMessage(messageReqDTO.getSenderId(), messageReqDTO.getProjectId(), messageReqDTO.getContent());
        return ResponseEntity.ok(sendMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception {
        List<Message> messages = messageService.getMessageByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
