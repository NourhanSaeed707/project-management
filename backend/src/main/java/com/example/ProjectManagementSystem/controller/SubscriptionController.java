package com.example.ProjectManagementSystem.controller;
import com.example.ProjectManagementSystem.modal.PlanType;
import com.example.ProjectManagementSystem.modal.Subscription;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.service.SubscriptionService;
import com.example.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Subscription> getUserSubscriptions(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Subscription subscription = subscriptionService.getUsersSubscriptions(user.getId());
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> upgradeSubscription(@RequestParam PlanType planType, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Subscription subscription = subscriptionService.upgrade(user.getId(), planType);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
