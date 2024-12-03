package com.example.ProjectManagementSystem.respository;
import com.example.ProjectManagementSystem.modal.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByUserId(Long userId);
}
