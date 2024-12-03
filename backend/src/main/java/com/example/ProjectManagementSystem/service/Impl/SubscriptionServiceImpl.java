package com.example.ProjectManagementSystem.service.Impl;
import com.example.ProjectManagementSystem.modal.PlayType;
import com.example.ProjectManagementSystem.modal.Subscription;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.respository.SubscriptionRepository;
import com.example.ProjectManagementSystem.service.SubscriptionService;
import com.example.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private UserService userService;

    @Override
    public Subscription create(User user) {
        Subscription subscription = Subscription.builder().user(user).subscriptionStartDate(LocalDate.now())
                .subscriptionEndDate(LocalDate.now().plusMonths(12))
                .isValid(true)
                .playType(PlayType.FREE).build();
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUsersSubscriptions(Long userId) {
        return null;
    }

    @Override
    public Subscription update(Long userId, PlayType playType) {
        return null;
    }

    @Override
    public boolean isValid(Subscription subscription) {
        return false;
    }
}
