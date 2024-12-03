package com.example.ProjectManagementSystem.DTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageReqDTO {
    private Long senderId;
    private String content;
    private Long projectId;
}
