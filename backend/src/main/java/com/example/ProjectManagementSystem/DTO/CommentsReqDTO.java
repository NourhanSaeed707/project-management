package com.example.ProjectManagementSystem.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsReqDTO {
    private Long issueId;
    private String content;

}
