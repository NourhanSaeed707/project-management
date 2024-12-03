package com.example.ProjectManagementSystem.service;
import com.example.ProjectManagementSystem.modal.Message;
import java.util.*;

public interface MessageService {
    Message sendMessage(Long senderId, Long projectId, String content) throws Exception;
    List<Message> getMessageByProjectId(Long projectId) throws Exception;
}
