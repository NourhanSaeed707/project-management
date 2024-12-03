package com.example.ProjectManagementSystem.service;
import com.example.ProjectManagementSystem.modal.PlanType;
import com.example.ProjectManagementSystem.modal.Subscription;
import com.example.ProjectManagementSystem.modal.User;

public interface SubscriptionService {
    Subscription create(User user);
    Subscription getUsersSubscriptions(Long userId);
    Subscription upgrade(Long userId, PlanType playType);
    boolean isValid(Subscription subscription);
}
