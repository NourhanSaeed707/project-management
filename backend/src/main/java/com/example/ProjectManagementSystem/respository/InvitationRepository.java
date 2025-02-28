package com.example.ProjectManagementSystem.respository;
import com.example.ProjectManagementSystem.modal.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    Optional<Invitation> findByToken(String token);
    Optional<Invitation> findByEmail(String email);
}
