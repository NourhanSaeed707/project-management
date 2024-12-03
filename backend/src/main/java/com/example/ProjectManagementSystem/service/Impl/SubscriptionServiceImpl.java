package com.example.ProjectManagementSystem.service.Impl;
import com.example.ProjectManagementSystem.modal.PlanType;
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
                .planType(PlanType.FREE).build();
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUsersSubscriptions(Long userId) {
        Subscription subscription =  subscriptionRepository.findByUserId(userId);
        if(!isValid(subscription)) {
            subscription.setPlanType(PlanType.FREE);
            subscription.setSubscriptionStartDate(LocalDate.now().plusMonths(12));
            subscription.setSubscriptionEndDate(LocalDate.now());
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription upgrade(Long userId, PlanType playType) {
        Subscription subscriptionUser = subscriptionRepository.findByUserId(userId);
        Subscription subscription = new Subscription();
        subscription.setPlanType(playType);
        subscription.setSubscriptionStartDate(LocalDate.now());
        if(playType.equals(PlanType.ANNUALLY) ) {
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        }else{
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
        if(subscription.getPlanType().equals(PlanType.FREE)) {
            return true;
        }
        LocalDate endDate = subscription.getSubscriptionStartDate();
        LocalDate currentDate = LocalDate.now();
        return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
    }
}
