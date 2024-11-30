package com.example.ProjectManagementSystem.service.Impl;
import com.example.ProjectManagementSystem.exception.InvitationNotFound;
import com.example.ProjectManagementSystem.exception.TokenInvalid;
import com.example.ProjectManagementSystem.modal.Invitation;
import com.example.ProjectManagementSystem.respository.InvitationRepository;
import com.example.ProjectManagementSystem.service.EmailService;
import com.example.ProjectManagementSystem.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public void sendInvitation(String email, Long projectId) throws Exception {
         String invitationToken = UUID.randomUUID().toString();
         Invitation invitation = Invitation.builder().email(email).token(invitationToken).projectId(projectId).build();
         invitationRepository.save(invitation);
         String invitationLink = "http://localhost:5173/accept_invitation?token=" + invitationToken;
         emailService.sendEmailWithToken(email, invitationLink);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) {
        return invitationRepository.findByToken(token).orElseThrow(() -> new TokenInvalid("Invalid invitation token"));
    }

    @Override
    public String getTokenByUserEmail(String userEmail) {
        Invitation invitation = invitationRepository.findByEmail(userEmail).orElseThrow(() -> new InvitationNotFound("Invitation not found"));
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {
         Invitation invitation = invitationRepository.findByToken(token).orElseThrow(() -> new TokenInvalid("Invalid token"));
         invitationRepository.delete(invitation);
    }
}
