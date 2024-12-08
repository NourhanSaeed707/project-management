package com.example.ProjectManagementSystem.controller;
import com.example.ProjectManagementSystem.modal.PlanType;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.response.PaymentLinkResponse;
import com.example.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${paymob.api.key}")
    private String apiKey;
    @Value("${paymob.api.secret}")
    private String apiSecret;
    @Autowired
    private UserService userService;

//    @PostMapping("/{planType}")
//    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable PlanType planType, @RequestHeader("Authorization") String jwt ) throws Exception {
//        User user = userService.findUserProfileByJwt(jwt);
//        int amount = 799 * 100;
//        if(planType.equals(PlanType.ANNUALLY)) {
//            amount = amount * 11;
//            amount = (int) (amount*0.7);
//        }
//
//    }

}
