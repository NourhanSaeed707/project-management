package com.example.ProjectManagementSystem.service;
import com.example.ProjectManagementSystem.modal.PlayType;
import com.example.ProjectManagementSystem.modal.Subscription;
import com.example.ProjectManagementSystem.modal.User;

public interface SubscriptionService {
    Subscription create(User user);
    Subscription getUsersSubscriptions(Long userId);
    Subscription update(Long userId, PlayType playType);
    boolean isValid(Subscription subscription);
}
