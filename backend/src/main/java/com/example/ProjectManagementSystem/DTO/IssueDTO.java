package com.example.ProjectManagementSystem.DTO;
import com.example.ProjectManagementSystem.modal.Project;
import com.example.ProjectManagementSystem.modal.User;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long projectId;
    private String priority;
    private LocalDate dueDate;
    private List<String> tags = new ArrayList<>();
    private User assignee;
    private Project project;
}
