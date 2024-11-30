package com.example.ProjectManagementSystem.service;

import com.example.ProjectManagementSystem.modal.Invitation;

public interface InvitationService {
    void sendInvitation(String email, Long projectId) throws Exception;
    Invitation acceptInvitation(String token, Long userId);
    String getTokenByUserEmail(String userEmail);
    void deleteToken(String token);
}
