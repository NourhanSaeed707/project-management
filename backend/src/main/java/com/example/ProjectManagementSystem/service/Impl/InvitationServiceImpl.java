package com.example.ProjectManagementSystem.service.Impl;
import com.example.ProjectManagementSystem.modal.Invitation;
import com.example.ProjectManagementSystem.respository.InvitationRepository;
import com.example.ProjectManagementSystem.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;

    @Override
    public void sendInvitation(String email, Long projectId) {

    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) {
        return null;
    }

    @Override
    public String getTokenByUserEmail(String userEmail) {
        return "";
    }

    @Override
    public void deleteToken(String token) {

    }
}
