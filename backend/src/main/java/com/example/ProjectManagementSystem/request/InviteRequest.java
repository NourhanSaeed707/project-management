package com.example.ProjectManagementSystem.request;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteRequest {
    private Long projectId;
    private String email;
}
