package com.example.ProjectManagementSystem.service;
import com.example.ProjectManagementSystem.DTO.IssueDTO;
import com.example.ProjectManagementSystem.modal.Issue;
import com.example.ProjectManagementSystem.modal.User;
import com.example.ProjectManagementSystem.request.IssueRequest;

import java.util.*;

public interface IssueService {
    Issue getIssueById(Long issueId) throws Exception;
    List<Issue> getIssueByProjectId(Long projectId) throws Exception;
    IssueDTO create(IssueRequest issue, User user) throws Exception;
    String deleteIssue(Long issueId, Long userId) throws Exception;
    Issue addUserToIssue(Long issueId, Long userId) throws Exception;
    Issue updateStatus(Long issueId, String status) throws Exception;
}
