package com.example.ProjectManagementSystem.DTO;
import com.example.ProjectManagementSystem.modal.Issue;
import com.example.ProjectManagementSystem.modal.User;
import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsDTO {
    private Long id;
    private String content;
    private LocalDateTime createdDate;
    private User user;
    private Issue issue;

}
